package cti.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.mozilla.universalchardet.UniversalDetector;

import cti.app.bean.FileManagerBean;

public class FileManagerService extends AppService {

	// 在桌面下用檔名找檔案
	public static String findFileOnDesktopByFileName(String fileName) {
		FileManagerBean fmb = new FileManagerBean();
		fmb.setResultType(FM_RESULTTYPE_FILE);
		fmb.setByFileName(fileName);
		fileManager(fmb);
		return fmb.getResultString();
	}

	/*** 主要執行點 ***/
	public static void fileManager(FileManagerBean fmb) {
		try {
			if (StringUtils.isBlank(fmb.getPath())) {
				throw new Exception("path不可為空值");
			}

			File file = new File(fmb.getPath());
			if (!file.isDirectory() && !file.isFile()) {
				throw new Exception("path須為資料夾或檔案");
			}

			if (StringUtils.isBlank(fmb.getResultType())) {
				throw new Exception("resultType不可為空值");
			}

			if (StringUtils.isNotBlank(fmb.getByFileText())) {
				fmb.setResultType(FM_RESULTTYPE_FILE);
			}

			loop4FindAllFiles(fmb.getPath(), fmb);

			StringBuffer sb = new StringBuffer();
			for (String item : fmb.getResultList()) {
				sb.append(item + System.lineSeparator());
			}

			fmb.setResultString(StringUtils.trimToEmpty(sb.toString()));
		} catch (Exception e) {
			fmb.setResultList(Arrays.asList(e.getMessage()));
		}
	}

	/*** 單純用路徑跑loop ***/
	private static void loop4FindAllFiles(String path, FileManagerBean fmb) {
		File file = new File(path);
		if (file.isDirectory()) {
			for (String fileName : file.list()) {
				String tempPath = path + File.separator + fileName;
				File tempFile = new File(tempPath);
				fmb.setTempPath(tempPath);

				if (FM_RESULTTYPE_ALL.equals(fmb.getResultType())) {
					if (tempFile.isDirectory()) {
						dirFilter(fmb);
					} else if (tempFile.isFile()) {
						fileFilter(fmb);
					}
				} else if (FM_RESULTTYPE_DIR.equals(fmb.getResultType()) && tempFile.isDirectory()) {
					dirFilter(fmb);
				} else if (FM_RESULTTYPE_FILE.equals(fmb.getResultType()) && tempFile.isFile()) {
					fileFilter(fmb);
				}

				loop4FindAllFiles(tempPath, fmb);
			}
		}
	}

	/*** 資料夾過濾器 ***/
	private static FileManagerBean dirFilter(FileManagerBean fmb) {
		boolean isRunning = true;
		String path = fmb.getTempPath();
		File file = new File(path);
		String fileName = file.getName();
		String fileNameU = fileName.toUpperCase();
		long fileLastModified = file.lastModified();

		// 資料夾-修改日大於等於
		if (isRunning && fmb.getByDir_Modify_greaterThan() != null) {
			isRunning = (fileLastModified >= fmb.getByDir_Modify_greaterThan().getTime());
		}

		// 資料夾-修改日小於等於
		if (isRunning && fmb.getByDir_Modify_lessThan() != null) {
			isRunning = (fileLastModified <= fmb.getByDir_Modify_lessThan().getTime());
		}

		// 資料夾名稱-整個字組搜尋
		if (isRunning && (StringUtils.isNotBlank(fmb.getByDirName()) || StringUtils.isNotBlank(fmb.getByDirNameFuzzy()))) {
			if (StringUtils.isNotBlank(fmb.getByDirNameFuzzy())) {
				// 資料夾名稱-字組模糊搜尋
				isRunning = fileNameU.matches(getFuzzySearchRegexpString(fmb.getByDirNameFuzzy().toUpperCase()));
			} else if (fmb.isDirName_CaseSensitive()) {
				isRunning = fmb.getByDirName().equals(fileName);
			} else {
				isRunning = (fmb.getByDirName().compareToIgnoreCase(fileName) == 0);
			}
		}
		if (isRunning) {
			fmb.getResultList().add(path);
		}
		return fmb;
	}

	/*** 檔案過濾器 ***/
	private static FileManagerBean fileFilter(FileManagerBean fmb) {
		boolean isRunning = true;
		String path = fmb.getTempPath();
		File file = new File(path);
		String fileName = file.getName();
		String fileNameU = fileName.toUpperCase();
		long fileLastModified = file.lastModified();

		// 檔案-副檔名(確認)
		if (isRunning && fmb.getByFileName_Extension() != null) {
			isRunning = false;
			for (Object obj : new JSONArray(fmb.getByFileName_Extension())) {
				if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
					isRunning = true;
				}
			}
		}

