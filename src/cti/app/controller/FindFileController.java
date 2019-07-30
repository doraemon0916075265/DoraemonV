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
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		setAllProperties();
	}

	/*** 初始化欄位 ***/
	public static void doInitial() {
		clearAllData();
		ffb.setSearchPath(getDesktopRootPath());
		ffb.setByFilenameExtension("[\"*\"]");
		ffb.setByFilenameExtension_Ignore("[\"~*\",\"*.lnk\",\"*.vfl\"]");
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
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		ffb.setResult("");
		setAllProperties();
	}

	public static void findConditionFile() throws Exception {
		ffb.setResult("");
		getAllProperties();
		validateInput_DirectoryPath(jtf_searchPath);
		ffb.setByFilenameExtension(transInput_SimpleArray2String(jtf_byFilenameExtension));
		ffb.setByFilenameExtension_Ignore(transInput_SimpleArray2String(jtf_byFilenameExtension_Ignore));
		validateInput_byModifyDate(jxdp_byModify_greaterThan, jxdp_byModify_lessThan);

		ffb = findConditionFile(ffb);
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		ffb.setSearchPath(jtf_searchPath.getText());
		ffb.setByText(jtf_byText.getText());
		ffb.setTextCaseSensitive(jcb_byTextCaseSensitive.isSelected());
		ffb.setByFilename(jtf_byFilename.getText());
		ffb.setByFilenameExtension(jtf_byFilenameExtension.getText());
		ffb.setByFilenameExtension_Ignore(jtf_byFilenameExtension_Ignore.getText());
		ffb.setByModify_greaterThan(jxdp_byModify_greaterThan.getDate());
		ffb.setByModify_lessThan(jxdp_byModify_lessThan.getDate());
		ffb.setResult(jta_result.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_searchPath.setText(ffb.getSearchPath());
		jtf_byText.setText(ffb.getByText());
		jcb_byTextCaseSensitive.setSelected(ffb.isTextCaseSensitive());
		jtf_byFilename.setText(ffb.getByFilename());
		jtf_byFilenameExtension.setText(ffb.getByFilenameExtension());
		jtf_byFilenameExtension_Ignore.setText(ffb.getByFilenameExtension_Ignore());
		jxdp_byModify_greaterThan.setDate(ffb.getByModify_greaterThan());
		jxdp_byModify_lessThan.setDate(ffb.getByModify_lessThan());
		jta_result.setText(ffb.getResult());
	}

}
