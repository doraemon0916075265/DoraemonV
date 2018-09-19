package cti.app.handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cti.app.constant.CutterConstant;

public class CutterHandler extends CutterConstant {

	/*** 切電文 ***/
	public static Map<String, String> analysis(Map<String, String> m) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		JSONArray ja_sCut0;
		JSONArray ja_sCut;
		JSONArray ja_fCut0;
		JSONArray ja_fCut;

		checkMap4Log(m);
		checkMap4Spec(m);

		try {
			ja_sCut0 = new JSONArray(m.get(KEY_SCUT0));
			if (ja_sCut0.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT0, ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT0, ERRMSG_FORMAT));
		}
		try {
			ja_sCut = new JSONArray(m.get(KEY_SCUT));
			if (ja_sCut.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT, ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT, ERRMSG_FORMAT));
		}
		try {
			ja_fCut0 = new JSONArray(m.get(KEY_FCUT0));
			if (ja_fCut0.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT0, ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT0, ERRMSG_FORMAT));
		}
		try {
			ja_fCut = new JSONArray(m.get(KEY_FCUT));
			if (ja_fCut.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT, ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT, ERRMSG_FORMAT));
		}

		List<String> list1 = cutter(m.get(KEY_SEND), ja_sCut0, ja_sCut);
		List<String> list2 = cutter(m.get(KEY_FILL), ja_fCut0, ja_fCut);
		resultMap.put(KEY_SCUT0, ja_sCut0.toString());
		resultMap.put(KEY_SCUT, ja_sCut.toString());
		resultMap.put(KEY_FCUT0, ja_fCut0.toString());
		resultMap.put(KEY_FCUT, ja_fCut.toString());
		resultMap.put(KEY_SRESULT, list1.toString());
		resultMap.put(KEY_FRESULT, list2.toString());

		return resultMap;
	}

	private static List<String> cutter(String text, JSONArray arr_cut0, JSONArray arr_cut) throws UnsupportedEncodingException {
		int cutIndex = 0;
		List<String> resultList = new ArrayList<>();
		int gbkLen = getGBKLen(text);
		resultList.add(System.lineSeparator());
		for (int i = 0; i < arr_cut0.length(); i++) {
			Integer cutSize = Integer.parseInt(arr_cut0.get(i).toString());
			resultList.add(SIGN_DOUBLEQUOTES + text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DOUBLEQUOTES);
			gbkLen -= cutSize;
		}
		while (gbkLen > arr_cut.length()) {
			resultList.add(System.lineSeparator());
			for (int i = 0; i < arr_cut.length(); i++) {
				Integer cutSize = Integer.parseInt(arr_cut.get(i).toString());
				resultList.add(SIGN_DOUBLEQUOTES + text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DOUBLEQUOTES);
				gbkLen -= cutSize;
			}
		}
		resultList.add(System.lineSeparator());
		return resultList;
	}

	/*** 讀檔 ***/
	public static Map<String, String> readFile(String pathLog, String pathSpec) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		if (StringUtils.isBlank(pathLog) || StringUtils.isBlank(pathSpec)) {
			throw new FileNotFoundException(ERRMSG_HASBLANK);
		}

		// 預先判斷是否皆可正常讀檔
		try (FileInputStream fisLog = new FileInputStream(pathLog); BufferedReader brLog = new BufferedReader(new InputStreamReader(fisLog, APP_FRAME_ENCODING));) {
		}
		try (FileInputStream fisSpec = new FileInputStream(pathSpec); BufferedReader brSpec = new BufferedReader(new InputStreamReader(fisSpec, APP_FRAME_ENCODING));) {
		}

		/*** 讀log檔 ***/
		try (FileInputStream fisLog = new FileInputStream(pathLog); BufferedReader brLog = new BufferedReader(new InputStreamReader(fisLog, APP_FRAME_ENCODING));) {
			String line;
			boolean isSend = false;
			boolean isFill = false;
			while ((line = brLog.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				String lineU = line.toUpperCase();
				if (lineU.matches(REGEXP_TG_NAME1)) {
					resultMap.put(KEY_ID, Pattern.compile(REGEXP_TG_ID).matcher(lineU).replaceAll(""));
				} else if (lineU.matches(REGEXP_TG_NAME2)) {
					resultMap.put(KEY_ID, lineU.replace(PARAM_TGID, ""));
				}
				if (isSend) {
					resultMap.put(KEY_SEND, lineU);
				}
				if (isFill) {
					resultMap.put(KEY_FILL, lineU);
				}
				isSend = false;
				isFill = false;
				if (lineU.matches(REGEXP_TG_SEND1) || lineU.matches(REGEXP_TG_SEND2)) {
					isSend = true;
				}
				if (lineU.matches(REGEXP_TG_FILL1) || lineU.matches(REGEXP_TG_FILL2)) {
					isFill = true;
				}
			}
			checkReadFileMap4ID(resultMap);
			checkReadFileMap4Log(resultMap);
		}

		/*** 讀spec檔 ***/
		try (FileInputStream fisSpec = new FileInputStream(pathSpec); BufferedReader brSpec = new BufferedReader(new InputStreamReader(fisSpec, APP_FRAME_ENCODING));) {
			String line;
			String lineSpec = "";
			boolean isFoundId = false;
			while ((line = brSpec.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				lineSpec += line;
			}
			try {
				JSONArray jsonArr = new JSONObject(lineSpec).getJSONArray("Spec");
				for (int i = 0; i < jsonArr.length(); i++) {
					if (getJsonValue(jsonArr.get(i), KEY_ID).equals(resultMap.get(KEY_ID))) {
						JSONObject jsonObj = new JSONObject(jsonArr.get(i).toString());
						// 必要欄位，不塞try-catch
						resultMap.put(KEY_SCUT0, jsonObj.get(KEY_SCUT0).toString());
						resultMap.put(KEY_SCUT, jsonObj.get(KEY_SCUT).toString());
						resultMap.put(KEY_FCUT0, jsonObj.get(KEY_FCUT0).toString());
						resultMap.put(KEY_FCUT, jsonObj.get(KEY_FCUT).toString());
						// 非必要欄位，塞try-catch
						try {
							resultMap.put(KEY_FNAME, jsonObj.get(KEY_FNAME).toString());
						} catch (Exception e) {
						}
						try {
							resultMap.put(KEY_NOTE, jsonObj.get(KEY_NOTE).toString());
						} catch (Exception e) {
						}
						isFoundId = true;
						break;
					}
				}
				if (!isFoundId) {
					throw new Exception("spec檔無" + VALUE_ID + "對應");
				}
			} catch (JSONException e) {
				e.printStackTrace();
				throw new JSONException(e);
			}
			checkReadFileMap4Spec(resultMap);
		}
		return resultMap;
	}

	private static void checkReadFileMap4ID(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_ID))) {
			throw new Exception("log檔無" + VALUE_ID);
		}
	}

	private static void checkReadFileMap4Log(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_SEND))) {
			throw new Exception("log檔無" + VALUE_SEND);
		} else if (StringUtils.isBlank(m.get(KEY_FILL))) {
			throw new Exception("log檔無" + VALUE_FILL);
		}
	}

	private static void checkReadFileMap4Spec(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_SCUT0))) {
			throw new Exception("spec檔無" + VALUE_SCUT0);
		} else if (StringUtils.isBlank(m.get(KEY_SCUT))) {
			throw new Exception("spec檔無" + VALUE_SCUT);
		} else if (StringUtils.isBlank(m.get(KEY_FCUT0))) {
			throw new Exception("spec檔無" + VALUE_FCUT0);
		} else if (StringUtils.isBlank(m.get(KEY_FCUT))) {
			throw new Exception("spec檔無" + VALUE_FCUT);
		}
	}

	private static void checkMap4Log(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_SEND))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SEND, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_FILL))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FILL, ERRMSG_ISBLANK));
		}
	}

	private static void checkMap4Spec(Map<String, String> m) throws Exception {
		if (StringUtils.isBlank(m.get(KEY_SCUT0))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT0, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_SCUT))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_SCUT, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_FCUT0))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT0, ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(m.get(KEY_FCUT))) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, VALUE_FCUT, ERRMSG_ISBLANK));
		}
	}

	private static Object getJsonValue(Object obj, String key) {
		if (!new JSONObject(obj.toString()).isNull(key)) {
			String objClassType = new JSONObject(obj.toString()).get(key).getClass().getSimpleName();
			if ("String".equals(objClassType)) {
				return new JSONObject(obj.toString()).getString(key);
			} else if ("Integer".equals(objClassType)) {
				return new JSONObject(obj.toString()).getInt(key);
			} else if ("JSONArray".equals(objClassType)) {
				return new JSONObject(obj.toString()).getJSONArray(key);
			} else {
				return new JSONObject(obj.toString()).get(key);
			}
		} else {
			return "";
		}
	}

	private static int subStrLen(String str, int index) throws UnsupportedEncodingException {
		if (str == null) {
			return -1;
		} else if (getGBKLen(str) >= index) {
			int i;
			for (i = 0; i < str.length(); i++) {
				if (getGBKLen(str.substring(0, i)) >= index) {
					break;
				}
			}
			return i;
		} else {
			return -1;
		}
	}

	// 取GBK長度
	public static int getGBKLen(String str) throws UnsupportedEncodingException {
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("轉GBK長度錯誤");
		}
	}

	// 取數字陣列和轉字串
	public static String getIntegerArrayLength2String(String str) {
		Integer cutSize = Integer.valueOf(0);
		try {
			JSONArray ja = new JSONArray(str);
			for (Object obj : ja) {
				cutSize += Integer.parseInt(obj.toString());
			}
			return cutSize.toString();
		} catch (Exception e) {
			return LEN_0;
		}
	}
}
