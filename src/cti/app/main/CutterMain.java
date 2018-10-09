package cti.app.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

import cti.app.constant.CutterConstant;
import cti.app.handler.CutterHandler;

public class CutterMain extends CutterConstant {
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
	private static JLabel jl_logInfo_sendLen = new JLabel(LEN_0);
	private static JButton jb_readFile = new JButton(BTN_READFILE);

	private static JLabel jl_logInfo_fill = new JLabel(JL_LOGINFO_FILL);// 下行電文
	protected static JTextField jtf_logInfo_fill = new JTextField();
	private static JLabel jl_logInfo_fillLen = new JLabel(LEN_0);
	private static JButton jb_analysis = new JButton(BTN_ANALYSIS);

	private static JLabel jl_specInfo_send = new JLabel(JL_SPECINFO_SEND);// 上行電文陣列
	protected static JTextField jtf_specSendCut0 = new JTextField();
	protected static JTextField jtf_specSendCut = new JTextField();
	private static JLabel jl_specInfo_sendLen = new JLabel(String.format(FORMAT_MSG_TGLEN, LEN_0, LEN_0));

	private static JLabel jl_specInfo_fill = new JLabel(JL_SPECINFO_FILL);// 下行電文陣列
	protected static JTextField jtf_specFillCut0 = new JTextField();
	protected static JTextField jtf_specFillCut = new JTextField();
	private static JLabel jl_specInfo_fillLen = new JLabel(String.format(FORMAT_MSG_TGLEN, LEN_0, LEN_0));

	private static JLabel jl_logInfo_ID = new JLabel(JL_LOGINFO_ID);// 電文ID/資訊
	protected static JTextField jtf_logInfo_ID = new JTextField();
	protected static JTextField jtf_specInfo_note = new JTextField();
	private static JButton jb_guideBook = new JButton(BTN_GUIDEBOOK);

	private static JLabel jl_exportFile = new JLabel(JL_EXPORTFILE);// 匯出檔案路徑
	protected static JTextField jtf_exportFile = new JTextField();
	protected static JCheckBox jcb_isAddEqual = new JCheckBox(JCB_ISADDEQUAL_2);
	private static JButton jb_exportFile = new JButton(BTN_EXPORTFILE);

	protected static JTextArea jta_resultS = new JTextArea(3, 93);
	protected static JTextArea jta_resultF = new JTextArea(12, 93);

