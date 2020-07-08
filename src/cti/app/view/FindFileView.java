package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JComboBoxSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.component.JXDatePickerSimple;
import cti.app.constant.FindFileConstant;
import cti.app.controller.FindFileController;
import cti.app.service.AppTimer;
import cti.app.service.FindFileService;

public class FindFileView extends FindFileConstant {

	private static JPanelSimple jp = new JPanelSimple();
	public static FindFileService fs = new FindFileService();

	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

	private static JLabelSimple jl_searchPath = new JLabelSimple(JL_SEARCHPATH);// 欲查路徑
	protected static JTextFieldSimple jtf_searchPath = new JTextFieldSimple(jl_searchPath);
	protected static JButtonFilePath jb_searchPath = new JButtonFilePath(jtf_searchPath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_searchCondition = new JLabelSimple(JL_SEARCHCONDITION);
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabelSimple jl_resultType = new JLabelSimple(JL_RESULTTYPE); // 結果類型
	protected static JComboBoxSimple<String> jcb_resultType = new JComboBoxSimple<String>(FM_RESULTTYPE_LIST);

	private static JLabelSimple jl_byFileText = new JLabelSimple(JL_BYFILETEXT);// 字串查詢
	protected static JTextFieldSimple jtf_byFileText = new JTextFieldSimple(jl_byFileText);
	private static JLabelSimple jl_byFileTextCaseSensitive = new JLabelSimple(JL_BYFILETEXTCASESENSITIVE);// 敏感字串
	protected static JCheckBox jcb_byFileTextCaseSensitive = new JCheckBox();
	private static JButtonSimple jb_search = new JButtonSimple(BTN_SEARCH);

	private static JLabelSimple jl_byFileName = new JLabelSimple(JL_BYFILENAME);// 檔名查詢
	protected static JTextFieldSimple jtf_byFileName = new JTextFieldSimple(jl_byFileName);
	private static JLabelSimple jl_byFileNameCaseSensitive = new JLabelSimple(JL_BYFILENAMECASESENSITIVE);// 敏感檔名
	protected static JCheckBox jcb_byFileNameCaseSensitive = new JCheckBox();

	private static JLabelSimple jl_byFileNameExtension = new JLabelSimple(JL_BYFILENAMEEXTENSION);
	protected static JTextFieldSimple jtf_byFileNameExtension = new JTextFieldSimple(jl_byFileNameExtension);

	private static JLabelSimple jl_byFileNameExtension_Ignore = new JLabelSimple(JL_BYFILENAMEEXTENSION_IGNORE);
	protected static JTextFieldSimple jtf_byFileNameExtension_Ignore = new JTextFieldSimple(jl_byFileNameExtension_Ignore);

	private static JLabelSimple jl_byModify_greaterThan = new JLabelSimple(JL_BYMODIFY_GREATERTHAN);
	protected static JXDatePickerSimple jxdp_byModify_greaterThan = new JXDatePickerSimple(jl_byModify_greaterThan);
	private static JLabelSimple jl_byModify_lessThan = new JLabelSimple(JL_BYMODIFY_LESSTHAN);
	protected static JXDatePickerSimple jxdp_byModify_lessThan = new JXDatePickerSimple(jl_byModify_lessThan);

	protected static JTextAreaSimple jta_result = new JTextAreaSimple("結果", 12, false);

	public static JPanel createView() {
		setBegin();
		setPosition();
		setComponent();
		setListener();
		setEnd();
		return jp;
	}

	public static void setBegin() {
		jpSub1.setLayout(null);
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 380));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 240));
		// jpSub1.setBorder(new LineBorder(Color.RED));
		// jpSub2.setBorder(new LineBorder(Color.RED));
	}

	private static void setPosition() {
		/*** 上半部，第一區 ***/
		Style.resetRow();
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_searchPath, jtf_searchPath, jb_searchPath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_searchPath, jtf_searchPath, jb_searchPath, jb_resetData));
		/*** 第二區 ***/
		Style.setBounds(Style.MODEL_NULL_JL_NULL_JC_BTN, Arrays.asList(null, null, jl_searchCondition, null, null, jb_clearData, null));
		jpSub1.add(Arrays.asList(jl_searchCondition, jb_clearData));
		/*** 第三區 ***/
		Style.setBounds(Style.MODEL_JL_JC1_NULL_JC2_NULL_JC3_NULL_JC4_JC5_BTN, Arrays.asList(null, jl_resultType, jcb_resultType, null, null, null, null, null, null, null, jb_search, null));
		jpSub1.add(Arrays.asList(jl_resultType, jcb_resultType, jb_search));
		/*** 第四區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_JCB_JC_BTN, Arrays.asList(null, jl_byFileText, jtf_byFileText, null, jl_byFileTextCaseSensitive, jcb_byFileTextCaseSensitive, null, null, null));
		jpSub1.add(Arrays.asList(jl_byFileText, jtf_byFileText, jl_byFileTextCaseSensitive, jcb_byFileTextCaseSensitive));
		/*** 第六區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_JCB_JC_BTN, Arrays.asList(null, jl_byFileName, jtf_byFileName, null, jl_byFileNameCaseSensitive, jcb_byFileNameCaseSensitive, null, null, null));
		jpSub1.add(Arrays.asList(jl_byFileName, jtf_byFileName, jl_byFileNameCaseSensitive, jcb_byFileNameCaseSensitive));
		/*** 第七區 ***/
		Style.setBounds(Style.MODEL_JL1_JC1_NULL_JL2_JC2_JC_BTN, Arrays.asList(null, jl_byFileNameExtension, jtf_byFileNameExtension, null, jl_byFileNameExtension_Ignore, jtf_byFileNameExtension_Ignore, null, null, null));
		jpSub1.add(Arrays.asList(jl_byFileNameExtension, jtf_byFileNameExtension, jl_byFileNameExtension_Ignore, jtf_byFileNameExtension_Ignore));
		/*** 第八區 ***/
		Style.setBounds(Style.MODEL_JL1_JC1_NULL_JL2_JC2_JC_BTN, Arrays.asList(null, jl_byModify_greaterThan, jxdp_byModify_greaterThan, null, jl_byModify_lessThan, jxdp_byModify_lessThan, null, null, null));
		jpSub1.add(Arrays.asList(jl_byModify_greaterThan, jxdp_byModify_greaterThan, jl_byModify_lessThan, jxdp_byModify_lessThan));
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {
		jl_searchCondition.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_resultType.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFileText.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFileTextCaseSensitive.setForeground(APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jcb_byFileTextCaseSensitive, NAME_BYTEXTCASESENSITIVE, APP_COLOR_DEFAULT);
		jl_byFileName.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFileNameCaseSensitive.setForeground(APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jcb_byFileNameCaseSensitive, NAME_BYTEXTCASESENSITIVE, APP_COLOR_DEFAULT);
		jl_byFileNameExtension.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFileNameExtension_Ignore.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byModify_greaterThan.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byModify_lessThan.setForeground(APP_COLOR_SEARCH_CRITERIA);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
				FindFileController.resetData();
				showStatus(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				FindFileController.clearData();
				showStatus(MSG_CLEARDATA);
			}
		});

		// 查詢
		jb_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
				try {
					FindFileController.findConditionFile();
					showStatus(MSG_SEARCH);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		setDbClickForCopy(jtf_searchPath);
		setDbClickForCopy(jtf_byFileText);
		setDbClickForCopy(jtf_byFileName);
		setDbClickForCopy(jta_result);
	}

	private static void setEnd() {
		FindFileController.formShow();
	}

}
