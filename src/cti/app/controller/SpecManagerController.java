package cti.app.controller;

import cti.app.bean.SpecManagerBean;
import cti.app.service.FileManagerService;
import cti.app.view.SpecManagerView;

public class SpecManagerController extends SpecManagerView {

	private static SpecManagerBean smb = new SpecManagerBean();

	/*** 初始化欄位 ***/
	public static void formShow() {
		getAllProperties();
		smb.setSpecFilePath(FileManagerService.findFileOnDesktopByFileName(FILENAME_SPEC));
		smb.setSpecID(genJCB4SpecID(smb.getSpecFilePath()).get(0));
		smb.setResult("");
		setAllProperties();
	}

	/*** 重設 ***/
	public static void resetData() {
		formShow();
	}

	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		smb.setResult("");
		setAllProperties();
	}

	/*** 讀檔 ***/
	public static void readFile() throws Exception {
		getAllProperties();
		validateInput_FilePath(jtf_specFilePath);
		smb = readJsonSpecInfo(smb);
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		smb.setSpecFilePath(jtf_specFilePath.getText());
		smb.setResult(jta_result.getText());
		smb.setSpecID(getPulldownItem(jcb_specID));
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_specFilePath.setText(smb.getSpecFilePath());
		jta_result.setText(smb.getResult());
		jcb_specID.setSelectedItem(smb.getSpecID());
	}

}
