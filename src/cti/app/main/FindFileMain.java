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

import org.jdesktop.swingx.JXDatePicker;

import cti.app.constant.FindFileConstant;
import cti.app.handler.AppHandler;
import cti.app.handler.FindFileHandler;

public class FindFileMain extends FindFileConstant {
	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_searchPath = new JLabel(JL_SEARCHPATH);// 欲查路徑
	private static JTextField jtf_searchPath = new JTextField();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);

	private static JLabel jl_searchCondition = new JLabel("搜尋條件");// 字串查詢

	private static JLabel jl_searchText = new JLabel("字串");// 字串查詢
	private static JTextField jtf_searchText = new JTextField();
	private static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	private static JLabel jl_filenameExtension = new JLabel("副檔名");
	private static JTextField jtf_filenameExtension = new JTextField();

	private static JLabel jl_filenameExtension_Ignore = new JLabel("副檔名(忽略)");
	private static JTextField jtf_filenameExtension_Ignore = new JTextField();

	private static JLabel jl_modifyGreaterThan = new JLabel("修改日(≧)");
	private static JXDatePicker jxdp_modifyLessThan = new JXDatePicker();
	private static JLabel jl_modifyLessThan = new JLabel("修改日(≦)");
	private static JXDatePicker jxdp_modifyGreaterThan = new JXDatePicker();

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
		jl_searchCondition.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jb_clearData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchCondition);
		jpSub1.add(jb_clearData);
		/*** 第三區 ***/
		row += 40;
		jl_searchText.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_searchText.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_searchText.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_searchText);
		jpSub1.add(jtf_searchText);
		jpSub1.add(jb_searchText);
		/*** 第四區 ***/
		row += 40;
		jl_filenameExtension.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_filenameExtension.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jl_filenameExtension_Ignore.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_filenameExtension_Ignore.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_filenameExtension);
		jpSub1.add(jtf_filenameExtension);
		jpSub1.add(jl_filenameExtension_Ignore);
		jpSub1.add(jtf_filenameExtension_Ignore);
		/*** 第五區 ***/
		row += 40;
		jl_modifyLessThan.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_modifyLessThan.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jl_modifyGreaterThan.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_modifyGreaterThan.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jpSub1.add(jl_modifyLessThan);
		jpSub1.add(jxdp_modifyLessThan);
		jpSub1.add(jl_modifyGreaterThan);
		jpSub1.add(jxdp_modifyGreaterThan);
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	public static void setComponent(JPanel jp) {
		setAppStyle(jl_searchPath, "jl_searchPath", APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, "jb_resetData", APP_COLOR_DEFAULT);

		setAppStyle(jl_searchCondition, "jl_searchCondition", APP_COLOR_SEARCH_CRITERIA);

		setAppStyle(jl_searchText, "jl_searchText", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jb_clearData, "jb_clearData", APP_COLOR_DEFAULT);

		setAppStyle(jl_filenameExtension, "jl_filenameExtension", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_filenameExtension, "jtf_filenameExtension", APP_COLOR_DEFAULT);
		setAppStyle(jb_searchText, "jb_searchText", APP_COLOR_DEFAULT);

		setAppStyle(jl_filenameExtension_Ignore, "jl_filenameExtension_Ignore", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jtf_filenameExtension_Ignore, "jtf_filenameExtension_Ignore", APP_COLOR_DEFAULT);

		setAppStyle(jl_modifyLessThan, "jl_modifyLessThan", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jxdp_modifyLessThan, "jxdp_modifyLessThan", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jl_modifyGreaterThan, "jl_modifyGreaterThan", APP_COLOR_SEARCH_CRITERIA);
		setAppStyle(jxdp_modifyGreaterThan, "jxdp_modifyGreaterThan", APP_COLOR_SEARCH_CRITERIA);

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
					// System.out.println(jxdp_modifyBegin.getEditor());
					// System.out.println(jxdp_modifyBegin.getInputMap());
					// System.out.println(jxdp_modifyBegin.getInputContext());
					m = FindFileHandler.findConditionFile(m);
					// System.out.println(m);
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
		jtf_searchPath.setText(AppHandler.getDesktopRootPath() + "\\test");

		jtf_searchText.setText("123");

		jtf_filenameExtension.setText("[*]");
		jtf_filenameExtension_Ignore.setText("[~*, *.vfl]");
	}

}
