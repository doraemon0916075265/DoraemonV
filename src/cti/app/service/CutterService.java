package cti.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cti.app.bean.CutterBean;

public class CutterService extends AppService {
	// 標誌
	private static final String FLAG_LOG = "LOG";
	private static final String FLAG_SPEC = "SPEC";

	private static final String SPEC_ID = "id";
	private static final String SPEC_SCUT0 = "s_cut0";
	private static final String SPEC_SCUT = "s_cut";
	private static final String SPEC_FCUT0 = "f_cut0";
	private static final String SPEC_FCUT = "f_cut";
	private static final String SPEC_NOTE = "note";
	private static final String SPEC_OWNER = "owner";
	// 隱藏欄位
	private static final String SPEC_CNAME = "cname";
	private static final String SPEC_SNAME0 = "s_name0";
	private static final String SPEC_SNAME = "s_name";
	private static final String SPEC_FNAME0 = "f_name0";
	private static final String SPEC_FNAME = "f_name";

	// 正規表示法
	private static final String PARAM_TGID = "電文ID:";
	private static final String REGEXP_ID1 = "畫面名稱:" + REGEXP_FORALL;
	private static final String REGEXP_ID2 = PARAM_TGID + REGEXP_FORALL;
	private static final String REGEXP_SEND1 = "送出:" + REGEXP_FORALL;
	private static final String REGEXP_SEND2 = REGEXP_FORALL + "送出";
	private static final String REGEXP_FILL1 = "收到:" + REGEXP_FORALL;
	private static final String REGEXP_FILL2 = REGEXP_FORALL + "收到";

	private static final String FILENAME_RESULT = "result";

	/*** 取得預設匯出檔案目錄 ***/
	public String getExportFilePath(String name) {
		return getDesktopRootPath() + File.separator + FILENAME_RESULT + (StringUtils.isBlank(name) ? "" : "_" + name) + FILENAME_EXTENSION_CSV;
	}

