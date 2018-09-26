package cti.app.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cti.app.constant.FindFileConstant;
import cti.app.handler.AppHandler;
import cti.app.handler.FindFileHandler;

public class FindFileMain extends FindFileConstant {
	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_searchPath = new JLabel(JL_SEARCHPATH);// 欲查路徑
	private static JTextField jtf_searchPath = new JTextField();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);

	private static JLabel jl_searchText = new JLabel(JL_SEARCHTEXT);// 字串查詢
	private static JTextField jtf_searchText = new JTextField();
	private static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	private static JLabel jl_filenameExtension = new JLabel("副檔名");
	private static JTextField jtf_filenameExtension = new JTextField();

	private static JLabel jl_filenameExtension_Ignore = new JLabel("副檔名(忽略)");
	private static JTextField jtf_filenameExtension_Ignore = new JTextField();

	private static JButton jb_searchText = new JButton(BTN_SEARCH);

	private static JTextArea jta_result = new JTextArea(10, 92);

	public static void setBegin(JPanel jp) {
		jpSub1.setLayout(null);
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 415));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 210));
		// jpSub1.setBorder(new LineBorder(Color.RED));
		// jpSub2.setBorder(new LineBorder(Color.RED));
	}

	public static void setPosition(JPanel jp) {
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
		jl_searchText.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_searchText.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchText);
		jpSub1.add(jtf_searchText);
		jpSub1.add(jb_clearData);
		/*** 第三區 ***/
		row += 40;
		jl_filenameExtension.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_filenameExtension.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_searchText.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_filenameExtension);
		jpSub1.add(jtf_filenameExtension);
		jpSub1.add(jb_searchText);
		/*** 第四區 ***/
		row += 40;
		jl_filenameExtension_Ignore.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_filenameExtension_Ignore.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jpSub1.add(jl_filenameExtension_Ignore);
		jpSub1.add(jtf_filenameExtension_Ignore);
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	public static void setComponent(JPanel jp) {
		setAppStyle(jl_searchPath, "jl_searchPath", APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, "jb_resetData", APP_COLOR_DEFAULT);

		setAppStyle(jl_searchText, "jl_searchText", APP_COLOR_DEFAULT);
		setAppStyle(jb_clearData, "jb_clearData", APP_COLOR_DEFAULT);

		setAppStyle(jl_filenameExtension, "jl_filenameExtension", APP_COLOR_DEFAULT);
		setAppStyle(jtf_filenameExtension, "jtf_filenameExtension", APP_COLOR_DEFAULT);
		setAppStyle(jb_searchText, "jb_searchText", APP_COLOR_DEFAULT);

		setAppStyle(jl_filenameExtension_Ignore, "jl_filenameExtension_Ignore", APP_COLOR_DEFAULT);
		setAppStyle(jtf_filenameExtension_Ignore, "jtf_filenameExtension_Ignore", APP_COLOR_DEFAULT);

		setAppStyle4TextArea(jta_result, "jta_result", APP_COLOR_DEFAULT, false);
	}

	public static void setListener(JPanel jp) {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetData();
				showMsg(MSG_RESETDATA);
			}
		});

		// 清除
		jb_clearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clearData();
				showMsg(MSG_SUCCESS, MSG_CLEARDATA);
			}
		});

		// 查詢
		jb_searchText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					Map<String, String> m = new HashMap<>();
					m.put(KEY_SEARCHPATH, jtf_searchPath.getText());
					m.put(KEY_SEARCHTEXT, jtf_searchText.getText());
					m.put(KEY_FILENAMEEXTENSION, jtf_filenameExtension.getText());
					m.put(KEY_FILENAMEEXTENSION_IGNORE, jtf_filenameExtension_Ignore.getText());
					m = FindFileHandler.findConditionFile(m);
					System.out.println(m);
					jta_result.setText(m.get(KEY_RESULT));
					showMsg(MSG_ANALYSIS);
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), e.getMessage());
					isTimerWork(false);
				}
			}
		});

		dbClickOnCopy(jtf_searchPath, "欲查路徑");
		dbClickOnCopy(jtf_searchText, "字串");
		dbClickOnCopy(jta_result, "結果");
	}

	public static void setEnd(JPanel jp) {
		doInitial();
	}

	/*** 重設欄位 ***/
	private static void resetData() {
		clearData();
		doInitial();
	}

	/*** 清除欄位 ***/
	private static void clearData() {
		jtf_searchText.setText("");
		jta_result.setText("");
	}

	private static void doInitial() {
		jtf_searchPath.setText(AppHandler.getDesktopRootPath());
		jtf_searchPath.setText("C:\\Users\\NT83382\\Desktop\\test");

		jtf_searchText.setText("");

		jtf_filenameExtension.setText("[*]");
		jtf_filenameExtension_Ignore.setText("[~*, *.vfl, *.lnk, *.rar]");
	}

}
