package cti.app.controller;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import cti.app.bean.CutterBean;
import cti.app.view.CutterView;

public class CutterController extends CutterView {
	private static CutterBean cb = new CutterBean();

	/*** 清除 ***/
	public static void clearData(String type) {
		getAllProperties();
		if (CLEAR_ALL.equals(type)) {
			cb.setLogFilePath("");
			cb.setSpecFilePath("");
			cb.setExportFilePath("");
			cb.setResultS("");
			cb.setResultF("");
		}
		cb.setLogInfo_send("");
		cb.setLogInfo_fill("");
		cb.setSpecSendCut0("");
		cb.setSpecSendCut("");
		cb.setSpecFillCut0("");
		cb.setSpecFillCut("");
		cb.setLogInfo_ID("");
		cb.setSpecInfo_note("");
		// 隱藏欄位
		cb.setHidden_cname("");
		cb.setHidden_scname("");
		cb.setHidden_scname0("");
		cb.setHidden_fcname("");
		cb.setHidden_fcname0("");
		cb.setHidden_sename("");
		cb.setHidden_sename0("");
		cb.setHidden_fename("");
		cb.setHidden_fename0("");
		setAllProperties();
	}

	/*** 初始化欄位 ***/
	public static void doInitial() {
		clearData(CLEAR_ALL);
		cb.setExportFilePath(getExportFilePath(""));
		String desktopRootPath = getDesktopRootPath();
		cb.setLogFilePath(findFilePathByRootPath(desktopRootPath, FILENAME_LOG));
		cb.setSpecFilePath(findFilePathByRootPath(desktopRootPath, FILENAME_SPEC));
		setAllProperties();
	}

	/*** 讀檔 ***/
	public static void readFile() throws Exception {
		clearData(null);
		getAllProperties();

		validateInput_FilePath(jtf_logFilePath);
		validateInput_FilePath(jtf_specFilePath);
		cb = readFileInfo(cb);

		setAllProperties();
	}

	/*** 解析 ***/
	public static void analysis() throws Exception {
		getAllProperties();

		validateInput_Text(jtf_logInfo_send);
		validateInput_Text(jtf_logInfo_fill);
		cb.setSpecSendCut0(transInput_Array2String(jtf_specSendCut0));
		cb.setSpecSendCut(transInput_Array2String(jtf_specSendCut));
		cb.setSpecFillCut0(transInput_Array2String(jtf_specFillCut0));
		cb.setSpecFillCut(transInput_Array2String(jtf_specFillCut));

		List<JSONArray> shead0 = Arrays.asList(new JSONArray(cb.getHidden_scname0()), new JSONArray(cb.getHidden_sename0()));
		List<JSONArray> shead = Arrays.asList(new JSONArray(cb.getHidden_scname()), new JSONArray(cb.getHidden_sename()));
		List<JSONArray> fhead0 = Arrays.asList(new JSONArray(cb.getHidden_fcname0()), new JSONArray(cb.getHidden_fename0()));
		List<JSONArray> fhead = Arrays.asList(new JSONArray(cb.getHidden_fcname()), new JSONArray(cb.getHidden_fename()));

		cb.setResultS(cutterPro(cb.getLogInfo_send(), new JSONArray(cb.getSpecSendCut0()), new JSONArray(cb.getSpecSendCut()), shead0, shead));
		cb.setResultF(cutterPro(cb.getLogInfo_fill(), new JSONArray(cb.getSpecFillCut0()), new JSONArray(cb.getSpecFillCut()), fhead0, fhead));

		setAllProperties();
	}

	/*** 匯出 ***/
	public static void export() throws Exception {
		getAllProperties();

		validateInput_ExportPath(jtf_exportFilePath, Arrays.asList("TXT", "LOG", "CSV"));
		validateInput_Text(jta_resultS);
		validateInput_Text(jta_resultF);

		exportFile(cb);
	}

	/*** 說明書 ***/
	public static StringBuffer guidebook() {
		StringBuffer sb = new StringBuffer();
		int i = 1;
		sb.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "操作說明"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "預設抓取桌面路徑下，檔名為log.txt和spec.json檔"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "輸入路徑後，可以讀檔"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "讀檔後，可以解析"));
		sb.append(System.lineSeparator());
		i = 1;
		sb.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "功能說明"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "重設：等同於重新啟動"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "清除：清除''檔案內容區''欄位"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "讀檔：讀取''路徑欄''的檔案，顯示於''檔案內容區''"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "解析：依''檔案內容區''的資訊，分析電文，顯示於下方''結果區''"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "匯出：將下方''結果區''的資訊，按路徑匯出txt"));
		sb.append(System.lineSeparator());
		i = 1;
		sb.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "其他操作說明"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "藍色字型的欄位與log檔有關"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "洋紅色字型的欄位與spec檔有關"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "點兩下輸入框，可以複製內容"));
		sb.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "最底部是''系統訊息顯示區''：[時間 主要訊息] 詳細訊息"));
		return sb;
	}

	/*** 從欄位中取出所有值塞入bean ***/
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
		cb.setResultS(jta_resultS.getText());
		cb.setResultF(jta_resultF.getText());
		// 隱藏欄位
		cb.setHidden_cname(hidden_cname.getText());
		cb.setHidden_scname(hidden_scname0.getText());
		cb.setHidden_scname(hidden_scname.getText());
		cb.setHidden_fcname(hidden_fcname0.getText());
		cb.setHidden_fcname(hidden_fcname.getText());
		cb.setHidden_sename(hidden_sename0.getText());
		cb.setHidden_sename(hidden_sename.getText());
		cb.setHidden_fename(hidden_fename0.getText());
		cb.setHidden_fename(hidden_fename.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
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
		jta_resultS.setText(cb.getResultS());
		jta_resultF.setText(cb.getResultF());
		// 隱藏欄位
		hidden_cname.setText(cb.getHidden_cname());
		hidden_scname0.setText(cb.getHidden_scname0());
		hidden_scname.setText(cb.getHidden_scname());
		hidden_fcname.setText(cb.getHidden_fcname());
		hidden_fcname0.setText(cb.getHidden_fcname0());
		hidden_sename0.setText(cb.getHidden_sename0());
		hidden_sename.setText(cb.getHidden_sename());
		hidden_fename.setText(cb.getHidden_fename());
		hidden_fename0.setText(cb.getHidden_fename0());
	}

}
