package cti.app.handler;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cti.app.constant.CutterConstant;

public class SimpleFileHandler extends CutterConstant {

	public static Map<String, String> InitialCutter() {
		Map<String, String> m = new HashMap<>();
		System.out.println("ww" + m.get("12"));
		String path_desktop = getRootPath4Desktop();

		if (StringUtils.isNotBlank(path_desktop)) {
			findAimFile(m, path_desktop, false);
			m.put(KEY_EXPORTFILEPATH, path_desktop + File.separator + FILENAME_RESULT);
		}
		if (StringUtils.isBlank(m.get(KEY_LOGFILEPATH))) {
			m.put(KEY_LOGFILEPATH, DFT_PATH_LOG);
		}
		if (StringUtils.isBlank(m.get(KEY_SPECFILEPATH))) {
			m.put(KEY_SPECFILEPATH, DFT_PATH_SPEC);
		}
		return m;
	}

	/*** 找桌面根目錄 ***/
	public static String getRootPath4Desktop() {
		Map<String, String> m = new HashMap<>();
		findRootPath(m, PATH_DISK_C, false, false);
		if (StringUtils.isNotBlank(m.get(KEY_EXPORTFILEPATH))) {
			return m.get(KEY_EXPORTFILEPATH);
		} else {
			return "";
		}
	}

	private static void findRootPath(Map<String, String> m, String filePath, boolean isDir1, boolean isDir2) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					String fileNameU = fileName.toUpperCase();
					String newFilePath = file.getAbsolutePath() + File.separator + fileName;
					if (!isDir1 && DIRECTORYNAME_USERS.equals(fileNameU)) {
						findRootPath(m, newFilePath, true, false);
					}
					if (isDir1 && !isDir2 && fileNameU.matches(DIRECTORYNAME_REGEXP_NT)) {
						findRootPath(m, newFilePath, true, true);
					}
					if (isDir1 && isDir2 && DIRECTORYNAME_DESKTOP.equals(fileNameU)) {
						m.put(KEY_EXPORTFILEPATH, newFilePath);
					}
				}
			}
		} catch (Exception e) {

		}
	}

	private static ArrayList<String> findAimFile(Map<String, String> m, String filePath, boolean isFoundAll) {
		ArrayList<String> list = new ArrayList<>();
		File file = new File(filePath);
		try {
			if (isFoundAll) {
				return null;
			} else {
				if (file.isDirectory()) {
					for (String fileName : file.list()) {
						list.addAll(findAimFile(m, filePath + File.separator + fileName, false));
					}
				} else {
					String fileNameU = file.getName().toUpperCase();
					if (FILENAME_LOG.equals(fileNameU)) {
						m.put(KEY_LOGFILEPATH, filePath);
					}
					if (FILENAME_SPEC.equals(fileNameU)) {
						m.put(KEY_SPECFILEPATH, filePath);
					}
					if (StringUtils.isNotBlank(m.get(KEY_LOGFILEPATH)) && StringUtils.isNotBlank(m.get(KEY_SPECFILEPATH))) {
						isFoundAll = true;
					}
					list.add(filePath);
				}
				return list;
			}
		} catch (Exception e) {
			return list;
		}
	}

	public static void exportFile(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_EXPORTFILEPATH))) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, VALUE_EXPORTFILEPATH, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_SRESULT))) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, VALUE_SRESULT, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_FRESULT))) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, VALUE_FRESULT, ERRMSG_ISBLANK));
		}
		String path = m.get(KEY_EXPORTFILEPATH);
		File f;
		File fp;
		try {
			f = new File(path);
			fp = new File(f.getParent());
		} catch (Exception e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_EXPORTFILEPATH, ERRMSG_FORMAT));
		}

		if (f.getName().toUpperCase().matches(REGEXP_FILE_TXT)) {
			if (!fp.exists()) {
				fp.mkdirs();
			}
		} else {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_EXPORTFILEPATH, ERRMSG_FORMAT));
		}

		try (FileWriter fw = new FileWriter(f.getAbsolutePath())) {
			fw.write(String.format(FORMAT_EXPORTFILE_SUBTITLE, VALUE_SRESULT));
			fw.write(String.format(FORMAT_EXPORTFILE_CONTENT, m.get(KEY_SRESULT)));
			fw.write(System.lineSeparator());
			fw.write(String.format(FORMAT_EXPORTFILE_SUBTITLE, VALUE_FRESULT));
			fw.write(String.format(FORMAT_EXPORTFILE_CONTENT, m.get(KEY_FRESULT)));
			fw.flush();
		} catch (Exception e) {
			throw new Exception();
		}

	}

}
