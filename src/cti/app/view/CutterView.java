package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import cti.app.constant.CutterConstant;
import cti.app.controller.CutterController;
import cti.app.handler.CutterHandler;
import cti.app.service.CutterService;

public class CutterView extends CutterConstant {
	private static JPanel jp = new JPanel();
	public static CutterService cs = new CutterService();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_logFilePath = new JLabel(JL_LOGFILEPATH);// log檔路徑
	protected static JTextField jtf_logFilePath = new JTextField();
	private static JButton jb_logFilepath = new JButton();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);

	private static JLabel jl_specFilePath = new JLabel(JL_SPECFILEPATH);// spec檔路徑
	protected static JTextField jtf_specFilePath = new JTextField();
	private static JButton jb_specFilepath = new JButton();
	private static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	private static JLabel jl_logInfo_send = new JLabel(JL_LOGINFO_SEND);// 上行電文
	protected static JTextField jtf_logInfo_send = new JTextField();
	private static JLabel jl_logInfo_sendLen = new JLabel(STR_ZERO);
	private static JButton jb_readFile = new JButton(BTN_READFILE);

	private static JLabel jl_logInfo_fill = new JLabel(JL_LOGINFO_FILL);// 下行電文
	protected static JTextField jtf_logInfo_fill = new JTextField();
	private static JLabel jl_logInfo_fillLen = new JLabel(STR_ZERO);
	private static JButton jb_analysis = new JButton(BTN_ANALYSIS);

	private static JLabel jl_specInfo_send = new JLabel(JL_SPECINFO_SEND);// 上行電文陣列
	protected static JTextField jtf_specSendCut0 = new JTextField();
	protected static JTextField jtf_specSendCut = new JTextField();
	private static JLabel jl_specInfo_sendLen = new JLabel(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabel jl_specInfo_fill = new JLabel(JL_SPECINFO_FILL);// 下行電文陣列
	protected static JTextField jtf_specFillCut0 = new JTextField();
	protected static JTextField jtf_specFillCut = new JTextField();
	private static JLabel jl_specInfo_fillLen = new JLabel(String.join(SIGN_VERTICAL_BAR01, STR_ZERO, STR_ZERO));

	private static JLabel jl_logInfo_ID = new JLabel(JL_LOGINFO_ID);// 電文ID/資訊
	protected static JTextField jtf_logInfo_ID = new JTextField();
	protected static JTextField jtf_specInfo_note = new JTextField();
	private static JButton jb_guideBook = new JButton(BTN_GUIDEBOOK);

	private static JLabel jl_exportFile = new JLabel(JL_EXPORTFILE);// 匯出檔案路徑
	protected static JTextField jtf_exportFilePath = new JTextField();
	protected static JCheckBox jcb_isAddEqual = new JCheckBox(JCB_ISADDEQUAL_2);
	private static JButton jb_exportFile = new JButton(BTN_EXPORTFILE);

	protected static JTextArea jta_resultS = new JTextArea(3, 93);
	protected static JTextArea jta_resultF = new JTextArea(12, 93);

	// 隱藏欄位
	protected static JLabel hidden_cname = new JLabel();
	protected static JLabel hidden_sname0 = new JLabel();
	protected static JLabel hidden_sname = new JLabel();
	protected static JLabel hidden_fname0 = new JLabel();
	protected static JLabel hidden_fname = new JLabel();

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
		jcb_isAddEqual.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_exportFile);
		jpSub1.add(jtf_exportFilePath);
		jpSub1.add(jb_exportFile);
		jpSub1.add(jcb_isAddEqual);

		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_resultS));
		jpSub2.add(new JScrollPane(jta_resultF));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	private static void setComponent() {
		cs.setAppStyle(jl_logFilePath, null, APP_COLOR_LOG);// log檔路徑
		cs.setAppStyle(jtf_logFilePath, NAME_LOGFILEPATH, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_logFilepath, null, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_resetData, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_specFilePath, null, APP_COLOR_SPEC);// spec檔路徑
		cs.setAppStyle(jtf_specFilePath, NAME_SPECFILEPATH, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_specFilepath, null, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_clearData, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_logInfo_send, null, APP_COLOR_LOG);// 上行電文
		cs.setAppStyle(jtf_logInfo_send, NAME_LOGINFO_SEND, APP_COLOR_DEFAULT);
		cs.setAppStyle(jl_logInfo_sendLen, null, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_readFile, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_logInfo_fill, null, APP_COLOR_LOG);// 下行電文
		cs.setAppStyle(jtf_logInfo_fill, NAME_LOGINFO_FILL, APP_COLOR_DEFAULT);
		cs.setAppStyle(jl_logInfo_fillLen, null, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_analysis, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_specInfo_send, null, APP_COLOR_SPEC);// 上行電文
		cs.setAppStyle(jtf_specSendCut0, NAME_SPECSENDCUT0, APP_COLOR_DEFAULT);
		cs.setAppStyle(jtf_specSendCut, NAME_SPECSENDCUT, APP_COLOR_DEFAULT);
		cs.setAppStyle(jl_specInfo_sendLen, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_specInfo_fill, null, APP_COLOR_SPEC);// 下行電文
		cs.setAppStyle(jtf_specFillCut0, NAME_SPECFILLCUT0, APP_COLOR_DEFAULT);
		cs.setAppStyle(jtf_specFillCut, NAME_SPECFILLCUT, APP_COLOR_DEFAULT);
		cs.setAppStyle(jl_specInfo_fillLen, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_logInfo_ID, null, APP_COLOR_LOG);// 電文ID/資訊
		cs.setAppStyle(jtf_logInfo_ID, NAME_LOGINFO_ID, APP_COLOR_DEFAULT);
		cs.setAppStyle4Info(jtf_specInfo_note, NAME_SPECINFO_NOTE, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_guideBook, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jl_exportFile, null, APP_COLOR_DEFAULT);
		cs.setAppStyle(jtf_exportFilePath, NAME_EXPORTFILEPATH, APP_COLOR_DEFAULT);
		cs.setAppStyle(jb_exportFile, null, APP_COLOR_DEFAULT);

		cs.setAppStyle(jcb_isAddEqual, null, APP_COLOR_DEFAULT);

		cs.setAppStyle4TextArea(jta_resultS, NAME_RESULTS, APP_COLOR_DEFAULT, false);
		cs.setAppStyle4TextArea(jta_resultF, NAME_RESULTF, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterController.clearData();
				showMsg(MSG_SUCCESS, MSG_CLEARDATA);
			}
		});

		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				CutterController.resetData();
				showMsg(MSG_RESETDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterHandler.readFile();
					showMsg(MSG_READFILE);
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), e.getMessage());
					isTimerWork(false);
				}
			}
		});

		// 解析
		jb_analysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterHandler.analysis();
					showMsg(MSG_ANALYSIS);
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), e.getMessage());
					isTimerWork(false);
				}
			}
		});

		// 匯出
		jb_exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterHandler.exportFile();
					showMsg(MSG_EXPORTFILE + "於" + jtf_exportFilePath.getText());
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), e.getMessage());
					isTimerWork(false);
				}
			}
		});

		// 導覽
		jb_guideBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				showMsg(MSG_OPEN_GUIDEBOOK);
				UIManager.put("OptionPane.messageFont", new FontUIResource(APP_FONT));
				JOptionPane.showMessageDialog(null, getGuidebookContent(), APP_GUIDEBOOK_TITLE, JOptionPane.DEFAULT_OPTION);
				showMsg(MSG_CLOSE_GUIDEBOOK);
			}
		});

		// 匯出格式
		jcb_isAddEqual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (jcb_isAddEqual.isSelected()) {
					jcb_isAddEqual.setText(JCB_ISADDEQUAL_2);
				} else {
					jcb_isAddEqual.setText(JCB_ISADDEQUAL_1);

				}
			}
		});

		// 取檔案路徑
		cs.btnGetPath(jb_logFilepath, jtf_logFilePath);
		cs.btnGetPath(jb_specFilepath, jtf_specFilePath);

		// 取電文長度
		cs.getInputTextLength(jtf_logInfo_send, jl_logInfo_sendLen);
		cs.getInputTextLength(jtf_logInfo_fill, jl_logInfo_fillLen);
		cs.getInputIntegerArraySum(Arrays.asList(jtf_specSendCut0, jtf_specSendCut), jl_specInfo_sendLen);
		cs.getInputIntegerArraySum(Arrays.asList(jtf_specFillCut0, jtf_specFillCut), jl_specInfo_fillLen);

		// 點擊複製
		cs.dbClickOnCopy(jtf_logFilePath);
		cs.dbClickOnCopy(jtf_specFilePath);
		cs.dbClickOnCopy(jtf_logInfo_ID);
		cs.dbClickOnCopy(jtf_logInfo_send);
		cs.dbClickOnCopy(jtf_logInfo_fill);
		cs.dbClickOnCopy(jtf_specSendCut0);
		cs.dbClickOnCopy(jtf_specSendCut);
		cs.dbClickOnCopy(jtf_specFillCut0);
		cs.dbClickOnCopy(jtf_specFillCut);
		cs.dbClickOnCopy(jtf_specInfo_note);
		cs.dbClickOnCopy(jtf_exportFilePath);
		cs.dbClickOnCopy(jta_resultS);
		cs.dbClickOnCopy(jta_resultF);
	}

	private static void setEnd() {
		CutterController.doInitial();
	}

}
