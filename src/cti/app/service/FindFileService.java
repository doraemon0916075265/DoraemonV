package cti.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import cti.app.bean.FindFileBean;

public class FindFileService extends AppService {
	private static List<String> list = new ArrayList<>();

	public static FindFileBean findConditionFile(FindFileBean ffb) {
		list = new ArrayList<>();

		String resultStr = "";
		findAllConditionFile(ffb.getSearchPath(), ffb);
		for (String str : list) {
			resultStr += (str + System.lineSeparator());
		}
		ffb.setResult(resultStr);
		return ffb;
	}

	/*** 找目錄下的所有檔案 ***/
	private static void findAllConditionFile(String filePath, FindFileBean ffb) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					findAllConditionFile(file.getAbsolutePath() + File.separator + fileName, ffb);
				}
			} else if (file.isFile()) {
				String fileNameU = file.getName().toUpperCase();
				String fileAbsPath = file.getAbsolutePath();

				boolean isRunning = false;

				for (Object obj : new JSONArray(ffb.getByFilenameExtension())) {
					if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
						isRunning = true;
					}
				}

				if (isRunning) {
					for (Object obj : new JSONArray(ffb.getByFilenameExtension_Ignore())) {
						if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
							isRunning = false;
						}
					}
				}

				if (isRunning && StringUtils.isNotBlank(ffb.getByFilename())) {
					if (new File(fileAbsPath).getName().matches(getFuzzySearchRegexpString(ffb.getByFilename(), true))) {
						isRunning = true;
					} else {
						isRunning = false;
					}
				}

				if (isRunning && ffb.getByModify_greaterThan() != null) {
					if (new File(fileAbsPath).lastModified() >= ffb.getByModify_greaterThan().getTime()) {
						isRunning = true;
					} else {
						isRunning = false;
					}
				}

				if (isRunning && ffb.getByModify_lessThan() != null) {
					if (new File(fileAbsPath).lastModified() < ffb.getByModify_lessThan().getTime()) {
						isRunning = true;
					} else {
						isRunning = false;
					}
				}

				if (isRunning && StringUtils.isNotBlank(ffb.getByText())) {
					isRunning = false;
					try (BufferedReader brLog = new BufferedReader((new InputStreamReader(new FileInputStream(fileAbsPath), getFileEncoding(fileAbsPath))));) {
						String line;
						String tempText;
						while ((line = brLog.readLine()) != null) {
							if (StringUtils.isBlank(line)) {
								continue;
							}
							// System.out.println(filePath + "," + line);

							tempText = ffb.getByText();
							if (!ffb.isTextCaseSensitive()) {
								line = line.toUpperCase();
								tempText = tempText.toUpperCase();
							}

							if (line.matches(getFuzzySearchRegexpString(tempText, true))) {
								isRunning = true;
								break;
							}
						}
					}
				}

				if (isRunning) {
					list.add(fileAbsPath);
				}
			} else {
				// 不是檔案也不是資料夾
			}
		} catch (Exception e) {

		}
	}

	private static String getFuzzySearchRegexpString(String text, boolean isWholeWordSraech) {
		String result = "";
		if (isWholeWordSraech) {// 整個字組搜尋
			result = getExchangeSpecialCharacter4Regexp(text);
		} else {// 模糊搜尋
			for (int i = 0; i < text.length(); i++) {
				result += "[" + getExchangeSpecialCharacter4Regexp(text.substring(i, i + 1)) + "]";
				result += (i == (text.length() - 1) ? "" : REGEXP_FORALL);
			}
		}
		result = REGEXP_FORALL + result + REGEXP_FORALL;
		// System.out.println("正規表示法>" + result);
		return result;
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

	private static String changeUerRegexp(String input) {
		String result = input.toUpperCase();
		result = result.replaceAll("\\*", REGEXP_FORALL);
		return result;
	}
}
