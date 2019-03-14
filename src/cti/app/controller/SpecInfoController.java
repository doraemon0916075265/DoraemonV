package cti.app.controller;

import cti.app.bean.SpecInfoBean;
import cti.app.view.SpecInfoView;

public class SpecInfoController extends SpecInfoView {
	private static SpecInfoBean sib = new SpecInfoBean();

	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		sib.setResult("");
		setAllProperties();
	}

	/*** 初始化欄位 ***/
	public static void doInitial() {
		getAllProperties();
		sib.setSpecFilePath(findFilePathByRootPath(getDesktopRootPath(), FILENAME_SPEC));
		sib.setSpecID(null);
		sib.setResult("");
		setAllProperties();
		
		genPulldownMenu(jcb_specID, genJCB4SpecID(jtf_specFilePath.getText()));
	}

	/*** 讀檔 ***/
	public static void readFile() throws Exception {
		getAllProperties();
		validateInput_FilePath(jtf_specFilePath);
		sib = readFileInfo(sib);
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		sib.setSpecFilePath(jtf_specFilePath.getText());
		sib.setResult(jta_result.getText());

		if (jcb_specID.getSelectedItem() != null) {
			sib.setSpecID(jcb_specID.getSelectedItem().toString());
		}
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_specFilePath.setText(sib.getSpecFilePath());
		jta_result.setText(sib.getResult());
	}
}
