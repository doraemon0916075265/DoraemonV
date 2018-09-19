package cti.app.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

import cti.app.constant.CutterConstant;
import cti.app.handler.CutterHandler;
import cti.app.handler.SimpleFileHandler;

public class CutterMain extends CutterConstant {
	public static JLabel jl_logFilePath = new JLabel(JL_LOGFILEPATH);// log檔路徑
	public static JTextField jtf_logFilePath = new JTextField();
	public static JButton jb_logFilepath = new JButton();
	public static JButton jb_resetData = new JButton(BTN_RESETDATA);

	public static JLabel jl_specFilePath = new JLabel(JL_SPECFILEPATH);// spec檔路徑
	public static JTextField jtf_specFilePath = new JTextField();
	public static JButton jb_specFilepath = new JButton();
	public static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	public static JLabel jl_logInfo_send = new JLabel(JL_LOGINFO_SEND);// 上行電文
	public static JTextField jtf_logInfo_send = new JTextField();
	public static JLabel jl_logInfo_sendLen = new JLabel(LEN_0);
	public static JButton jb_readFile = new JButton(BTN_READFILE);

	public static JLabel jl_logInfo_fill = new JLabel(JL_LOGINFO_FILL);// 下行電文
	public static JTextField jtf_logInfo_fill = new JTextField();
	public static JLabel jl_logInfo_fillLen = new JLabel(LEN_0);
	public static JButton jb_analysis = new JButton(BTN_ANALYSIS);

	public static JLabel jl_specInfo_send = new JLabel(JL_SPECINFO_SEND);// 上行電文陣列
	public static JTextField jtf_specSendCut0 = new JTextField();
	public static JTextField jtf_specSendCut = new JTextField();
	public static JLabel jl_specInfo_sendLen = new JLabel(String.format(FORMAT_MSG_TGLEN, LEN_0, LEN_0));

	public static JLabel jl_specInfo_fill = new JLabel(JL_SPECINFO_FILL);// 下行電文陣列
	public static JTextField jtf_specFillCut0 = new JTextField();
	public static JTextField jtf_specFillCut = new JTextField();
	public static JLabel jl_specInfo_fillLen = new JLabel(String.format(FORMAT_MSG_TGLEN, LEN_0, LEN_0));

	public static JLabel jl_logInfo_ID = new JLabel(JL_LOGINFO_ID);// 電文ID/資訊
	public static JTextField jtf_logInfo_ID = new JTextField();
	public static JTextField jtf_specInfo_note = new JTextField();
	public static JButton jb_guideBook = new JButton(BTN_GUIDEBOOK);

	public static JLabel jl_exportFile = new JLabel(JL_EXPORTFILE);// 匯出檔案路徑
	public static JTextField jtf_exportFile = new JTextField();
	public static JButton jb_exportFile = new JButton(BTN_EXPORTFILE);

	public static JTextArea jta_resultS = new JTextArea();
	public static JTextArea jta_resultF = new JTextArea();

	public static void setBegin(JPanel jp) {
		jp.setLayout(null);
	}

