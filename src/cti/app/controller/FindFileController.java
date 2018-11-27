package cti.app.controller;

import cti.app.bean.FindFileBean;
import cti.app.view.FindFileView;

public class FindFileController extends FindFileView {
	private static FindFileBean ffb = new FindFileBean();

	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		ffb.setByText("");
		ffb.setByFilename("");
		ffb.setByModify_greaterThan("");
		ffb.setByModify_lessThan("");
		setAllProperties();
	}

	/*** 初始化欄位 ***/
	public static void doInitial() {
		clearAllData();
		ffb.setSearchPath(fs.getDesktopRootPath());
		ffb.setByFilenameExtension("[*]");
		ffb.setByFilenameExtension_Ignore("[~*,*.vfl]");
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		setAllProperties();
	}

	/*** 清除所有 ***/
	private static void clearAllData() {
		getAllProperties();
		ffb.setSearchPath("");
		ffb.setByText("");
		ffb.setByFilename("");
		ffb.setByFilenameExtension("");
		ffb.setByFilenameExtension_Ignore("");
		ffb.setByModify_greaterThan("");
		ffb.setByModify_lessThan("");
		ffb.setResult("");
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		ffb.setSearchPath(jtf_searchPath.getText());
		ffb.setByText(jtf_byText.getText());
		ffb.setByFilename(jtf_byFilename.getText());
		ffb.setByFilenameExtension(jtf_byFilenameExtension.getText());
		ffb.setByFilenameExtension_Ignore(jtf_byFilenameExtension_Ignore.getText());
		// ffb.setByModify_greaterThan(APPDATE_SDF.format(jxdp_byModify_greaterThan.getDate()));
		// ffb.setByModify_lessThan(APPDATE_SDF.format(jxdp_byModify_lessThan.getDate()));
		ffb.setResult(jta_result.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_searchPath.setText(ffb.getSearchPath());
		jtf_byText.setText(ffb.getByText());
		jtf_byFilename.setText(ffb.getByFilename());
		jtf_byFilenameExtension.setText(ffb.getByFilenameExtension());
		jtf_byFilenameExtension_Ignore.setText(ffb.getByFilenameExtension_Ignore());
		// ffb.setByModify_greaterThan(APPDATE_SDF.format(jxdp_byModify_greaterThan.getDate()));
		// ffb.setByModify_lessThan(APPDATE_SDF.format(jxdp_byModify_lessThan.getDate()));
		jta_result.setText(ffb.getResult());
	}
}
