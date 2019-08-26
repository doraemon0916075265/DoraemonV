package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.CutterConstant;
import cti.app.controller.CutterController;
import cti.app.service.AppTimer;

public class CutterView extends CutterConstant {
	private static JPanel jp = new JPanel();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

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

	protected static JTextArea jta_resultS = new JTextArea(5, 93);
	protected static JTextArea jta_resultF = new JTextArea(10, 93);

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
		jl_specFilePath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_specFilepath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_specFilePath);
		jpSub1.add(jtf_specFilePath);
		jpSub1.add(jb_specFilepath);
		jpSub1.add(jb_clearData);
		/*** 第三區 ***/
		row += 40;
		jl_logInfo_send.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_send.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jl_logInfo_sendLen.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_COL7, SIZE_VER_INPUT);
		jb_readFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_logInfo_send);
		jpSub1.add(jtf_logInfo_send);
		jpSub1.add(jl_logInfo_sendLen);
		jpSub1.add(jb_readFile);
		/*** 第四區 ***/
		row += 40;
		jl_logInfo_fill.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_fill.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jl_logInfo_fillLen.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_COL7, SIZE_VER_INPUT);
		jb_analysis.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_logInfo_fill);
		jpSub1.add(jtf_logInfo_fill);
		jpSub1.add(jl_logInfo_fillLen);
		jpSub1.add(jb_analysis);
		/*** 第五區 ***/
		row += 40;
		jl_specInfo_send.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specSendCut0.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specSendCut.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jl_specInfo_sendLen.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_specInfo_send);
		jpSub1.add(jtf_specSendCut0);
		jpSub1.add(jtf_specSendCut);
		jpSub1.add(jl_specInfo_sendLen);
		/*** 第六區 ***/
		row += 40;
		jl_specInfo_fill.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specFillCut0.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specFillCut.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jl_specInfo_fillLen.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_specInfo_fill);
		jpSub1.add(jtf_specFillCut0);
		jpSub1.add(jtf_specFillCut);
		jpSub1.add(jl_specInfo_fillLen);
		/*** 第七區 ***/
		row += 40;
		jl_logInfo_ID.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_ID.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specInfo_note.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jb_guideBook.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_logInfo_ID);
		jpSub1.add(jtf_logInfo_ID);
		jpSub1.add(jtf_specInfo_note);
		jpSub1.add(jb_guideBook);
		/*** 第八區 ***/
		row += 40;
		jl_exportFile.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_exportFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_exportFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_exportFile);
		jpSub1.add(jtf_exportFilePath);
		jpSub1.add(jb_exportFile);

		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_resultS));
		jpSub2.add(new JScrollPane(jta_resultF));

		jp.add(jpSub1);
		jp.add(jpSub2);
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

		setAppStyle4TextArea(jta_resultS, NAME_RESULTS, APP_COLOR_DEFAULT, false);
		setAppStyle4TextArea(jta_resultF, NAME_RESULTF, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.doInitial();
				showStatus(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.clearData(null);
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
		CutterController.doInitial();
	}

}
