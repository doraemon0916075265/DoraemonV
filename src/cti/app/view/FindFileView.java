package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import cti.app.constant.FindFileConstant;
import cti.app.controller.FindFileController;
import cti.app.service.FindFileService;

public class FindFileView extends FindFileConstant {
	private static JPanel jp = new JPanel();
	public static FindFileService fs = new FindFileService();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_searchPath = new JLabel(JL_SEARCHPATH);// 欲查路徑
	protected static JTextField jtf_searchPath = new JTextField();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);

	private static JLabel jl_searchCondition = new JLabel(JL_SEARCHCONDITION);
	private static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	private static JLabel jl_byText = new JLabel(JL_BYTEXT);// 字串查詢
	protected static JTextField jtf_byText = new JTextField();
	private static JCheckBox jcb_byText = new JCheckBox();
	private static JButton jb_search = new JButton(BTN_SEARCH);

	private static JLabel jl_byFilename = new JLabel(JL_BYFILENAME);// 檔名查詢
	protected static JTextField jtf_byFilename = new JTextField();

	private static JLabel jl_byFilenameExtension = new JLabel(JL_BYFILENAMEEXTENSION);
	protected static JTextField jtf_byFilenameExtension = new JTextField();

	private static JLabel jl_byFilenameExtension_Ignore = new JLabel(JL_BYFILENAMEEXTENSION_IGNORE);
	protected static JTextField jtf_byFilenameExtension_Ignore = new JTextField();

	private static JLabel jl_byModify_greaterThan = new JLabel(JL_BYMODIFY_GREATERTHAN);
	protected static JXDatePicker jxdp_byModify_greaterThan = new JXDatePicker();
	private static JLabel jl_byModify_lessThan = new JLabel(JL_BYMODIFY_LESSTHAN);
	protected static JXDatePicker jxdp_byModify_lessThan = new JXDatePicker();

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
		jtf_searchPath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchPath);
		jpSub1.add(jtf_searchPath);
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
		jcb_byText.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_VER_INPUT, SIZE_VER_INPUT);
		jb_search.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_byText);
		jpSub1.add(jtf_byText);
		jpSub1.add(jcb_byText);
		jpSub1.add(jb_search);
		/*** 第四區 ***/
		row += 40;
		jl_byFilename.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilename.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilename);
		jpSub1.add(jtf_byFilename);
		/*** 第五區 ***/
		row += 40;
		jl_byFilenameExtension.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jl_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilenameExtension);
		jpSub1.add(jtf_byFilenameExtension);
		jpSub1.add(jl_byFilenameExtension_Ignore);
		jpSub1.add(jtf_byFilenameExtension_Ignore);
		/*** 第六區 ***/
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
		setAppStyle(jl_searchPath, null, APP_COLOR_DEFAULT);
		setAppStyle(jtf_searchPath, NAME_SEARCHPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_searchCondition, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jb_clearData, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_byText, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_byText, NAME_BYTEXT, APP_COLOR_DEFAULT);
		setAppStyle(jb_search, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_byFilename, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_byFilename, NAME_BYFILENAME, APP_COLOR_DEFAULT);

		setAppStyle(jl_byFilenameExtension, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_byFilenameExtension, NAME_BYFILENAMEEXTENSION, APP_COLOR_DEFAULT);

		setAppStyle(jl_byFilenameExtension_Ignore, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_byFilenameExtension_Ignore, NAME_BYFILENAMEEXTENSION_IGNORE, APP_COLOR_DEFAULT);

		setAppStyle(jl_byModify_greaterThan, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jxdp_byModify_greaterThan, NAME_BYMODIFY_GREATERTHAN, APP_COLOR_DEFAULT);
		setAppStyle(jl_byModify_lessThan, null, APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jxdp_byModify_lessThan, NAME_BYMODIFY_LESSTHAN, APP_COLOR_DEFAULT);

		setAppStyle4TextArea(jta_result, NAME_RESULT, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				FindFileController.doInitial();
				showMsg(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				FindFileController.clearData();
				showMsg(MSG_CLEARDATA);
			}
		});

		// 查詢
		jb_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				try {
					FindFileController.findConditionFile();
					showMsg(MSG_SEARCH);
				} catch (Exception e) {
					showMsg(e);
				}
			}
		});

		dbClickOnCopy(jtf_searchPath);
		dbClickOnCopy(jtf_byText);
		dbClickOnCopy(jtf_byFilename);
		dbClickOnCopy(jta_result);
	}

	private static void setEnd() {
		FindFileController.doInitial();
	}

}
