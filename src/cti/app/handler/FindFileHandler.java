package cti.app.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import cti.app.constant.FindFileConstant;

public class FindFileHandler extends FindFileConstant {

	/*** FindFile：找目錄下符合條件的所有檔案 ***/
	public static Map<String, String> findConditionFile(Map<String, String> m) throws Exception {
		String jtf_searchPath = m.get(FindFileConstant.KEY_SEARCHPATH);
		if (StringUtils.isBlank(jtf_searchPath)) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, VALUE_SEARCHPATH, ERRMSG_ISBLANK));
		}
		List<String> list = new ArrayList<>();
		String resultStr = "";

		JSONArray isMatchesJA;
		JSONArray isMatchesIgnoreJA;

		try {
			isMatchesJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION));
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "1", ERRMSG_FORMAT));
		}
		try {
			isMatchesIgnoreJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION_IGNORE));
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "2", ERRMSG_FORMAT));
		}

		findAllConditionFile(list, jtf_searchPath, isMatchesJA, isMatchesIgnoreJA, m.get(KEY_SEARCHTEXT));

		for (String str : list) {
			resultStr += (str + System.lineSeparator());
		}

		m.put(FindFileConstant.KEY_RESULT, resultStr);
		return m;
	}

	private static String changeUerRegexp(String input) {
		String result = input.toUpperCase();
		result = result.replaceAll("\\*", REGEXP_FORALL);
		return result;
	}

	/*** 找目錄下的所有檔案 ***/
	public static List<String> findAllConditionFile(List<String> list, String filePath, JSONArray isMatchesJA, JSONArray isMatchesIgnoreJA, String searchText) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					findAllConditionFile(list, file.getAbsolutePath() + File.separator + fileName, isMatchesJA, isMatchesIgnoreJA, searchText);
				}
			} else {
				String fileNameU = file.getName().toUpperCase();
				String filepath = file.getAbsolutePath();

				boolean isRunning = false;

				for (Object obj : isMatchesJA) {
					if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
						isRunning = true;
					}
				}

				if (isRunning) {
					for (Object obj : isMatchesIgnoreJA) {
						if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
							isRunning = false;
						}
					}
				}

				if (isRunning && StringUtils.isNotBlank(searchText)) {
					isRunning = false;
					try (BufferedReader brLog = new BufferedReader((new InputStreamReader(new FileInputStream(filepath), AppHandler.getFileEncoding(filepath))));) {
						String line;
						while ((line = brLog.readLine()) != null) {
							if (StringUtils.isBlank(line)) {
								continue;
							}
							String lineU = line.toUpperCase();
							if (lineU.matches(REGEXP_FORALL + searchText + REGEXP_FORALL)) {
								isRunning = true;
								break;
							}
						}
					}
				}

				if (isRunning) {
					list.add(filepath);
				}

			}
		} catch (Exception e) {
			return list;
		}
		return list;
	}

}
