package cti.app.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cti.app.bean.CutterBean;
import cti.app.service.AppService;
import cti.app.view.CutterView;

public class CutterHandler extends CutterView {
	private static CutterBean cb = new CutterBean();
	private static AppService as = new AppService();


	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		cb.setLogInfo_send("");
		cb.setLogInfo_fill("");
		cb.setSpecSendCut0("");
		cb.setSpecSendCut("");
		cb.setSpecFillCut0("");
		cb.setSpecFillCut("");
		cb.setLogInfo_ID("");
		cb.setSpecInfo_note("");
		setAllProperties();
	}

	/*** 讀檔 ***/
	public static void readFile() throws Exception {
		clearData();
		getAllProperties();
		valid4ReadFile();

		String pathLog = cb.getLogFilePath();
		String pathSpec = cb.getSpecFilePath();
		String encodeLog = AppHandler.getFileEncoding(pathLog);
		String encodeSpec = AppHandler.getFileEncoding(pathSpec);

		// 預先判斷是否皆可正常讀檔
		try (BufferedReader brLog = new BufferedReader(new InputStreamReader(new FileInputStream(pathLog), encodeLog));) {
		}
		try (BufferedReader brSpec = new BufferedReader(new InputStreamReader(new FileInputStream(pathSpec), encodeSpec));) {
		}

		/*** 讀log檔 ***/
		try (BufferedReader brLog = new BufferedReader(new InputStreamReader(new FileInputStream(pathLog), encodeLog));) {
			String line;
			boolean isSend = false;
			boolean isFill = false;
			while ((line = brLog.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				String lineU = line.toUpperCase();
				if (lineU.matches(REGEXP_ID1)) {
					cb.setLogInfo_ID(Pattern.compile(REGEXP_TGID).matcher(lineU).replaceAll(""));
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

			valid4ReadFileLog();
		}

		/*** 讀spec檔 ***/
		try (BufferedReader brSpec = new BufferedReader(new InputStreamReader(new FileInputStream(pathSpec), encodeSpec));) {
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
						try {
							cb.setSpecInfo_note(jsonObj.get(SPEC_NOTE).toString());
						} catch (Exception e) {
							cb.setSpecInfo_note("");
						}
						isFoundId = true;
						break;
					}
				}
				if (!isFoundId) {
					throw new Exception("spec檔無" + jtf_logInfo_ID.getName() + "對應");
				}
			} catch (JSONException e) {
				e.printStackTrace();
				throw new JSONException(e);
			}
			valid4ReadFileSpec();
		}
		setAllProperties();
	}

	/*** 解析 ***/
	public static void analysis() throws Exception {
		getAllProperties();
		valid4Analysis();
		cb.setResultS(cutterPro(cb.getLogInfo_send(), new JSONArray(cb.getSpecSendCut0()), new JSONArray(cb.getSpecSendCut())));
		cb.setResultF(cutterPro(cb.getLogInfo_fill(), new JSONArray(cb.getSpecFillCut0()), new JSONArray(cb.getSpecFillCut())));
		setAllProperties();
	}

	private static String cutterPro(String text, JSONArray arr_cut0, JSONArray arr_cut) throws UnsupportedEncodingException {
		int cutIndex = 0;
		StringBuffer sb = new StringBuffer();
		int gbkLen = as.getGBKLen(text);
		for (int i = 0; i < arr_cut0.length(); i++) {
			Integer cutSize = Integer.parseInt(arr_cut0.get(i).toString());
			String row;
			if (cb.isAddEqual()) {
				row = SIGN_EQUAL + SIGN_DOUBLEQUOTES;
			} else {
				row = SIGN_DOUBLEQUOTES;
			}
			row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DOUBLEQUOTES;
			row = (i < arr_cut0.length() - 1) ? row += SIGN_COMMA : row;
			sb.append(row);
			gbkLen -= cutSize;
		}
		while (gbkLen > arr_cut.length()) {
			sb.append(System.lineSeparator());
			for (int i = 0; i < arr_cut.length(); i++) {
				Integer cutSize = Integer.parseInt(arr_cut.get(i).toString());
				String row;
				if (cb.isAddEqual()) {
					row = SIGN_EQUAL + SIGN_DOUBLEQUOTES;
				} else {
					row = SIGN_DOUBLEQUOTES;
				}
				row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DOUBLEQUOTES;
				row = (i < arr_cut.length() - 1) ? row += SIGN_COMMA : row;
				sb.append(row);
				gbkLen -= cutSize;
			}
		}
		return sb.toString();
	}

	private static void valid4ReadFile() throws Exception {
		if (StringUtils.isBlank(cb.getLogFilePath())) {
			throw new Exception(jtf_logFilePath.getName() + ERRMSG_ISBLANK);
		} else if (StringUtils.isBlank(cb.getSpecFilePath())) {
			throw new Exception(jtf_specFilePath.getName() + ERRMSG_ISBLANK);
		}
	}

	private static void valid4ReadFileLog() throws Exception {
		String msg = "log檔無";
		if (StringUtils.isBlank(cb.getLogInfo_ID())) {
			throw new Exception(msg + jtf_logInfo_ID.getName());
		} else if (StringUtils.isBlank(cb.getLogInfo_send())) {
			throw new Exception(msg + jtf_logInfo_send.getName());
		} else if (StringUtils.isBlank(cb.getLogInfo_fill())) {
			throw new Exception(msg + jtf_logInfo_fill.getName());
		}
	}

	private static void valid4ReadFileSpec() throws Exception {
		String msg = "spec檔無";
		if (StringUtils.isBlank(cb.getSpecSendCut0())) {
			throw new Exception(msg + jtf_specSendCut0.getName());
		} else if (StringUtils.isBlank(cb.getSpecSendCut())) {
			throw new Exception(msg + jtf_specSendCut.getName());
		} else if (StringUtils.isBlank(cb.getSpecFillCut0())) {
			throw new Exception(msg + jtf_specFillCut0.getName());
		} else if (StringUtils.isBlank(cb.getSpecFillCut())) {
			throw new Exception(msg + jtf_specFillCut.getName());
		}
	}

	/*** 匯出檔案 ***/
	public static void exportFile() throws Exception {
		getAllProperties();
		valid4ExportFile();

		File f;
		File fp;
		try {
			f = new File(cb.getExportFilePath());
			fp = new File(f.getParent());
		} catch (Exception e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_exportFilePath.getName(), ERRMSG_FORMAT));
		}
		boolean isValidPath = false;
		// 判斷副檔名
		for (String filenExtension : REGEXP_FILEEXTEN_EXPORT) {
			if (f.getName().toUpperCase().matches(filenExtension)) {
				isValidPath = true;
				break;
			}
		}
		if (isValidPath) {
			if (!fp.exists()) {
				fp.mkdirs();
			}
		} else {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_exportFilePath.getName(), ERRMSG_FORMAT));
		}

		try (FileWriter fw = new FileWriter(f.getAbsolutePath())) {
			fw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			fw.write(String.format(FORMAT_EXPORTFILE_SUBTITLE, jta_resultS.getName()));
			fw.write(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultS()));
			fw.write(System.lineSeparator());
			fw.write(String.format(FORMAT_EXPORTFILE_SUBTITLE, jta_resultF.getName()));
			fw.write(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultF()));
			fw.flush();
		} catch (Exception e) {
			throw new Exception();
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
		} else if (as.getGBKLen(str) >= index) {
			int i;
			for (i = 0; i < str.length(); i++) {
				if (as.getGBKLen(str.substring(0, i)) >= index) {
					break;
				}
			}
			return i;
		} else {
			return -1;
		}
	}

	/*** 取得所有欄位值裝在bean ***/
	private static void getAllProperties() {
		cb.setLogFilePath(jtf_logFilePath.getText());
		cb.setSpecFilePath(jtf_specFilePath.getText());
		cb.setLogInfo_send(jtf_logInfo_send.getText());
		cb.setLogInfo_fill(jtf_logInfo_fill.getText());
		cb.setSpecSendCut0(jtf_specSendCut0.getText());
		cb.setSpecSendCut(jtf_specSendCut.getText());
		cb.setSpecFillCut0(jtf_specFillCut0.getText());
		cb.setSpecFillCut(jtf_specFillCut.getText());
		cb.setLogInfo_ID(jtf_logInfo_ID.getText());
		cb.setSpecInfo_note(jtf_specInfo_note.getText());
		cb.setExportFilePath(jtf_exportFilePath.getText());
		cb.setAddEqual(jcb_isAddEqual.isSelected());
		cb.setResultS(jta_resultS.getText());
		cb.setResultF(jta_resultF.getText());
	}

	/*** 從bean中取出所有欄位值 ***/
	private static void setAllProperties() {
		jtf_logFilePath.setText(cb.getLogFilePath());
		jtf_specFilePath.setText(cb.getSpecFilePath());
		jtf_logInfo_send.setText(cb.getLogInfo_send());
		jtf_logInfo_fill.setText(cb.getLogInfo_fill());
		jtf_specSendCut0.setText(cb.getSpecSendCut0());
		jtf_specSendCut.setText(cb.getSpecSendCut());
		jtf_specFillCut0.setText(cb.getSpecFillCut0());
		jtf_specFillCut.setText(cb.getSpecFillCut());
		jtf_logInfo_ID.setText(cb.getLogInfo_ID());
		jtf_specInfo_note.setText(cb.getSpecInfo_note());
		jtf_exportFilePath.setText(cb.getExportFilePath());
		jcb_isAddEqual.setSelected(cb.isAddEqual());
		jta_resultS.setText(cb.getResultS());
		jta_resultF.setText(cb.getResultF());
	}

	/*** 欄位判斷for解析 ***/
	private static void valid4Analysis() throws Exception {
		if (StringUtils.isBlank(cb.getLogInfo_send())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_logInfo_send.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getLogInfo_fill())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_logInfo_fill.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getSpecSendCut0())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut0.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getSpecSendCut())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getSpecFillCut0())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut0.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getSpecFillCut())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut.getName(), ERRMSG_ISBLANK));
		}

		JSONArray ja_sCut0;
		JSONArray ja_sCut;
		JSONArray ja_fCut0;
		JSONArray ja_fCut;

		try {
			ja_sCut0 = new JSONArray(cb.getSpecSendCut0());
			if (ja_sCut0.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut0.getName(), ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut0.getName(), ERRMSG_FORMAT));
		}
		cb.setSpecSendCut0(ja_sCut0.toString());

		try {
			ja_sCut = new JSONArray(cb.getSpecSendCut());
			if (ja_sCut.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut.getName(), ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specSendCut.getName(), ERRMSG_FORMAT));
		}
		cb.setSpecSendCut(ja_sCut.toString());

		try {
			ja_fCut0 = new JSONArray(cb.getSpecFillCut0());
			if (ja_fCut0.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut0.getName(), ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut0.getName(), ERRMSG_FORMAT));
		}
		cb.setSpecFillCut0(ja_fCut0.toString());

		try {
			ja_fCut = new JSONArray(cb.getSpecFillCut());
			if (ja_fCut.isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut.getName(), ERRMSG_ARRAYISEMPTY));
			}
		} catch (JSONException e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtf_specFillCut.getName(), ERRMSG_FORMAT));
		}
		cb.setSpecFillCut(ja_fCut.toString());
	}

	/*** 欄位判斷for匯出 ***/
	private static void valid4ExportFile() {
		if (StringUtils.isBlank(cb.getExportFilePath())) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, jtf_exportFilePath.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getResultS())) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, jta_resultS.getName(), ERRMSG_ISBLANK));
		} else if (StringUtils.isBlank(cb.getResultF())) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, jta_resultF.getName(), ERRMSG_ISBLANK));
		}
	}
}
