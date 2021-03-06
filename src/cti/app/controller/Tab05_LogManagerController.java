package cti.app.controller;

import cti.app.bean.LogManagerBean;
import cti.app.service.FileManagerService;
import cti.app.view.Tab05_LogManagerView;

public class Tab05_LogManagerController extends Tab05_LogManagerView {

	private static LogManagerBean lmb = new LogManagerBean();

	/*** 初始化欄位 ***/
	public static void formShow() {
		getAllProperties();
		lmb.setResult("");
		lmb.setLogFilePath(FileManagerService.findFileOnDesktopByFileName(FM_FILENAME_LOG));
		lmb.setByExecuteTime("3000");
		setAllProperties();
	}

	/*** 重設 ***/
	public static void doResetData() {
		formShow();
	}

	/*** 清除 ***/
	public static void doClearData() {
		// formShow();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		lmb.setLogFilePath(jtf_logFilePath.getText());
		lmb.setResult(jta_result.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_logFilePath.setText(lmb.getLogFilePath());
		jta_result.setText(lmb.getResult());
	}
}
