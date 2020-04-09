package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.CutterConstant;
import cti.app.controller.CutterController;
import cti.app.service.AppTimer;

public class CutterView extends CutterConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

	private static JLabelSimple jl_logFilePath = new JLabelSimple(JL_LOGFILEPATH); // log檔路徑
	protected static JTextFieldSimple jtf_logFilePath = new JTextFieldSimple(jl_logFilePath);
	private static JButtonFilePath jb_logFilepath = new JButtonFilePath(jtf_logFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_specFilePath = new JLabelSimple(JL_SPECFILEPATH); // spec檔路徑
	protected static JTextFieldSimple jtf_specFilePath = new JTextFieldSimple(jl_specFilePath);
	private static JButtonFilePath jb_specFilepath = new JButtonFilePath(jtf_specFilePath);
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabelSimple jl_logInfo_send = new JLabelSimple(JL_LOGINFO_SEND); // 上行電文
	protected static JTextFieldSimple jtf_logInfo_send = new JTextFieldSimple(jl_logInfo_send);
	private static JLabelSimple jl_logInfo_sendLen = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb_readFile = new JButtonSimple(BTN_READFILE);

	private static JLabelSimple jl_logInfo_fill = new JLabelSimple(JL_LOGINFO_FILL); // 下行電文
	protected static JTextFieldSimple jtf_logInfo_fill = new JTextFieldSimple(jl_logInfo_fill);
	private static JLabelSimple jl_logInfo_fillLen = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb_analysis = new JButtonSimple(BTN_ANALYSIS);

	private static JLabelSimple jl_specInfo_send = new JLabelSimple(JL_SPECINFO_SEND); // 上行電文陣列
	protected static JTextFieldSimple jtf_specSendCut0 = new JTextFieldSimple(jl_specInfo_send);
	protected static JTextFieldSimple jtf_specSendCut = new JTextFieldSimple(jl_specInfo_send);
	private static JLabelSimple jl_specInfo_sendLen = new JLabelSimple(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabelSimple jl_specInfo_fill = new JLabelSimple(JL_SPECINFO_FILL); // 下行電文陣列
	protected static JTextFieldSimple jtf_specFillCut0 = new JTextFieldSimple(jl_specInfo_fill);
	protected static JTextFieldSimple jtf_specFillCut = new JTextFieldSimple(jl_specInfo_fill);
	private static JLabelSimple jl_specInfo_fillLen = new JLabelSimple(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabelSimple jl_logInfo_ID = new JLabelSimple(JL_LOGINFO_ID); // 電文ID/資訊
	protected static JTextFieldSimple jtf_logInfo_ID = new JTextFieldSimple(jl_logInfo_ID);
	protected static JTextFieldSimple jtf_specInfo_note = new JTextFieldSimple(jl_logInfo_ID);
	private static JButtonSimple jb_guideBook = new JButtonSimple(BTN_GUIDEBOOK);

	private static JLabelSimple jl_exportFile = new JLabelSimple(JL_EXPORTFILE); // 匯出檔案路徑
	protected static JTextFieldSimple jtf_exportFilePath = new JTextFieldSimple(jl_exportFile);
	private static JButtonSimple jb_exportFile = new JButtonSimple(BTN_EXPORTFILE);

	protected static JTextAreaSimple jta_resultS = new JTextAreaSimple(5, false);
	protected static JTextAreaSimple jta_resultF = new JTextAreaSimple(10, false);

	// 隱藏欄位
	protected static JLabel hidden_cname = new JLabel();
	protected static JLabel hidden_scname0 = new JLabel();
	protected static JLabel hidden_scname = new JLabel();
	protected static JLabel hidden_fcname0 = new JLabel();
	protected static JLabel hidden_fcname = new JLabel();
	protected static JLabel hidden_sename0 = new JLabel();
	protected static JLabel hidden_sename = new JLabel();
	protected static JLabel hidden_fename0 = new JLabel();
	protected static JLabel hidden_fename = new JLabel();

	public static JPanel createView() {
		setBegin();
		setPosition();
		setComponent();
		setListener();
		setEnd();
		return jp;
	}

	private static void setBegin() {
		jpSub1.setLayout(null);
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 325));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
	}

	private static void setPosition() {
		/*** 上半部，第一區 ***/
		Style.resetRow();
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_logFilePath, jtf_logFilePath, jb_logFilepath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_logFilePath, jtf_logFilePath, jb_logFilepath, jb_resetData));
		/*** 第二區 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_specFilePath, jtf_specFilePath, jb_specFilepath, null, jb_clearData, null));
		jpSub1.add(Arrays.asList(jl_specFilePath, jtf_specFilePath, jb_specFilepath, jb_clearData));
		/*** 第三區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_logInfo_send, jtf_logInfo_send, null, jl_logInfo_sendLen, jb_readFile, null));
		jpSub1.add(Arrays.asList(jl_logInfo_send, jtf_logInfo_send, jl_logInfo_sendLen, jb_readFile));
		/*** 第四區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_logInfo_fill, jtf_logInfo_fill, null, jl_logInfo_fillLen, jb_analysis, null));
		jpSub1.add(Arrays.asList(jl_logInfo_fill, jtf_logInfo_fill, jl_logInfo_fillLen, jb_analysis));
		/*** 第五區 ***/
		Style.setBounds(Style.MODEL_JL_JTF1_NULL_JTF2_NULL_JL_BTN, Arrays.asList(null, jl_specInfo_send, jtf_specSendCut0, null, jtf_specSendCut, null, jl_specInfo_sendLen, null, null));
		jpSub1.add(Arrays.asList(jl_specInfo_send, jtf_specSendCut0, jtf_specSendCut, jl_specInfo_sendLen));
		/*** 第六區 ***/
		Style.setBounds(Style.MODEL_JL_JTF1_NULL_JTF2_NULL_JL_BTN, Arrays.asList(null, jl_specInfo_fill, jtf_specFillCut0, null, jtf_specFillCut, null, jl_specInfo_fillLen, null, null));
		jpSub1.add(Arrays.asList(jl_specInfo_fill, jtf_specFillCut0, jtf_specFillCut, jl_specInfo_fillLen));
		/*** 第七區 ***/
		Style.setBounds(Style.MODEL_JL_JTF1_NULL_JTF2_NULL_JL_BTN, Arrays.asList(null, jl_logInfo_ID, jtf_logInfo_ID, null, jtf_specInfo_note, null, null, jb_guideBook, null));
		jpSub1.add(Arrays.asList(jl_logInfo_ID, jtf_logInfo_ID, jtf_specInfo_note, jb_guideBook));
		/*** 第八區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_exportFile, jtf_exportFilePath, null, null, jb_exportFile, null));
		jpSub1.add(Arrays.asList(jl_exportFile, jtf_exportFilePath, jb_exportFile));

		/*** 下半部 ***/
		jpSub2.add(Arrays.asList(new JScrollPane(jta_resultS), new JScrollPane(jta_resultF)));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {
		// setStyle
		jl_logFilePath.setForeground(APP_COLOR_LOG);
		jl_specFilePath.setForeground(APP_COLOR_SPEC);
		jl_logInfo_send.setForeground(APP_COLOR_LOG);
		jl_logInfo_fill.setForeground(APP_COLOR_LOG);
		jl_specInfo_send.setForeground(APP_COLOR_SPEC);
		jl_specInfo_fill.setForeground(APP_COLOR_SPEC);
		jl_logInfo_ID.setForeground(APP_COLOR_LOG);

		// setEditable
		jtf_specInfo_note.setEditable(false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.resetData();
				showStatus(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.clearData();
				showStatus(MSG_CLEARDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					CutterController.readFile();
					showStatus(MSG_READFILE);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 解析
		jb_analysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					CutterController.analysis();
					showStatus(MSG_ANALYSIS);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 說明書
		jb_guideBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				showStatus(MSG_OPEN_GUIDEBOOK);
				UIManager.put("OptionPane.messageFont", new FontUIResource(APP_FONT));
				JOptionPane.showMessageDialog(null, CutterController.guidebook(), APP_GUIDEBOOK_TITLE, JOptionPane.DEFAULT_OPTION);
				showStatus(MSG_CLOSE_GUIDEBOOK);
			}
		});

		// 匯出
		jb_exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					CutterController.export();
					showStatus(MSG_EXPORTFILE + "於" + jtf_exportFilePath.getText());
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 雙擊複製
		setDbClickForCopy(jtf_logFilePath);
		setDbClickForCopy(jtf_specFilePath);
		setDbClickForCopy(jtf_logInfo_ID);
		setDbClickForCopy(jtf_logInfo_send);
		setDbClickForCopy(jtf_logInfo_fill);
		setDbClickForCopy(jtf_specSendCut0);
		setDbClickForCopy(jtf_specSendCut);
		setDbClickForCopy(jtf_specFillCut0);
		setDbClickForCopy(jtf_specFillCut);
		setDbClickForCopy(jtf_specInfo_note);
		setDbClickForCopy(jtf_exportFilePath);
		setDbClickForCopy(jta_resultS);
		setDbClickForCopy(jta_resultF);

		// 取電文長度
		getInputTextLength(jtf_logInfo_send, jl_logInfo_sendLen);
		getInputTextLength(jtf_logInfo_fill, jl_logInfo_fillLen);
		getInputIntegerArraySum(Arrays.asList(jtf_specSendCut0, jtf_specSendCut), jl_specInfo_sendLen);
		getInputIntegerArraySum(Arrays.asList(jtf_specFillCut0, jtf_specFillCut), jl_specInfo_fillLen);
	}

	private static void setEnd() {
		CutterController.formShow();
	}

}