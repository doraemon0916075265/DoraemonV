package cti.app.controller;

import cti.app.bean.FindFileBean;
import cti.app.view.Tab03_FindFileView;

public class Tab03_FindFileController extends Tab03_FindFileView {

	private static FindFileBean ffb = new FindFileBean();

	/*** 畫面初始化欄位 ***/
	public static void formShow() {
		clearAllData();
		ffb.setSearchPath(MY_HOME_DIRECTORY);
		ffb.setByFileName_Extension("[\"*\"]");
		ffb.setByFileName_Extension_Ignore("[\"~*\",\"*.lnk\",\"*.vfl\"]");
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		setAllProperties();
	}

	/*** 重設 ***/
	public static void resetData() {
		formShow();
	}

	/*** 清除 ***/
	public static void clearData() {
		getAllProperties();
		ffb.setResultType(0);
		ffb.setByFileText("");
		ffb.setFileText_CaseSensitive(false);
		ffb.setByFileName("");
		ffb.setFileName_CaseSensitive(false);
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		setAllProperties();
	}

	/*** 清除所有 ***/
	private static void clearAllData() {
		getAllProperties();
		ffb.setSearchPath("");
		ffb.setResultType(0);
		ffb.setByFileText("");
		ffb.setFileText_CaseSensitive(false);
		ffb.setByFileName("");
		ffb.setFileName_CaseSensitive(false);
		ffb.setByFileName_Extension("");
		ffb.setByFileName_Extension_Ignore("");
		ffb.setByModify_greaterThan(null);
		ffb.setByModify_lessThan(null);
		ffb.setResult("");
		setAllProperties();
	}

	public static void findConditionFile() throws Exception {
		ffb.setResult("");
		getAllProperties();
		validateInput_DirectoryPath(jtf_searchPath);
		ffb.setByFileName_Extension(transInput_SimpleArray2String(jtf_byFileNameExtension));
		ffb.setByFileName_Extension_Ignore(transInput_SimpleArray2String(jtf_byFileNameExtension_Ignore));
		validateInput_byModifyDate(jxdp_byModify_greaterThan, jxdp_byModify_lessThan);

		ffb = findConditionFile(ffb);
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		ffb.setSearchPath(jtf_searchPath.getText());
		ffb.setResultType(jcb_resultType.getSelectedIndex());
		ffb.setByFileText(jtf_byFileText.getText());
		ffb.setFileText_CaseSensitive(jcb_byFileTextCaseSensitive.isSelected());
		ffb.setByFileName(jtf_byFileName.getText());
		ffb.setFileName_CaseSensitive(jcb_byFileNameCaseSensitive.isSelected());
		ffb.setByFileName_Extension(jtf_byFileNameExtension.getText());
		ffb.setByFileName_Extension_Ignore(jtf_byFileNameExtension_Ignore.getText());
		ffb.setByModify_greaterThan(jxdp_byModify_greaterThan.getDate());
		ffb.setByModify_lessThan(jxdp_byModify_lessThan.getDate());
		ffb.setResult(jta_result.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf_searchPath.setText(ffb.getSearchPath());
		jcb_resultType.setSelectedIndex(ffb.getResultType());
		jtf_byFileText.setText(ffb.getByFileText());
		jcb_byFileTextCaseSensitive.setSelected(ffb.isFileText_CaseSensitive());
		jtf_byFileName.setText(ffb.getByFileName());
		jcb_byFileNameCaseSensitive.setSelected(ffb.isFileName_CaseSensitive());
		jtf_byFileNameExtension.setText(ffb.getByFileName_Extension());
		jtf_byFileNameExtension_Ignore.setText(ffb.getByFileName_Extension_Ignore());
		jxdp_byModify_greaterThan.setDate(ffb.getByModify_greaterThan());
		jxdp_byModify_lessThan.setDate(ffb.getByModify_lessThan());
		jta_result.setText(ffb.getResult());
	}

}
