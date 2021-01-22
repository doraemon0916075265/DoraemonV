package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JComboBoxSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.Tab02_SpecManagerConstant;
import cti.app.controller.Tab02_SpecManagerController;
import cti.app.service.AppTimer;

public class Tab02_SpecManagerView extends Tab02_SpecManagerConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

	private static JLabelSimple jl_specFilePath = new JLabelSimple(JL_SPECFILEPATH); // spec檔路徑
	protected static JTextFieldSimple jtf_specFilePath = new JTextFieldSimple(jl_specFilePath);
	private static JButtonFilePath jb_specFilepath = new JButtonFilePath(jtf_specFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_specID = new JLabelSimple(JL_SPECID); // ID選單
	protected static JComboBoxSimple<String> jcb_specID = new JComboBoxSimple<String>();
	private static JButtonSimple jb_readFile = new JButtonSimple(BTN_READFILE);

	protected static JTextAreaSimple jta_result = new JTextAreaSimple("spec內容", 26, false);

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
		Style.resetRow();
		/*** Block1，Row1 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_specFilePath, jtf_specFilePath, jb_specFilepath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_specFilePath, jtf_specFilePath, jb_specFilepath, jb_resetData));
		/*** Block1，Row2 ***/
		Style.setBounds(Style.MODEL_JL_JC1_NULL_JC2_NULL_JC3_NULL_JC4_JC5_BTN, Arrays.asList(null, jl_specID, jcb_specID, null, null, null, null, null, null, null, jb_readFile, null));
		jpSub1.add(Arrays.asList(jl_specID, jcb_specID, jb_readFile));
		
		/*** Block2，Area1 ***/
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {
		jl_specFilePath.setForeground(APP_COLOR_SPEC);
		jcb_specID.setEditable(true);
		jl_specID.setForeground(APP_COLOR_SPEC);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Tab02_SpecManagerController.doFormShow();
				showStatus(MSG_RESETDATA);
			}
		});

		// 讀檔
		jb_readFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				AppTimer.setTimerWork(true);
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

		// 點擊複製
		setDbClickForCopy(jtf_specFilePath);
		setDbClickForCopy(jta_result);
	}

	private static void setEnd() {
		Tab02_SpecManagerController.doFormShow();
	}

	private static void readFile() {
		try {
			Tab02_SpecManagerController.doReadFile();
			genPulldownMenu(jcb_specID, genJCB4SpecID(jtf_specFilePath.getText()));
			showStatus(MSG_READFILE + getPulldownItem(jcb_specID));
		} catch (FileNotFoundException fnfe) {
			jcb_specID.removeAllItems();
		} catch (Exception e) {
			showSatus(e);
		}
	}
}