		// 檔案-副檔名(忽略)
		if (isRunning && fmb.getByFileName_Extension_Ignore() != null) {
			for (Object obj : new JSONArray(fmb.getByFileName_Extension_Ignore())) {
				if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
					isRunning = false;
				}
			}
		}

		// 檔案-修改日大於等於
		if (isRunning && fmb.getByFile_Modify_greaterThan() != null) {
			isRunning = (fileLastModified >= fmb.getByFile_Modify_greaterThan().getTime());
		}

		// 檔案-修改日小於等於
		if (isRunning && fmb.getByFile_Modify_lessThan() != null) {
			isRunning = (fileLastModified <= fmb.getByFile_Modify_lessThan().getTime());
		}

		// 檔案名稱-整個字組搜尋
		if (isRunning && (StringUtils.isNotBlank(fmb.getByFileName()) || StringUtils.isNotBlank(fmb.getByFileNameFuzzy()))) {
			// 檔案名稱-字組模糊搜尋
			if (StringUtils.isNotBlank(fmb.getByFileNameFuzzy())) {
				isRunning = fileNameU.matches(getFuzzySearchRegexpString(fmb.getByFileNameFuzzy().toUpperCase()));
			} else if (fmb.isFileName_CaseSensitive()) {
				isRunning = fmb.getByFileName().equals(fileName);
			} else {
				isRunning = (fmb.getByFileName().compareToIgnoreCase(fileName) == 0);
			}
		}

		/*** 檔案內容過濾器 ***/
		if ((isRunning && StringUtils.isNotBlank(fmb.getByFileText()))) {
			return fileTextFilter(fmb);
		}

		if (isRunning) {
			fmb.getResultList().add(path);
		}
		return fmb;
	}

	/*** 檔案內容過濾器 ***/
	private static FileManagerBean fileTextFilter(FileManagerBean fmb) {
		boolean isRunning = false;
		String path = fmb.getTempPath();
		File file = new File(path);
		String fileAbsPath = file.getAbsolutePath();
		try {
			try (BufferedReader brLog = new BufferedReader((new InputStreamReader(new FileInputStream(fileAbsPath), getFileEncoding(fileAbsPath))));) {
				String line;
				while ((line = brLog.readLine()) != null) {
					if (StringUtils.isBlank(line)) {
						continue;
					}
					// System.out.println(fileAbsPath + "," + line);

					String tempText = fmb.getByFileText();
					if (!fmb.isFileText_CaseSensitive()) {
						line = line.toUpperCase();
						tempText = tempText.toUpperCase();
					}

					if (line.matches(getFuzzySearchRegexpString(tempText))) {
						isRunning = true;
						break;
					}
				}
			}
		} catch (Exception e) {

		}

		if (isRunning) {
			fmb.getResultList().add(path);
		}
		return fmb;
	}

	/*** 主要執行點 ***/
	public static void fileContentManager(FileManagerBean fmb) {
		try {
			if (StringUtils.isBlank(fmb.getPath())) {
				throw new Exception("path不可為空值");
			}

			File file = new File(fmb.getPath());
			if (!file.isFile()) {
				throw new Exception("path須為檔案");
			}

			readFileContent(fmb);
		} catch (Exception e) {
			fmb.setResultString(e.getMessage());
		}
	}

	/*** 讀檔案內所有內容 ***/
	public static FileManagerBean readFileContent(FileManagerBean fmb) throws Exception {
		List<String> list = new ArrayList<>();
		String path = fmb.getPath();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), getFileEncoding(path)));) {
			String line;
			while ((line = br.readLine()) != null) {
				if (StringUtils.isNotBlank(line)) {
					list.add(line);
				}
			}
		}
		fmb.setResultString(String.join(System.lineSeparator(), list));
		return fmb;
	}

	/*** 讀檔案內所有內容 ***/
	public static String readFileContent(String path) throws Exception {
		StringBuffer content = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), getFileEncoding(path)));) {
			String line;
			while ((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				content.append(line);
			}
		}
		return content.toString();
	}

	private static String getFuzzySearchRegexpString(String text) {
		return REGEXP_FORALL + getExchangeSpecialCharacter4Regexp(text) + REGEXP_FORALL;
	}

	/*** 轉換正規表示法中的特殊字元 ***/
	private static String getExchangeSpecialCharacter4Regexp(String input) {
		input = input.replaceAll("\\\\", "\\\\\\\\");
		input = input.replaceAll("\\^", "\\\\\\^");
		input = input.replaceAll("\\$", "\\\\\\$");
		input = input.replaceAll("\\*", "\\\\\\*");
		input = input.replaceAll("\\+", "\\\\\\+");
		input = input.replaceAll("\\?", "\\\\\\?");
		input = input.replaceAll("\\{", "\\\\\\{");
		input = input.replaceAll("\\}", "\\\\\\}");
		input = input.replaceAll("\\.", "\\\\\\.");
		input = input.replaceAll("\\(", "\\\\\\(");
		input = input.replaceAll("\\)", "\\\\\\)");
		input = input.replaceAll("\\|", "\\\\\\|");
		input = input.replaceAll("\\[", "\\\\\\[");
		input = input.replaceAll("\\]", "\\\\\\]");
		return input;
	}

	/*** 找編碼 ***/
	public static String getFileEncoding(String filepath) {
		try (FileInputStream fis = new FileInputStream(filepath)) {
			/* 建立分析器 */
			UniversalDetector detector = new UniversalDetector(null);
			int nread;
			byte[] buf = new byte[4096];
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				/* 分析資料 */
				detector.handleData(buf, 0, nread);
			}
			detector.dataEnd();
			if (StringUtils.isBlank(detector.getDetectedCharset())) {
				return System.getProperty("file.encoding");
			} else {
				return detector.getDetectedCharset();
			}
		} catch (Exception e) {
			return System.getProperty("file.encoding");
		}
	}

	private static String changeUerRegexp(String input) {
		return input.toUpperCase().replaceAll("\\*", REGEXP_FORALL);
	}
}
