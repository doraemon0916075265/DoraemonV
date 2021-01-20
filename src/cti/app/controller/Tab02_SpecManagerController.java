package cti.app.controller;

import cti.app.bean.SpecManagerBean;
import cti.app.service.FileManagerService;
import cti.app.view.Tab02_SpecManagerView;

public class Tab02_SpecManagerController extends Tab02_SpecManagerView {

	private static SpecManagerBean smb = new SpecManagerBean();

	/*** 初始化欄位 ***/
	public static void doFormShow() {
		getAllProperties();
		smb.setSpecFilePath(FileManagerService.findFileOnDesktopByFileName(FM_FILENAME_SPEC));
		smb.setSpecID(genJCB4SpecID(smb.getSpecFilePath()).get(0));
		smb.setResult("");
		setAllProperties();
	}

	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		smb.setResult("");
		setAllProperties();
	}

	/*** 讀檔 ***/
	public static void doReadFile() throws Exception {
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
