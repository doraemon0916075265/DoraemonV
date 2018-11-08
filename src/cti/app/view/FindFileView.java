package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import cti.app.constant.FindFileConstant;
import cti.app.handler.AppHandler;
import cti.app.handler.FindFileHandler;
import cti.app.service.FindFileService;

public class FindFileView extends FindFileConstant {
	private static JPanel jp = new JPanel();
	private static FindFileService fs = new FindFileService();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_searchPath = new JLabel(JL_SEARCHPATH);// 欲查路徑
	private static JTextField jtf_searchPath = new JTextField();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);

	private static JLabel jl_searchCondition = new JLabel("搜尋條件");// 字串查詢
	private static JButton jb_clearData = new JButton(BTN_CLEARDATA);

	private static JLabel jl_byText = new JLabel("字串");// 字串查詢
	private static JTextField jtf_byText = new JTextField();
	private static JCheckBox jcb_byText = new JCheckBox();
	private static JButton jb_search = new JButton(BTN_SEARCH);

	private static JLabel jl_byFilename = new JLabel("檔案名稱");// 字串查詢
	private static JTextField jtf_byFilename = new JTextField();

	private static JLabel jl_byFilenameExtension = new JLabel("副檔名");
	private static JTextField jtf_byFilenameExtension = new JTextField();

	private static JLabel jl_byFilenameExtension_Ignore = new JLabel("副檔名(忽略)");
	private static JTextField jtf_byFilenameExtension_Ignore = new JTextField();

	private static JLabel jl_byModify_greaterThan = new JLabel("修改日(≧)");
	private static JXDatePicker jxdp_byModify_greaterThan = new JXDatePicker();
	private static JLabel jl_byModify_lessThan = new JLabel("修改日(≦)");
	private static JXDatePicker jxdp_byModify_lessThan = new JXDatePicker();

	private static JTextArea jta_result = new JTextArea(10, 92);

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

	public static void setPosition() {
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
		jl_byText.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byText.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jcb_byText.setBounds(SIZE_HOR_COL4_MSG, row, SIZE_VER_INPUT, SIZE_VER_INPUT);
		jb_search.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_byText);
		jpSub1.add(jtf_byText);
		jpSub1.add(jcb_byText);
		jpSub1.add(jb_search);
		/*** 第四區 ***/
		row += 40;
		jl_byFilename.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilename.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilename);
		jpSub1.add(jtf_byFilename);
		/*** 第五區 ***/
		row += 40;
		jl_byFilenameExtension.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jl_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_byFilenameExtension_Ignore.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_TEXT5, SIZE_VER_INPUT);
		jpSub1.add(jl_byFilenameExtension);
		jpSub1.add(jtf_byFilenameExtension);
		jpSub1.add(jl_byFilenameExtension_Ignore);
		jpSub1.add(jtf_byFilenameExtension_Ignore);
		/*** 第六區 ***/
		row += 40;
		jl_byModify_greaterThan.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_byModify_greaterThan.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jl_byModify_lessThan.setBounds(SIZE_HOR_COLMID, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jxdp_byModify_lessThan.setBounds(SIZE_HOR_COLMID + SIZE_HOR_LABEL1, row, SIZE_HOR_CALENDAR1, SIZE_VER_INPUT);
		jpSub1.add(jl_byModify_greaterThan);
		jpSub1.add(jxdp_byModify_greaterThan);
		jpSub1.add(jl_byModify_lessThan);
		jpSub1.add(jxdp_byModify_lessThan);
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	public static void setComponent() {
		fs.setAppStyle(jl_searchPath, "欲查路徑", APP_COLOR_DEFAULT);
		fs.setAppStyle(jb_resetData, "jb_resetData", APP_COLOR_DEFAULT);

		fs.setAppStyle(jl_searchCondition, "jl_searchCondition", APP_COLOR_SEARCH_CRITERIA);

		fs.setAppStyle(jl_byText, "by字串", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jb_clearData, "jb_clearData", APP_COLOR_DEFAULT);

		fs.setAppStyle(jl_byFilenameExtension, "jl_byFilenameExtension", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jtf_byFilenameExtension, "jtf_byFilenameExtension", APP_COLOR_DEFAULT);
		fs.setAppStyle(jb_search, "jb_search", APP_COLOR_DEFAULT);

		fs.setAppStyle(jl_byFilename, "jl_byFilename", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jtf_byFilename, "jtf_byFilename", APP_COLOR_DEFAULT);

		fs.setAppStyle(jl_byFilenameExtension_Ignore, "jl_byFilenameExtension_Ignore", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jtf_byFilenameExtension_Ignore, "jtf_byFilenameExtension_Ignore", APP_COLOR_DEFAULT);

		fs.setAppStyle(jl_byModify_greaterThan, "jl_byModify_greaterThan", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jxdp_byModify_greaterThan, "jxdp_byModify_greaterThan", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jl_byModify_lessThan, "jl_byModify_lessThan", APP_COLOR_SEARCH_CRITERIA);
		fs.setAppStyle(jxdp_byModify_lessThan, "jxdp_byModify_lessThan", APP_COLOR_SEARCH_CRITERIA);

		fs.setAppStyle4TextArea(jta_result, "結果", APP_COLOR_DEFAULT, false);
	}

	public static void setListener() {
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
		jb_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					isTimerWork(true);
					jta_result.setText("");
					Map<String, String> m = new HashMap<>();
					m.put(KEY_SEARCHPATH, jtf_searchPath.getText());
					m.put(KEY_BYTEXT, jtf_byText.getText());
					m.put(KEY_BYFILENAME, jtf_byFilename.getText());
					m.put(KEY_FILENAMEEXTENSION, jtf_byFilenameExtension.getText());
					m.put(KEY_FILENAMEEXTENSION_IGNORE, jtf_byFilenameExtension_Ignore.getText());
					if (jxdp_byModify_greaterThan.getDate() != null) {
						if (new Date().after(jxdp_byModify_greaterThan.getDate())) {
							m.put(KEY_MODIFYGREATERTHAN, APPDATE_SDF.format(jxdp_byModify_greaterThan.getDate()));
						} else {
							jxdp_byModify_greaterThan.setDate(null);
						}
					}
					if (jxdp_byModify_lessThan.getDate() != null) {
						if (new Date().after(jxdp_byModify_lessThan.getDate())) {
							m.put(KEY_MODIFYLESSTHAN, APPDATE_SDF.format(AppHandler.getDateCalculator(jxdp_byModify_lessThan.getDate(), 0, 0, 1)));
						} else {
							jxdp_byModify_lessThan.setDate(null);
						}
					}

					m = FindFileHandler.findConditionFile(m);
					// System.out.println("mm" + m);

					jtf_byFilenameExtension.setText(m.get(KEY_FILENAMEEXTENSION));
					jtf_byFilenameExtension_Ignore.setText(m.get(KEY_FILENAMEEXTENSION_IGNORE));
					jta_result.setText(m.get(KEY_RESULT));
					showMsg(MSG_ANALYSIS);
				} catch (Exception e) {
					showMsg(e.getClass().getSimpleName(), e.getMessage());
				}
			}
		});

		fs.dbClickOnCopy(jtf_searchPath);
		fs.dbClickOnCopy(jtf_byText);
		fs.dbClickOnCopy(jta_result);
	}

	public static void setEnd() {
		doInitial();
	}

	/*** 重設欄位 ***/
	private static void resetData() {
		clearData();
		doInitial();
	}

	/*** 清除欄位 ***/
	private static void clearData() {
		jtf_byText.setText("");
		jta_result.setText("");
		jxdp_byModify_greaterThan.setDate(null);
		jxdp_byModify_lessThan.setDate(null);
	}

	private static void doInitial() {
		// jtf_searchPath.setText(AppHandler.getDesktopRootPath());
		jtf_searchPath.setText(AppHandler.getDesktopRootPath() + "\\test");

		jtf_byText.setText("");

		jtf_byFilenameExtension.setText("[*]");
		jtf_byFilenameExtension_Ignore.setText("[~*,*.vfl]");

		jxdp_byModify_greaterThan.setDate(null);
		jxdp_byModify_lessThan.setDate(null);
	}

}
