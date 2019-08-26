package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.component.JXDatePickerSimple;
import cti.app.constant.FindFileConstant;
import cti.app.controller.FindFileController;
import cti.app.service.AppTimer;
import cti.app.service.FindFileService;

public class FindFileView extends FindFileConstant {
	private static JPanel jp = new JPanel();
	public static FindFileService fs = new FindFileService();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabelSimple jl_searchPath = new JLabelSimple(JL_SEARCHPATH);// 欲查路徑
	protected static JTextFieldSimple jtf_searchPath = new JTextFieldSimple(jl_searchPath);
	protected static JButtonFilePath jb_searchPath = new JButtonFilePath(jtf_searchPath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_searchCondition = new JLabelSimple(JL_SEARCHCONDITION);
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabelSimple jl_byText = new JLabelSimple(JL_BYTEXT);// 字串查詢
	protected static JTextFieldSimple jtf_byText = new JTextFieldSimple(jl_byText);
	private static JButtonSimple jb_search = new JButtonSimple(BTN_SEARCH);

	private static JLabelSimple jl_byTextCaseSensitive = new JLabelSimple(JL_BYTEXTCASESENSITIVE);// 字串查詢
	protected static JCheckBox jcb_byTextCaseSensitive = new JCheckBox();

	private static JLabelSimple jl_byFilename = new JLabelSimple(JL_BYFILENAME);// 檔名查詢
	protected static JTextFieldSimple jtf_byFilename = new JTextFieldSimple(jl_byFilename);

	private static JLabelSimple jl_byFilenameExtension = new JLabelSimple(JL_BYFILENAMEEXTENSION);
	protected static JTextFieldSimple jtf_byFilenameExtension = new JTextFieldSimple(jl_byFilenameExtension);

	private static JLabelSimple jl_byFilenameExtension_Ignore = new JLabelSimple(JL_BYFILENAMEEXTENSION_IGNORE);
	protected static JTextFieldSimple jtf_byFilenameExtension_Ignore = new JTextFieldSimple(jl_byFilenameExtension_Ignore);

	private static JLabelSimple jl_byModify_greaterThan = new JLabelSimple(JL_BYMODIFY_GREATERTHAN);
	protected static JXDatePickerSimple jxdp_byModify_greaterThan = new JXDatePickerSimple(jl_byModify_greaterThan);
	private static JLabelSimple jl_byModify_lessThan = new JLabelSimple(JL_BYMODIFY_LESSTHAN);
	protected static JXDatePickerSimple jxdp_byModify_lessThan = new JXDatePickerSimple(jl_byModify_lessThan);

	protected static JTextArea jta_result = new JTextArea(10, 92);

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
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 415));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 210));
		// jpSub1.setBorder(new LineBorder(Color.RED));
		// jpSub2.setBorder(new LineBorder(Color.RED));
	}

	private static void setPosition() {
		int row = 15;// 每一列
		/*** 上半部，第一區 ***/
		jl_searchPath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_searchPath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_searchPath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchPath);
		jpSub1.add(jtf_searchPath);
		jpSub1.add(jb_searchPath);
		jpSub1.add(jb_resetData);
		/*** 第二區 ***/
		row += 40;
		jl_searchCondition.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchCondition);
		jpSub1.add(jb_clearData);
		/*** 第三區 ***/
		row += 40;
		jl_byText.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byText.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_search.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_byText);
		jpSub1.add(jtf_byText);
		jpSub1.add(jb_search);
		/*** 第四區 ***/
		row += 40;
		jl_byTextCaseSensitive.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jcb_byTextCaseSensitive.setBounds(SIZE_HOR_COL2, row, SIZE_VER_INPUT, SIZE_VER_INPUT);
		jpSub1.add(jl_byTextCaseSensitive);
		jpSub1.add(jcb_byTextCaseSensitive);
		/*** 第五區 ***/
		row += 40;
		jl_byFilename.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilename.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilename);
		jpSub1.add(jtf_byFilename);
		/*** 第六區 ***/
		row += 40;
		jl_byFilenameExtension.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jl_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilenameExtension);
		jpSub1.add(jtf_byFilenameExtension);
		jpSub1.add(jl_byFilenameExtension_Ignore);
		jpSub1.add(jtf_byFilenameExtension_Ignore);
		/*** 第七區 ***/
		row += 40;
		jl_byModify_greaterThan.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_byModify_greaterThan.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jl_byModify_lessThan.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_byModify_lessThan.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jpSub1.add(jl_byModify_greaterThan);
		jpSub1.add(jxdp_byModify_greaterThan);
		jpSub1.add(jl_byModify_lessThan);
		jpSub1.add(jxdp_byModify_lessThan);
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	private static void setComponent() {
		jl_searchCondition.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byText.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byTextCaseSensitive.setForeground(APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jcb_byTextCaseSensitive, NAME_BYTEXTCASESENSITIVE, APP_COLOR_DEFAULT);
		jl_byFilename.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFilenameExtension.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byFilenameExtension_Ignore.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byModify_greaterThan.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byModify_lessThan.setForeground(APP_COLOR_SEARCH_CRITERIA);
		setAppStyle4TextArea(jta_result, NAME_RESULT, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
				FindFileController.doInitial();
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
		setDbClickForCopy(jtf_byText);
		setDbClickForCopy(jtf_byFilename);
		setDbClickForCopy(jta_result);
	}

	private static void setEnd() {
		FindFileController.doInitial();
	}

}