	public static void setBegin(JPanel jp) {
		jpSub1.setLayout(null);
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 325));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
	}

	public static void setPosition(JPanel jp) {
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
		jtf_exportFile.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_exportFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jcb_isAddEqual.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_exportFile);
		jpSub1.add(jtf_exportFile);
		jpSub1.add(jb_exportFile);
		jpSub1.add(jcb_isAddEqual);

		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_resultS));
		jpSub2.add(new JScrollPane(jta_resultF));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	public static void setComponent(JPanel jp) {
		setAppStyle(jl_logFilePath, KEY_JL_LOGFILEPATH, APP_COLOR_LOG);// log檔路徑
		setAppStyle(jtf_logFilePath, KEY_LOGFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_logFilepath, KEY_JB_LOGFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, KEY_JB_RESETDATA, APP_COLOR_DEFAULT);

		setAppStyle(jl_specFilePath, KEY_JL_SPECFILEPATH, APP_COLOR_SPEC);// spec檔路徑
		setAppStyle(jtf_specFilePath, KEY_SPECFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_specFilepath, KEY_JB_SPECFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_clearData, KEY_JB_CLEARDATA, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_send, KEY_JL_LOGINFO_SEND, APP_COLOR_LOG);// 上行電文
		setAppStyle(jtf_logInfo_send, KEY_SEND, APP_COLOR_DEFAULT);
		setAppStyle(jl_logInfo_sendLen, KEY_JL_LOGINFO_SENDLEN, APP_COLOR_DEFAULT);
		setAppStyle(jb_readFile, KEY_JB_READFILE, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_fill, KEY_JL_LOGINFO_FILL, APP_COLOR_LOG);// 下行電文
		setAppStyle(jtf_logInfo_fill, KEY_FILL, APP_COLOR_DEFAULT);
		setAppStyle(jl_logInfo_fillLen, KEY_JL_LOGINFO_FILLLEN, APP_COLOR_DEFAULT);
		setAppStyle(jb_analysis, KEY_JB_ANALYSIS, APP_COLOR_DEFAULT);

		setAppStyle(jl_specInfo_send, KEY_JL_SPECINFO_SEND, APP_COLOR_SPEC);// 上行電文
		setAppStyle(jtf_specSendCut0, KEY_SCUT0, APP_COLOR_DEFAULT);
		setAppStyle(jtf_specSendCut, KEY_SCUT, APP_COLOR_DEFAULT);
		setAppStyle(jl_specInfo_sendLen, KEY_JL_SPECINFO_SENDLEN, APP_COLOR_DEFAULT);

		setAppStyle(jl_specInfo_fill, KEY_JL_SPECINFO_FILL, APP_COLOR_SPEC);// 下行電文
		setAppStyle(jtf_specFillCut0, KEY_FCUT0, APP_COLOR_DEFAULT);
		setAppStyle(jtf_specFillCut, KEY_FCUT, APP_COLOR_DEFAULT);
		setAppStyle(jl_specInfo_fillLen, KEY_JL_SPECINFO_FILLLEN, APP_COLOR_DEFAULT);

		setAppStyle(jl_logInfo_ID, KEY_JL_LOGINFO_ID, APP_COLOR_LOG);// 電文ID/資訊
		setAppStyle(jtf_logInfo_ID, KEY_ID, APP_COLOR_DEFAULT);
		setAppStyle4Info(jtf_specInfo_note, KEY_NOTE, APP_COLOR_DEFAULT);
		setAppStyle(jb_guideBook, KEY_JB_GUIDEBOOK, APP_COLOR_DEFAULT);

		setAppStyle(jl_exportFile, KEY_JL_EXPORTFILE, APP_COLOR_DEFAULT);
		setAppStyle(jtf_exportFile, KEY_EXPORTFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_exportFile, KEY_JB_EXPORTFILE, APP_COLOR_DEFAULT);

		setAppStyle(jcb_isAddEqual, "格式", APP_COLOR_DEFAULT);

		setAppStyle4TextArea(jta_resultS, KEY_RESULTS, APP_COLOR_DEFAULT, false);
		setAppStyle4TextArea(jta_resultF, KEY_RESULTF, APP_COLOR_DEFAULT, false);
	}

	public static void setListener(JPanel jp) {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				CutterHandler.resetData();
				showMsg(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CutterHandler.clearData();
				showMsg(MSG_SUCCESS, MSG_CLEARDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					CutterHandler.clearData();
					CutterHandler.readFile();
					showMsg(MSG_READFILE);
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), "路徑", e.getMessage());
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
					Map<String, String> m = new HashMap<>();
					m.put(KEY_RESULTS, jta_resultS.getText());
					m.put(KEY_RESULTF, jta_resultF.getText());
					m.put(KEY_EXPORTFILEPATH, jtf_exportFile.getText());
					CutterHandler.exportFile(m);
					showMsg(MSG_EXPORTFILE + "於" + jtf_exportFile.getText());
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
		btnGetPath(jb_logFilepath, jtf_logFilePath, VALUE_LOGFILEPATH);
		btnGetPath(jb_specFilepath, jtf_specFilePath, VALUE_SPECFILEPATH);

		// 取電文長度
		inputOnChange(jtf_logInfo_send);
		inputOnChange(jtf_logInfo_fill);
		inputOnChange(jtf_specSendCut0);
		inputOnChange(jtf_specSendCut);
		inputOnChange(jtf_specFillCut0);
		inputOnChange(jtf_specFillCut);

		// 點擊複製
		dbClickOnCopy(jtf_logFilePath, VALUE_LOGFILEPATH);
		dbClickOnCopy(jtf_specFilePath, VALUE_SPECFILEPATH);
		dbClickOnCopy(jtf_logInfo_ID, VALUE_ID);
		dbClickOnCopy(jtf_logInfo_send, VALUE_SEND);
		dbClickOnCopy(jtf_logInfo_fill, VALUE_FILL);
		dbClickOnCopy(jtf_specSendCut0, VALUE_SCUT0);
		dbClickOnCopy(jtf_specSendCut, VALUE_SCUT);
		dbClickOnCopy(jtf_specFillCut0, VALUE_FCUT0);
		dbClickOnCopy(jtf_specFillCut, VALUE_FCUT);
		dbClickOnCopy(jtf_specInfo_note, VALUE_NOTE);
		dbClickOnCopy(jtf_exportFile, VALUE_EXPORTFILEPATH);
		dbClickOnCopy(jta_resultS, VALUE_RESULTS);
		dbClickOnCopy(jta_resultF, VALUE_RESULTF);
	}

	public static void setEnd(JPanel jp) {
		CutterHandler.doInitial();
	}

	/*** 取電文長度 ***/
	private static void inputOnChange(JTextField jtxf) {
		jtxf.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent de) {
				getIntputLen(de);
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				getIntputLen(de);
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				getIntputLen(de);
			}

			private void getIntputLen(DocumentEvent de) {
				String name = jtxf.getName();
				String text = jtxf.getText();
				if (KEY_SEND.equals(name)) {
					try {
						jl_logInfo_sendLen.setText(Integer.toString(CutterHandler.getGBKLen(text)));
					} catch (Exception e) {
						jl_logInfo_sendLen.setText(LEN_0);
						showMsg(e.getClass().getSimpleName(), e.getMessage());
					}
				} else if (KEY_FILL.equals(name)) {
					try {
						jl_logInfo_fillLen.setText(Integer.toString(CutterHandler.getGBKLen(text)));
					} catch (Exception e) {
						jl_logInfo_fillLen.setText(LEN_0);
						showMsg(e.getClass().getSimpleName(), e.getMessage());
					}
				} else if (KEY_SCUT0.equals(name) || KEY_SCUT.equals(name)) {
					jl_specInfo_sendLen.setText(String.format(FORMAT_MSG_TGLEN, CutterHandler.getIntegerArrayLength2String(jtf_specSendCut0.getText()), CutterHandler.getIntegerArrayLength2String(jtf_specSendCut.getText())));
				} else if (KEY_FCUT0.equals(name) || KEY_FCUT.equals(name)) {
					jl_specInfo_fillLen.setText(String.format(FORMAT_MSG_TGLEN, CutterHandler.getIntegerArrayLength2String(jtf_specFillCut0.getText()), CutterHandler.getIntegerArrayLength2String(jtf_specFillCut.getText())));
				}
			}

		});
	}

}
