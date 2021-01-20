package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.Tab05_LogManagerConstant;
import cti.app.controller.Tab05_LogManagerController;
import cti.app.service.AppTimer;

public class Tab05_LogManagerView extends Tab05_LogManagerConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

	private static JLabelSimple jl_logFilePath = new JLabelSimple(JL_LOGFILEPATH); // log檔路徑
	protected static JTextFieldSimple jtf_logFilePath = new JTextFieldSimple(jl_logFilePath);
	private static JButtonFilePath jb_logFilepath = new JButtonFilePath(jtf_logFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_searchCondition = new JLabelSimple(JL_SEARCHCONDITION);
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabelSimple jl_byExecuteTime = new JLabelSimple(JL_BYTIME);
	protected static JTextFieldSimple jtf_byExecuteTime = new JTextFieldSimple(jl_byExecuteTime);
	private static JButtonSimple jb_search = new JButtonSimple(BTN_SEARCH);

	protected static JTextAreaSimple jta_result = new JTextAreaSimple("結果", 10, false);

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
	}

	private static void setPosition() {
		Style.resetRow();
		/*** 上半部，第一區 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_logFilePath, jtf_logFilePath, jb_logFilepath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_logFilePath, jtf_logFilePath, jb_logFilepath, jb_resetData));
		/*** 第二區 ***/
		Style.setBounds(Style.MODEL_NULL_JL_NULL_JC_BTN, Arrays.asList(null, null, jl_searchCondition, null, null, jb_clearData, null));
		jpSub1.add(Arrays.asList(jl_searchCondition, jb_clearData));
		/*** 第三區 ***/
		Style.setBounds(Style.MODEL_JL_JC1_NULL_JC2_NULL_JC3_NULL_JC4_JC5_BTN, Arrays.asList(null, jl_byExecuteTime, jtf_byExecuteTime, null, null, null, null, null, null, null, jb_search, null));
		jpSub1.add(Arrays.asList(jl_byExecuteTime, jtf_byExecuteTime, jb_search));
		/*** 第四區 ***/
		/*** 第五區 ***/
		/*** 第六區 ***/
		/*** 第七區 ***/
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {
		jl_logFilePath.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_searchCondition.setForeground(APP_COLOR_SEARCH_CRITERIA);
		jl_byExecuteTime.setForeground(APP_COLOR_SEARCH_CRITERIA);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
				Tab05_LogManagerController.doResetData();
				showStatus(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Tab05_LogManagerController.doClearData();
				showStatus(MSG_CLEARDATA);
			}
		});

		// 查詢
		jb_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
				try {
					// FindFileController.findConditionFile();
					showStatus(MSG_SEARCH);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		setDbClickForCopy(jtf_byExecuteTime);
		setDbClickForCopy(jta_result);
	}

	private static void setEnd() {
		Tab05_LogManagerController.formShow();
	}

}
