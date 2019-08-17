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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import cti.app.component.JButtonSimple;
import cti.app.constant.CutterConstant;
import cti.app.controller.CutterController;

public class CutterView extends CutterConstant {
	private static JPanel jp = new JPanel();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_logFilePath = new JLabel(JL_LOGFILEPATH); // log檔路徑
	private static JButtonSimple jb_logFilepath = new JButtonSimple();
	protected static JTextField jtf_logFilePath = new JTextField();
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabel jl_specFilePath = new JLabel(JL_SPECFILEPATH); // spec檔路徑
	protected static JTextField jtf_specFilePath = new JTextField();
	private static JButtonSimple jb_specFilepath = new JButtonSimple();
	private static JButtonSimple jb_clearData = new JButtonSimple(BTN_CLEARDATA);

	private static JLabel jl_logInfo_send = new JLabel(JL_LOGINFO_SEND); // 上行電文
	protected static JTextField jtf_logInfo_send = new JTextField();
	private static JLabel jl_logInfo_sendLen = new JLabel(STR_ZERO);
	private static JButtonSimple jb_readFile = new JButtonSimple(BTN_READFILE);

	private static JLabel jl_logInfo_fill = new JLabel(JL_LOGINFO_FILL); // 下行電文
	protected static JTextField jtf_logInfo_fill = new JTextField();
	private static JLabel jl_logInfo_fillLen = new JLabel(STR_ZERO);
	private static JButtonSimple jb_analysis = new JButtonSimple(BTN_ANALYSIS);

