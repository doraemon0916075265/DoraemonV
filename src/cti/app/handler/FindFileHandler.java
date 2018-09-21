package cti.app.handler;

import java.io.File;
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
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, FindFileConstant.VALUE_SEARCHPATH, ERRMSG_ISBLANK));
		}
		List<String> list = new ArrayList<>();
		String resultStr = "";

		JSONArray isMatchesJA;
		JSONArray isMatchesIgnoreJA;
//		changeUerRegexp
		
		try {
			isMatchesJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION).replaceAll("\\*", ".*").toUpperCase());
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "1", ERRMSG_FORMAT));
		}
		try {
			isMatchesIgnoreJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION_IGNORE).toUpperCase());
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "2", ERRMSG_FORMAT));
		}
System.out.println("123"+m.get(KEY_FILENAMEEXTENSION).replaceAll("\\*", ".*").toUpperCase());
System.out.println("456"+m.get(KEY_FILENAMEEXTENSION).replaceAll("\\*", ".*").toUpperCase());
		findAllFile(list, jtf_searchPath, isMatchesJA, isMatchesIgnoreJA);

		for (String str : list) {
			resultStr += (str + System.lineSeparator());
		}
		m.put(FindFileConstant.KEY_RESULT, resultStr);
		return m;
	}

	/*** 找目錄下的所有檔案 ***/
	public static List<String> findAllFile(List<String> list, String filePath, JSONArray isMatchesJA, JSONArray isMatchesIgnoreJA) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					findAllFile(list, file.getAbsolutePath() + File.separator + fileName, isMatchesJA, isMatchesIgnoreJA);
				}
			} else {
				String fileNameU = file.getName().toUpperCase();

				boolean isMatches = false;
				boolean isMatchesIgnore = false;

				for (Object obj : isMatchesJA) {
					if (fileNameU.matches(obj.toString())) {
						isMatches = true;
					}
				}

				for (Object obj : isMatchesIgnoreJA) {
					if (fileNameU.matches(obj.toString())) {
						isMatchesIgnore = true;
					}
				}

				if (isMatches && !isMatchesIgnore) {
					list.add(file.getAbsolutePath());
				}

			}
		} catch (Exception e) {
			return list;
		}
		return list;
	}
}
