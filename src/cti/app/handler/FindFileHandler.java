//package cti.app.handler;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import cti.app.constant.FindFileConstant;
//
//public class FindFileHandler extends FindFileConstant {
//	private static List<String> list = new ArrayList<>();
//
//	private static String byText;
//	private static String byFilename;
//	private static String byModify_greaterThan;
//	private static String byModify_lessThan;
//	private static JSONArray isMatchesJA;
//	private static JSONArray isMatchesIgnoreJA;
//
//	/*** FindFile：找目錄下符合條件的所有檔案 ***/
//	public static Map<String, String> findConditionFile(Map<String, String> m) throws Exception {
//		// 所有值初始化
//		list = new ArrayList<>();
//
//		String searchPath = m.get(KEY_SEARCHPATH);
//		if (StringUtils.isBlank(searchPath)) {
//			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, VALUE_SEARCHPATH, ERRMSG_IS_BLANK));
//		}
//
//		// 初步判斷&塞值
//		byText = m.get(KEY_BYTEXT);
//		byFilename = m.get(KEY_BYFILENAME);
//
//		try {
//			isMatchesJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION));
//			m.put(FindFileConstant.KEY_FILENAMEEXTENSION, isMatchesJA.toString().replaceAll(SIGN_DBQUOTES, ""));
//		} catch (JSONException e) {
//			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "副檔名", ERRMSG_FORMAT));
//		}
//
//		try {
//			isMatchesIgnoreJA = new JSONArray(m.get(KEY_FILENAMEEXTENSION_IGNORE));
//			m.put(FindFileConstant.KEY_FILENAMEEXTENSION_IGNORE, isMatchesIgnoreJA.toString().replaceAll(SIGN_DBQUOTES, ""));
//		} catch (JSONException e) {
//			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "副檔名(忽略)", ERRMSG_FORMAT));
//		}
//
//		byModify_greaterThan = m.get(KEY_MODIFYGREATERTHAN);
//		byModify_lessThan = m.get(KEY_MODIFYLESSTHAN);
//
//		// 執行迴圈
//		String resultStr = "";
//		findAllConditionFile(searchPath);
//		for (String str : list) {
//			resultStr += (str + System.lineSeparator());
//		}
//
//		m.put(FindFileConstant.KEY_RESULT, resultStr);
//		return m;
//	}
//
//	private static String changeUerRegexp(String input) {
//		String result = input.toUpperCase();
//		result = result.replaceAll("\\*", REGEXP_FORALL);
//		return result;
//	}
//
//	/*** 找目錄下的所有檔案 ***/
//	private static void findAllConditionFile(String filePath) {
//		File file = new File(filePath);
//		try {
//			if (file.isDirectory()) {
//				for (String fileName : file.list()) {
//					findAllConditionFile(file.getAbsolutePath() + File.separator + fileName);
//				}
//			} else if (file.isFile()) {
//				String fileNameU = file.getName().toUpperCase();
//				String fileAbsPath = file.getAbsolutePath();
//
//				boolean isRunning = false;
//
//				for (Object obj : isMatchesJA) {
//					if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
//						isRunning = true;
//					}
//				}
//
//				if (isRunning) {
//					for (Object obj : isMatchesIgnoreJA) {
//						if (fileNameU.matches(changeUerRegexp(obj.toString()))) {
//							isRunning = false;
//						}
//					}
//				}
//
//				if (isRunning && StringUtils.isNotBlank(byFilename)) {
//					if (new File(fileAbsPath).getName().matches(AppHandler.getFuzzySearchRegexpString(byFilename, true))) {
//						isRunning = true;
//					} else {
//						isRunning = false;
//					}
//				}
//
//				if (isRunning && StringUtils.isNotBlank(byModify_greaterThan)) {
//					if (new File(fileAbsPath).lastModified() >= APPDATE_SDF.parse(byModify_greaterThan).getTime()) {
//						isRunning = true;
//					} else {
//						isRunning = false;
//					}
//				}
//
//				if (isRunning && StringUtils.isNotBlank(byModify_lessThan)) {
//					if (new File(fileAbsPath).lastModified() < APPDATE_SDF.parse(byModify_lessThan).getTime()) {
//						isRunning = true;
//					} else {
//						isRunning = false;
//					}
//				}
//
//				if (isRunning && StringUtils.isNotBlank(byText)) {
//					isRunning = false;
//					try (BufferedReader brLog = new BufferedReader((new InputStreamReader(new FileInputStream(fileAbsPath), AppHandler.getFileEncoding(fileAbsPath))));) {
//						String line;
//						while ((line = brLog.readLine()) != null) {
//							if (StringUtils.isBlank(line)) {
//								continue;
//							}
//							// System.out.println(filePath + "," + line);
//							if (line.matches(AppHandler.getFuzzySearchRegexpString(byText, true))) {
//								isRunning = true;
//								break;
//							}
//						}
//					}
//				}
//
//				if (isRunning) {
//					list.add(fileAbsPath);
//				}
//			} else {
//				// 不是檔案也不是資料夾
//			}
//		} catch (Exception e) {
//
//		}
//	}
//
//}