	private static JLabel jl_specInfo_send = new JLabel(JL_SPECINFO_SEND); // 上行電文陣列
	protected static JTextField jtf_specSendCut0 = new JTextField();
	protected static JTextField jtf_specSendCut = new JTextField();
	private static JLabel jl_specInfo_sendLen = new JLabel(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabel jl_specInfo_fill = new JLabel(JL_SPECINFO_FILL); // 下行電文陣列
	protected static JTextField jtf_specFillCut0 = new JTextField();
	protected static JTextField jtf_specFillCut = new JTextField();
	private static JLabel jl_specInfo_fillLen = new JLabel(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabel jl_logInfo_ID = new JLabel(JL_LOGINFO_ID); // 電文ID/資訊
	protected static JTextField jtf_logInfo_ID = new JTextField();
	protected static JTextField jtf_specInfo_note = new JTextField();
	private static JButtonSimple jb_guideBook = new JButtonSimple(BTN_GUIDEBOOK);

	private static JLabel jl_exportFile = new JLabel(JL_EXPORTFILE); // 匯出檔案路徑
	protected static JTextField jtf_exportFilePath = new JTextField();
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
		setAppStyle(jl_logFilePath, null, APP_COLOR_LOG);// log檔路徑
		setAppStyle(jtf_logFilePath, NAME_LOGFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_logFilepath, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_specFilePath, null, APP_COLOR_SPEC);// spec檔路徑
		setAppStyle(jtf_specFilePath, NAME_SPECFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_specFilepath, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_clearData, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_send, null, APP_COLOR_LOG);// 上行電文
		setAppStyle(jtf_logInfo_send, NAME_LOGINFO_SEND, APP_COLOR_DEFAULT);
		setAppStyle(jl_logInfo_sendLen, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_readFile, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_fill, null, APP_COLOR_LOG);// 下行電文
		setAppStyle(jtf_logInfo_fill, NAME_LOGINFO_FILL, APP_COLOR_DEFAULT);
		setAppStyle(jl_logInfo_fillLen, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_analysis, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_specInfo_send, null, APP_COLOR_SPEC);// 上行電文
		setAppStyle(jtf_specSendCut0, NAME_SPECSENDCUT0, APP_COLOR_DEFAULT);
		setAppStyle(jtf_specSendCut, NAME_SPECSENDCUT, APP_COLOR_DEFAULT);
		setAppStyle(jl_specInfo_sendLen, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_specInfo_fill, null, APP_COLOR_SPEC);// 下行電文
		setAppStyle(jtf_specFillCut0, NAME_SPECFILLCUT0, APP_COLOR_DEFAULT);
		setAppStyle(jtf_specFillCut, NAME_SPECFILLCUT, APP_COLOR_DEFAULT);
		setAppStyle(jl_specInfo_fillLen, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_ID, null, APP_COLOR_LOG);// 電文ID/資訊
		setAppStyle(jtf_logInfo_ID, NAME_LOGINFO_ID, APP_COLOR_DEFAULT);
		setAppStyle4Info(jtf_specInfo_note, NAME_SPECINFO_NOTE, APP_COLOR_DEFAULT);
		setAppStyle(jb_guideBook, null, APP_COLOR_DEFAULT);

		setAppStyle(jl_exportFile, null, APP_COLOR_DEFAULT);
		setAppStyle(jtf_exportFilePath, NAME_EXPORTFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_exportFile, null, APP_COLOR_DEFAULT);

		setAppStyle4TextArea(jta_resultS, NAME_RESULTS, APP_COLOR_DEFAULT, false);
		setAppStyle4TextArea(jta_resultF, NAME_RESULTF, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.doInitial();
				showMsg(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.clearData(null);
				showMsg(MSG_CLEARDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("eee");
				try {
					isTimerWork(true);
					CutterController.readFile();
					showMsg(MSG_READFILE);
				} catch (Exception e) {
					showMsg(e);
				}
			}
		});

		// 解析
		jb_analysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterController.analysis();
					showMsg(MSG_ANALYSIS);
				} catch (Exception e) {
					showMsg(e);
				}
			}
		});

		// 說明書
		jb_guideBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				showMsg(MSG_OPEN_GUIDEBOOK);
				UIManager.put("OptionPane.messageFont", new FontUIResource(APP_FONT));
				JOptionPane.showMessageDialog(null, CutterController.guidebook(), APP_GUIDEBOOK_TITLE, JOptionPane.DEFAULT_OPTION);
				showMsg(MSG_CLOSE_GUIDEBOOK);
			}
		});

		// 匯出
		jb_exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterController.export();
					showMsg(MSG_EXPORTFILE + "於" + jtf_exportFilePath.getText());
				} catch (Exception e) {
					showMsg(e);
				}
			}
		});

		// 取檔案路徑
		btnGetPath(jb_logFilepath, jtf_logFilePath);
		btnGetPath(jb_specFilepath, jtf_specFilePath);

		// 取電文長度
		getInputTextLength(jtf_logInfo_send, jl_logInfo_sendLen);
		getInputTextLength(jtf_logInfo_fill, jl_logInfo_fillLen);
		getInputIntegerArraySum(Arrays.asList(jtf_specSendCut0, jtf_specSendCut), jl_specInfo_sendLen);
		getInputIntegerArraySum(Arrays.asList(jtf_specFillCut0, jtf_specFillCut), jl_specInfo_fillLen);

		// 點擊複製
		dbClickOnCopy(jtf_logFilePath);
		dbClickOnCopy(jtf_specFilePath);
		dbClickOnCopy(jtf_logInfo_ID);
		dbClickOnCopy(jtf_logInfo_send);
		dbClickOnCopy(jtf_logInfo_fill);
		dbClickOnCopy(jtf_specSendCut0);
		dbClickOnCopy(jtf_specSendCut);
		dbClickOnCopy(jtf_specFillCut0);
		dbClickOnCopy(jtf_specFillCut);
		dbClickOnCopy(jtf_specInfo_note);
		dbClickOnCopy(jtf_exportFilePath);
		dbClickOnCopy(jta_resultS);
		dbClickOnCopy(jta_resultF);
	}

	private static void setEnd() {
		CutterController.doInitial();
	}

}