	public static void setPosition(JPanel jp) {
		int row = 20;// 每一列
		/*** 第一區 ***/
		jl_logFilePath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_logFilepath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_logFilePath);
		jp.add(jtf_logFilePath);
		jp.add(jb_logFilepath);
		jp.add(jb_resetData);
		/*** 第二區 ***/
		row = 60;
		jl_specFilePath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_specFilepath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_specFilePath);
		jp.add(jtf_specFilePath);
		jp.add(jb_specFilepath);
		jp.add(jb_clearData);
		/*** 第三區 ***/
		row = 100;
		jl_logInfo_send.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_send.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jl_logInfo_sendLen.setBounds(SIZE_HOR_COL4 + 5, row, SIZE_HOR_COL7, SIZE_VER_INPUT);
		jb_readFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_logInfo_send);
		jp.add(jtf_logInfo_send);
		jp.add(jl_logInfo_sendLen);
		jp.add(jb_readFile);
		/*** 第四區 ***/
		row = 140;
		jl_logInfo_fill.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_fill.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jl_logInfo_fillLen.setBounds(SIZE_HOR_COL4 + 5, row, SIZE_HOR_COL7, SIZE_VER_INPUT);
		jb_analysis.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_logInfo_fill);
		jp.add(jtf_logInfo_fill);
		jp.add(jl_logInfo_fillLen);
		jp.add(jb_analysis);
		/*** 第五區 ***/
		row = 180;
		jl_specInfo_send.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specSendCut0.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specSendCut.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jl_specInfo_sendLen.setBounds(SIZE_HOR_COL4 + 5, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_specInfo_send);
		jp.add(jtf_specSendCut0);
		jp.add(jtf_specSendCut);
		jp.add(jl_specInfo_sendLen);
		/*** 第六區 ***/
		row = 220;
		jl_specInfo_fill.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specFillCut0.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specFillCut.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jl_specInfo_fillLen.setBounds(SIZE_HOR_COL4 + 5, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_specInfo_fill);
		jp.add(jtf_specFillCut0);
		jp.add(jtf_specFillCut);
		jp.add(jl_specInfo_fillLen);
		/*** 第七區 ***/
		row = 260;
		jl_logInfo_ID.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_logInfo_ID.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT2, SIZE_VER_INPUT);
		jtf_specInfo_note.setBounds(SIZE_HOR_COL3, row, SIZE_HOR_TEXT3, SIZE_VER_INPUT);
		jb_guideBook.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_logInfo_ID);
		jp.add(jtf_logInfo_ID);
		jp.add(jtf_specInfo_note);
		jp.add(jb_guideBook);
		/*** 第八區 ***/
		row = 300;
		jl_exportFile.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_exportFile.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_exportFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_exportFile);
		jp.add(jtf_exportFile);
		jp.add(jb_exportFile);
		/*** 第九區 ***/
		row = 340;
		jta_resultS.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_RESULT, SIZE_VER_SEND);
		jp.add(jta_resultS);
		/*** 第十區 ***/
		row = 430;
		jta_resultF.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_RESULT, SIZE_VER_FILL);
		jp.add(jta_resultF);
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

		setAppStyle4TextArea(jta_resultS, KEY_RESULTS, APP_COLOR_DEFAULT);
		setAppStyle4TextArea(jta_resultF, KEY_RESULTF, APP_COLOR_DEFAULT);
	}

	public static void setListener(JPanel jp) {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				resetData();
				doInitial();
				showMsg(MSG_RESETDATA);
			}
		});

		// 按鈕清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clearData();
				showMsg(MSG_SUCCESS, MSG_CLEARDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					clearData();
					Map<String, String> m = CutterHandler.readFile(jtf_logFilePath.getText(), jtf_specFilePath.getText());
					jtf_logInfo_ID.setText(m.get(KEY_ID));
					jtf_logInfo_send.setText(m.get(KEY_SEND));
					jtf_logInfo_fill.setText(m.get(KEY_FILL));
					jtf_specSendCut0.setText(m.get(KEY_SCUT0));
					jtf_specSendCut.setText(m.get(KEY_SCUT));
					jtf_specFillCut0.setText(m.get(KEY_FCUT0));
					jtf_specFillCut.setText(m.get(KEY_FCUT));
					jtf_specInfo_note.setText(m.get(KEY_NOTE));
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
					Map<String, String> m1 = new HashMap<>();
					m1.put(KEY_SEND, jtf_logInfo_send.getText());
					m1.put(KEY_FILL, jtf_logInfo_fill.getText());
					m1.put(KEY_SCUT0, jtf_specSendCut0.getText());
					m1.put(KEY_SCUT, jtf_specSendCut.getText());
					m1.put(KEY_FCUT0, jtf_specFillCut0.getText());
					m1.put(KEY_FCUT, jtf_specFillCut.getText());

					Map<String, String> m2 = CutterHandler.analysis(m1);
					jta_resultS.setText(m2.get(KEY_RESULTS));
					jta_resultF.setText(m2.get(KEY_RESULTF));
					jtf_specSendCut0.setText(m2.get(KEY_SCUT0));
					jtf_specSendCut.setText(m2.get(KEY_SCUT));
					jtf_specFillCut0.setText(m2.get(KEY_FCUT0));
					jtf_specFillCut.setText(m2.get(KEY_FCUT));
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
					SimpleFileHandler.exportFile(m);
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
		dbClickOnCopy(jtf_exportFile, VALUE_EXPORTFILEPATH);
		dbClickOnCopy(jta_resultS, VALUE_RESULTS);
		dbClickOnCopy(jta_resultF, VALUE_RESULTF);
	}

	public static void setEnd(JPanel jp) {
		doInitial();
	}

	/*** 重設欄位 ***/
	private static void resetData() {
		clearData();
		jta_resultS.setText("");
		jta_resultF.setText("");
	}

	/*** 清除欄位 ***/
	private static void clearData() {
		jtf_logInfo_ID.setText("");
		jtf_logInfo_send.setText("");
		jtf_logInfo_fill.setText("");
		jtf_specSendCut0.setText("");
		jtf_specSendCut.setText("");
		jtf_specFillCut0.setText("");
		jtf_specFillCut.setText("");
		jtf_specInfo_note.setText("");
	}

	/*** 第一次開啟視窗，初始化欄位 ***/
	private static void doInitial() {
		Map<String, String> m = SimpleFileHandler.InitialCutter();
		jtf_logFilePath.setText(m.get(KEY_LOGFILEPATH));
		jtf_specFilePath.setText(m.get(KEY_SPECFILEPATH));
		jtf_exportFile.setText(m.get(KEY_EXPORTFILEPATH));
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
