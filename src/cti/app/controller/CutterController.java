package cti.app.controller;

import cti.app.bean.CutterBean;
import cti.app.view.CutterView;

public class CutterController extends CutterView {
	private static CutterBean cb = new CutterBean();

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
		// 隱藏欄位
		cb.setHidden_cname("");
		cb.setHidden_sname("");
		cb.setHidden_sname("");
		cb.setHidden_fname("");
		cb.setHidden_fname("");
		setAllProperties();
	}

	/*** 清除所有 ***/
	public static void clearAllData() {
		getAllProperties();
		cb.setLogFilePath("");
		cb.setSpecFilePath("");
		cb.setLogInfo_send("");
		cb.setLogInfo_fill("");
		cb.setSpecSendCut0("");
		cb.setSpecSendCut("");
		cb.setSpecFillCut0("");
		cb.setSpecFillCut("");
		cb.setLogInfo_ID("");
		cb.setSpecInfo_note("");
		cb.setExportFilePath("");
		cb.setAddEqual(true);
		cb.setResultS("");
		cb.setResultF("");
		// 隱藏欄位
		cb.setHidden_cname("");
		cb.setHidden_sname("");
		cb.setHidden_sname("");
		cb.setHidden_fname("");
		cb.setHidden_fname("");
		setAllProperties();
	}

	/*** 重設 ***/
	public static void resetData() {
		clearAllData();
		doInitial();
	}

	/*** 第一次開啟視窗，初始化欄位 ***/
	public static void doInitial() {
		getAllProperties();
		cb.setExportFilePath(cs.getExportFilePath());
		cb.setLogFilePath(cs.findFilePathByRootPath(cs.getDesktopRootPath(), FILENAME_LOG));
		cb.setSpecFilePath(cs.findFilePathByRootPath(cs.getDesktopRootPath(), FILENAME_SPEC));
		setAllProperties();
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
		// 隱藏欄位
		cb.setHidden_cname(hidden_cname.getText());
		cb.setHidden_sname(hidden_sname0.getText());
		cb.setHidden_sname(hidden_sname.getText());
		cb.setHidden_fname(hidden_fname0.getText());
		cb.setHidden_fname(hidden_fname.getText());
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
		// 隱藏欄位
		hidden_cname.setText(cb.getHidden_cname());
		hidden_sname0.setText(cb.getHidden_sname0());
		hidden_sname.setText(cb.getHidden_sname());
		hidden_fname.setText(cb.getHidden_fname());
		hidden_fname0.setText(cb.getHidden_fname0());
	}

}
