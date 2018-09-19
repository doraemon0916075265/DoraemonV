package cti.app.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cti.app.constant.TextFindFileConstant;
import cti.app.handler.SimpleFileHandler;

public class TextFindFileMain extends TextFindFileConstant {
	public static JLabel jl_searchPath = new JLabel(JL_SEARCHPATH);// 欲查路徑
	public static JTextField jtf_searchPath = new JTextField();
	public static JButton jb_resetData = new JButton(BTN_RESETDATA);

	public static JLabel jl_searchText = new JLabel(JL_SEARCHTEXT);// 字串查詢
	public static JTextField jtf_searchText = new JTextField();
	public static JButton jb_searchText = new JButton(BTN_SEARCH);

	public static JTextArea jta_result = new JTextArea();//

	public static void setBegin(JPanel jp) {
		jp.setLayout(null);
	}

	public static void setPosition(JPanel jp) {
		int row = 20;// 每一列
		/*** 第一區 ***/
		jl_searchPath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_searchPath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_searchPath);
		jp.add(jtf_searchPath);
		jp.add(jb_resetData);
		/*** 第二區 ***/
		row = 60;
		jl_searchText.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_searchText.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1, SIZE_VER_INPUT);
		jb_searchText.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jp.add(jl_searchText);
		jp.add(jtf_searchText);
		jp.add(jb_searchText);
		/*** 第十區 ***/
		row = 430;
		jta_result.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_RESULT, SIZE_VER_FILL);
		jp.add(jta_result);
	}

	public static void setComponent(JPanel jp) {
		setAppStyle(jl_searchPath, "jl_searchPath", APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, "jb_resetData", APP_COLOR_DEFAULT);

		setAppStyle(jl_searchText, "jl_searchText", APP_COLOR_DEFAULT);
		setAppStyle(jb_searchText, "jb_searchText", APP_COLOR_DEFAULT);

		setAppStyle4TextArea(jta_result, "jta_result", APP_COLOR_DEFAULT);
	}

	public static void setListener(JPanel jp) {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doInitial();
				showMsg(MSG_RESETDATA);
			}
		});
	}

	public static void setEnd(JPanel jp) {
		doInitial();
	}

	private static void doInitial() {
		jtf_searchPath.setText(SimpleFileHandler.getRootPath4Desktop());
	}

}
