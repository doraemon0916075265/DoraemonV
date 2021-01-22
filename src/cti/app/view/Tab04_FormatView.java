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
import cti.app.constant.Tab04_FormatConstant;
import cti.app.controller.Tab04_FormatController;
import cti.app.service.AppTimer;

public class Tab04_FormatView extends Tab04_FormatConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JLabelSimple jl_specFilePath = new JLabelSimple(JL_FILEPATH_SPEC); // spec檔路徑
	protected static JTextFieldSimple jtf_specFilePath = new JTextFieldSimple(jl_specFilePath);
	private static JButtonFilePath jb_specFilepath = new JButtonFilePath(jtf_specFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple(BTN_RESETDATA);

	private static JLabelSimple jl_formatFilePath = new JLabelSimple(JL_FILEPATH_FORMAT); // spec檔路徑
	protected static JTextFieldSimple jtf_formatFilePath = new JTextFieldSimple(jl_formatFilePath);
	private static JButtonFilePath jb_formatFilePath = new JButtonFilePath(jtf_formatFilePath);
	private static JButtonSimple jb_readFile = new JButtonSimple(BTN_READFILE);

	private static JLabelSimple jl_specID_format = new JLabelSimple(JL_SPECID_FORMAT);
	protected static JComboBoxSimple<String> jcb_specID_Format = new JComboBoxSimple<String>();
	protected static JTextFieldSimple jtf_format = new JTextFieldSimple(jl_specID_format);
	private static JButtonSimple jb_execute = new JButtonSimple(BTN_EXECUTE);

	protected static JTextAreaSimple jta_input = new JTextAreaSimple("輸入內容", 11, true);
	protected static JTextAreaSimple jta_output = new JTextAreaSimple("輸出內容", 11, true);

	private static JLabelSimple jl_exportFile = new JLabelSimple(JL_EXPORTFILE); // 匯出檔案路徑
	protected static JTextFieldSimple jtf_exportFilePath = new JTextFieldSimple(jl_exportFile);
	private static JButtonSimple jb_exportFile = new JButtonSimple(BTN_EXPORTFILE);

	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();
	private static JPanelSimple jpSub3 = new JPanelSimple();

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
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 170));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 225));
		jpSub3.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 225));

		// jpSub1.setBorder(BorderFactory.createLineBorder(Color.RED));
		// jpSub2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		// jpSub3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	private static void setPosition() {
		Style.resetRow();
		/*** Block1，Row1 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_specFilePath, jtf_specFilePath, jb_specFilepath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_specFilePath, jtf_specFilePath, jb_specFilepath, jb_resetData));
		/*** Block1，Row2 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_formatFilePath, jtf_formatFilePath, jb_formatFilePath, null, jb_readFile, null));
		jpSub1.add(Arrays.asList(jl_formatFilePath, jtf_formatFilePath, jb_formatFilePath, jb_readFile));
		/*** Block1，Row3 ***/
		Style.setBounds(Style.MODEL_JL_JC_NULL_JTF_NULL_BTN, Arrays.asList(null, jl_specID_format, jcb_specID_Format, null, jtf_format, null, jb_execute, null));
		jpSub1.add(Arrays.asList(jl_specID_format, jcb_specID_Format, jtf_format, jb_execute));
		/*** Block1，Row4 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_exportFile, jtf_exportFilePath, null, null, jb_exportFile, null));
		jpSub1.add(Arrays.asList(jl_exportFile, jtf_exportFilePath, jb_exportFile));

		/*** Block2，Area1 ***/
		jpSub2.add(Arrays.asList(new JScrollPane(jta_input)));

		/*** Block3，Area1 ***/
		jpSub3.add(Arrays.asList(new JScrollPane(jta_output)));

		jp.add(Arrays.asList(jpSub1, jpSub2, jpSub3));
	}

	private static void setComponent() {
		// setStyle
		jl_specFilePath.setForeground(APP_COLOR_SPEC);
		jl_formatFilePath.setForeground(APP_COLOR_FORMAT);
		jl_specID_format.setForeground(APP_COLOR_SPEC);
		// setEditable
		jcb_specID_Format.setEditable(true);
	}

	private static void setListener() {
		// 重設
		jb_resetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Tab04_FormatController.doFormShow();
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
		jcb_specID_Format.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				readFile();
			}
		});

		// 執行
		jb_execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					Tab04_FormatController.doStringFormat();
					showStatus(MSG_SUCCESS);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 匯出
		jb_exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					Tab04_FormatController.doExport();
					showStatus(MSG_EXPORTFILE + "於" + jtf_exportFilePath.getText());
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 雙擊複製
		setDbClickForCopy(jtf_specFilePath);
		setDbClickForCopy(jtf_formatFilePath);
		setDbClickForCopy(jtf_format);
		setDbClickForCopy(jtf_exportFilePath);
		setDbClickForCopy(jta_input);
		setDbClickForCopy(jta_output);
	}

	private static void setEnd() {
		Tab04_FormatController.doFormShow();
	}

	private static void readFile() {
		try {
			Tab04_FormatController.doReadFile();
			genPulldownMenu(jcb_specID_Format, genJCB4SpecID_Format(jtf_specFilePath.getText()));
			showStatus(MSG_READFILE + getPulldownItem(jcb_specID_Format));
		} catch (FileNotFoundException fnfe) {
			jcb_specID_Format.removeAllItems();
		} catch (Exception e) {
			showSatus(e);
		}
	}

}