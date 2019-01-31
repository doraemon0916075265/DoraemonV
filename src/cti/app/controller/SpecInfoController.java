package cti.app.controller;

import cti.app.bean.SpecInfoBean;
import cti.app.view.SpecInfoView;

public class SpecInfoController extends SpecInfoView {
	private static SpecInfoBean tb = new SpecInfoBean();

	public static void doInitial() {
		getAllProperties();
		tb.setSpecFilePath(findFilePathByRootPath(getDesktopRootPath(), FILENAME_SPEC));
		tb.setResult("");
		setAllProperties();
	}

	public static void readFile() {
		getAllProperties();
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		tb.setSpecFilePath(jtf_specFilePath.getText());
		tb.setResult(jta_result.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_specFilePath.setText(tb.getSpecFilePath());
		jta_result.setText(tb.getResult());
	}
}
