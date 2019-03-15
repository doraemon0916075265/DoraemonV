package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cti.app.constant.SpecInfoConstant;
import cti.app.controller.SpecInfoController;

public class SpecInfoView extends SpecInfoConstant {
	private static JPanel jp = new JPanel();

	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	private static JLabel jl_specFilePath = new JLabel(JL_SPECFILEPATH);// spec檔路徑
	protected static JTextField jtf_specFilePath = new JTextField();
	private static JButton jb_specFilepath = new JButton();
	private static JButton jb_resetData = new JButton(BTN_RESETDATA);
	private static JLabel jl_specID = new JLabel("ID選單");
	protected static JComboBox<String> jcb_specID = new JComboBox<String>();

	private static JButton jb_readFile = new JButton(BTN_READFILE);

	protected static JTextArea jta_result = new JTextArea(30, 93);

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
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 90));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 535));
	}

	private static void setPosition() {
		int row = 15;// 每一列
		/*** 上半部，第一區 ***/
		jl_specFilePath.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jtf_specFilePath.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_specFilepath.setBounds(SIZE_HOR_COL2 + SIZE_HOR_TEXT1 - SIZE_HOR_BTNF, row, SIZE_HOR_BTNF, SIZE_VER_INPUT);
		jb_resetData.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_specFilePath);
		jpSub1.add(jtf_specFilePath);
		jpSub1.add(jb_specFilepath);
		jpSub1.add(jb_resetData);
		/*** 第二區 ***/
		row += 40;
		jl_specID.setBounds(SIZE_HOR_COL1, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jcb_specID.setBounds(SIZE_HOR_COL2, row, SIZE_HOR_LABEL1, SIZE_VER_INPUT);
		jb_readFile.setBounds(SIZE_HOR_COL8, row, SIZE_HOR_BTN, SIZE_VER_INPUT);
		jpSub1.add(jl_specID);
		jpSub1.add(jcb_specID);
		jpSub1.add(jb_readFile);
		/*** 下半部 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	private static void setComponent() {
		setAppStyle(jl_specFilePath, null, APP_COLOR_SPEC);// spec檔路徑
		setAppStyle(jtf_specFilePath, NAME_SPECFILEPATH, APP_COLOR_DEFAULT);
		setAppStyle(jb_specFilepath, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_resetData, null, APP_COLOR_DEFAULT);
		setAppStyle(jl_specID, null, APP_COLOR_SPEC);// ID選單
		setAppStyle4ComboBox(jcb_specID, null, APP_COLOR_DEFAULT);
		setAppStyle(jb_readFile, null, APP_COLOR_DEFAULT);
		setAppStyle4TextArea(jta_result, NAME_RESULT, APP_COLOR_DEFAULT, false);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isTimerWork(true);
				SpecInfoController.doInitial();
				setEnterTab();
				showMsg(MSG_RESETDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				readFile();
			}
		});

		// 選單動作
		jcb_specID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				readFile();
			}
		});

		// 取檔案路徑
		btnGetPath(jb_specFilepath, jtf_specFilePath);

		// 點擊複製
		dbClickOnCopy(jtf_specFilePath);
		dbClickOnCopy(jta_result);

	}

	private static void setEnd() {
		SpecInfoController.doInitial();
	}

	public static void setEnterTab() {
		genPulldownMenu(jcb_specID, genJCB4SpecID(jtf_specFilePath.getText()));
	}

	private static void readFile() {
		isTimerWork(true);
		try {
			SpecInfoController.readFile();
			setEnterTab();
			showMsg(MSG_READFILE + getPulldownItem(jcb_specID));
		} catch (Exception e) {
			showMsg(e);
		}
	}
}