	/*** 讀檔 ***/
	public CutterBean readFileInfo(CutterBean cb) throws Exception {
		String pathLog = cb.getLogFilePath();
		String pathSpec = cb.getSpecFilePath();
		/*** 讀log檔 ***/
		try (BufferedReader brLog = new BufferedReader(new InputStreamReader(new FileInputStream(pathLog), getFileEncoding(pathLog)));) {
			String line;
			boolean isSend = false;
			boolean isFill = false;
			while ((line = brLog.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				String lineU = line.toUpperCase();
				if (lineU.matches(REGEXP_ID1)) {
					cb.setLogInfo_ID(Pattern.compile("[\\W]").matcher(lineU).replaceAll(""));
				} else if (lineU.matches(REGEXP_ID2)) {
					cb.setLogInfo_ID(lineU.replace(PARAM_TGID, ""));
				}
				if (isSend) {
					cb.setLogInfo_send(lineU);
				}
				if (isFill) {
					cb.setLogInfo_fill(lineU);
				}
				isSend = false;
				isFill = false;
				if (lineU.matches(REGEXP_SEND1) || lineU.matches(REGEXP_SEND2)) {
					isSend = true;
				}
				if (lineU.matches(REGEXP_FILL1) || lineU.matches(REGEXP_FILL2)) {
					isFill = true;
				}
			}

			validFileContent(cb, FLAG_LOG);
		}

		/*** 讀spec檔 ***/
		try (BufferedReader brSpec = new BufferedReader(new InputStreamReader(new FileInputStream(pathSpec), getFileEncoding(pathSpec)));) {
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
					if (getJsonValue(jsonArr.get(i), SPEC_ID).equals(cb.getLogInfo_ID())) {
						JSONObject jsonObj = new JSONObject(jsonArr.get(i).toString());
						// 必要欄位，不塞try-catch
						cb.setSpecSendCut0(jsonObj.get(SPEC_SCUT0).toString());
						cb.setSpecSendCut(jsonObj.get(SPEC_SCUT).toString());
						cb.setSpecFillCut0(jsonObj.get(SPEC_FCUT0).toString());
						cb.setSpecFillCut(jsonObj.get(SPEC_FCUT).toString());
						// 非必要欄位，塞try-catch
						try {
							cb.setSpecFillName0(jsonObj.get(SPEC_FNAME0).toString());
						} catch (Exception e) {
							cb.setSpecFillName0("");
						}
						try {
							cb.setSpecFillName(jsonObj.get(SPEC_FNAME).toString());
						} catch (Exception e) {
							cb.setSpecFillName("");
						}
						// 隱藏欄位，非必要欄位，塞try-catch
						String note = "";
						try {
							cb.setHidden_cname(jsonObj.get(SPEC_CNAME).toString());
							note += StringUtils.isNotBlank(jsonObj.get(SPEC_CNAME).toString()) ? "名稱：" + jsonObj.get(SPEC_CNAME).toString() + "," : "";
						} catch (Exception e) {
							cb.setHidden_cname("");
						}
						try {
							cb.setHidden_sname0(vaildObject_Array2String(jsonObj.get(SPEC_SNAME0)));
						} catch (Exception e) {
							cb.setHidden_sname0(INIT_JSONARRRAY);
						}
						try {
							cb.setHidden_sname(vaildObject_Array2String(jsonObj.get(SPEC_SNAME)));
						} catch (Exception e) {
							cb.setHidden_sname(INIT_JSONARRRAY);
						}
						try {
							cb.setHidden_fname0(vaildObject_Array2String(jsonObj.get(SPEC_FNAME0)));
						} catch (Exception e) {
							cb.setHidden_fname0(INIT_JSONARRRAY);
						}
						try {
							cb.setHidden_fname(vaildObject_Array2String(jsonObj.get(SPEC_FNAME)));
						} catch (Exception e) {
							cb.setHidden_fname(INIT_JSONARRRAY);
						}
						try {
							note += StringUtils.isNotBlank(jsonObj.get(SPEC_OWNER).toString()) ? "負責人：" + jsonObj.get(SPEC_OWNER).toString() + "," : "";
						} catch (Exception e) {

						}
						// note最後塞
						try {
							note += "note：" + jsonObj.get(SPEC_NOTE).toString();
							cb.setSpecInfo_note(note);
						} catch (Exception e) {
							cb.setSpecInfo_note(note + "note：");
						}

						isFoundId = true;
						break;
					}
				}
				if (!isFoundId) {
					throw new Exception("spec檔無 電文id 對應");
				}
			} catch (JSONException e) {
				e.printStackTrace();
				throw new JSONException(e);
			}
			validFileContent(cb, FLAG_SPEC);
		}
		cb.setExportFilePath(getExportFilePath(cb.getLogInfo_ID()));
		return cb;
	}

	// 驗證物件為陣列並轉換成字串
	private String vaildObject_Array2String(Object object) {
		return new JSONArray(object.toString()).toString();
	}

	/*** 主要切電文 ***/
	public String cutterPro(String telegram, JSONArray cut0, JSONArray cut, JSONArray name0, JSONArray name) throws Exception {
		int cutIndex = 0;
		StringBuffer sb = new StringBuffer();
		int gbkLen = getGBKLen(telegram);

		sb.append(addTgHeader(name0));// 上行表頭

		for (Object obj : cut0) {
			Integer cutSize = Integer.parseInt(obj.toString());
			sb.append(SIGN_EQUAL_DBQUOTES + telegram.substring(subStrLen(telegram, cutIndex), subStrLen(telegram, cutIndex += cutSize)) + SIGN_DBQUOTES_COMMA);
			gbkLen -= cutSize;
		}

		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append(addTgHeader(name));// 下行表頭

		try {
			while (gbkLen > cut.length()) {
				for (Object obj : cut) {
					Integer cutSize = Integer.parseInt(obj.toString());
					sb.append(SIGN_EQUAL_DBQUOTES + telegram.substring(subStrLen(telegram, cutIndex), subStrLen(telegram, cutIndex += cutSize)) + SIGN_DBQUOTES_COMMA);
					gbkLen -= cutSize;
				}
				sb.append(System.lineSeparator());
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return sb.toString();
	}

	// 組表頭顯示
	private String addTgHeader(JSONArray arr) {
		StringBuffer sb = new StringBuffer();
		if (!arr.isEmpty()) {
			for (Object obj : arr) {
				sb.append(SIGN_EQUAL_DBQUOTES + obj.toString() + SIGN_DBQUOTES_COMMA);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	/*** 切電文(舊方法) ***/
	public String cutterPro(String text, boolean isAddEqual, JSONArray arr_cut0, JSONArray arr_cut) throws UnsupportedEncodingException {
		int cutIndex = 0;
		StringBuffer sb = new StringBuffer();
		int gbkLen = getGBKLen(text);
		for (int i = 0; i < arr_cut0.length(); i++) {
			Integer cutSize = Integer.parseInt(arr_cut0.get(i).toString());
			String row;
			if (isAddEqual) {
				row = SIGN_EQUAL + SIGN_DBQUOTES;
			} else {
				row = SIGN_DBQUOTES;
			}
			row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DBQUOTES;
			row = (i < arr_cut0.length() - 1) ? row += SIGN_COMMA : row;
			sb.append(row);
			gbkLen -= cutSize;
		}
		while (gbkLen > arr_cut.length()) {
			sb.append(System.lineSeparator());
			for (int i = 0; i < arr_cut.length(); i++) {
				Integer cutSize = Integer.parseInt(arr_cut.get(i).toString());
				String row;
				if (isAddEqual) {
					row = SIGN_EQUAL + SIGN_DBQUOTES;
				} else {
					row = SIGN_DBQUOTES;
				}
				row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DBQUOTES;
				row = (i < arr_cut.length() - 1) ? row += SIGN_COMMA : row;
				sb.append(row);
				gbkLen -= cutSize;
			}
		}
		return sb.toString();
	}

	private int subStrLen(String str, int index) throws UnsupportedEncodingException {
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

	/*** 匯出檔案 ***/
	public void exportFile(CutterBean cb) throws Exception {
		List<String> contents = new ArrayList<>();
		contents.add(String.format(FORMAT_EXPORTFILE_SUBTITLE, "上行電文結果"));
		contents.add(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultS()));
		contents.add(System.lineSeparator());
		contents.add(String.format(FORMAT_EXPORTFILE_SUBTITLE, "下行電文結果"));
		contents.add(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultF()));
		exportFile(cb.getExportFilePath(), contents);
	}

	/*** 驗證檔案內容 ***/
	private void validFileContent(CutterBean cb, String type) throws Exception {
		String msg;
		if (FLAG_LOG.equals(type)) {
			msg = "log檔無";
			if (StringUtils.isBlank(cb.getLogInfo_ID())) {
				throw new Exception(msg + "電文id");
			} else if (StringUtils.isBlank(cb.getLogInfo_send())) {
				throw new Exception(msg + "上行電文");
			} else if (StringUtils.isBlank(cb.getLogInfo_fill())) {
				throw new Exception(msg + "下行電文");
			}
		} else if (FLAG_SPEC.equals(type)) {
			msg = "spec檔無";
			if (StringUtils.isBlank(cb.getSpecSendCut0())) {
				throw new Exception(msg + "1上行電文陣列(頭)");
			} else if (StringUtils.isBlank(cb.getSpecSendCut())) {
				throw new Exception(msg + "上行電文陣列(主要)");
			} else if (StringUtils.isBlank(cb.getSpecFillCut0())) {
				throw new Exception(msg + "下行電文陣列(頭)");
			} else if (StringUtils.isBlank(cb.getSpecFillCut())) {
				throw new Exception(msg + "下行電文陣列(主要)");
			}
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

}
