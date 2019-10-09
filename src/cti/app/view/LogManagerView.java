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
import cti.app.constant.LogManagerConstant;
import cti.app.controller.FindFileController;
import cti.app.service.AppTimer;
import cti.app.service.FindFileService;

public class LogManagerView extends LogManagerConstant {
	private static JPanel jp = new JPanel();
	public static FindFileService fs = new FindFileService();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabelSimple jl_logFilePath = new JLabelSimple(JL_LOGFILEPATH); // log檔路徑
	protected static JTextFieldSimple jtf_logFilePath = new JTextFieldSimple(jl_logFilePath);
	private static JButtonFilePath jb_logFilepath = new JButtonFilePath(jtf_logFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_searchCondition = new JLabelSimple(JL_SEARCHCONDITION);
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabelSimple jl_byTime = new JLabelSimple(JL_BYTIME);
	protected static JTextFieldSimple jtf_byTime = new JTextFieldSimple(jl_byTime);
	private static JButtonSimple jb_search = new JButtonSimple(BTN_SEARCH);

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
		jl_logFilePath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_logFilepath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_logFilePath);
		jpSub1.add(jtf_logFilePath);
		jpSub1.add(jb_logFilepath);
		jpSub1.add(jb_resetData);
		/*** 第二區 ***/
		row += 40;
		jl_searchCondition.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchCondition);
		jpSub1.add(jb_clearData);
		/*** 第三區 ***/
		row += 40;
		jl_byTime.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byTime.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jb_search.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_byTime);
		jpSub1.add(jtf_byTime);
		jpSub1.add(jb_search);
		/*** 第四區 ***/
		row += 40;
		/*** 第五區 ***/
		row += 40;
		/*** 第六區 ***/
		row += 40;
		/*** 第七區 ***/
		row += 40;
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	private static void setComponent() {
		jl_searchCondition.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byTime.setForeground(APP_COLOR_SEARCH_CRITERIA);
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

		setDbClickForCopy(jtf_byTime);
		setDbClickForCopy(jta_result);
	}

	private static void setEnd() {
		FindFileController.doInitial();
	}

}
