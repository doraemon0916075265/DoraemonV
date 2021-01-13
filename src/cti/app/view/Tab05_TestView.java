package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.Tab05_TestConstant;
import cti.app.controller.Tab05_TestController;
import cti.app.service.AppTimer;

public class Tab05_TestView extends Tab05_TestConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JLabelSimple jl1 = new JLabelSimple("格式(%s)");
	protected static JTextFieldSimple jtf1 = new JTextFieldSimple(jl1);
	private static JButtonSimple jb1 = new JButtonSimple(BTN_EXECUTE);

	private static JLabelSimple jl2 = new JLabelSimple("預留欄位");
	protected static JTextFieldSimple jtf2 = new JTextFieldSimple(jl2);
	private static JButtonSimple jb2 = new JButtonSimple(BTN_CLEARDATA);

	protected static JTextAreaSimple jta_input = new JTextAreaSimple("輸入內容", 14, true);
	protected static JTextAreaSimple jta_output = new JTextAreaSimple("輸出內容", 14, false);

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
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 100));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 260));
		jpSub3.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 260));

		// jpSub1.setBorder(BorderFactory.createLineBorder(Color.RED));
		// jpSub2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		// jpSub3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
	}

	private static void setPosition() {
		Style.resetRow();
		/*** 上半部，第一區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl1, jtf1, null, null, jb1, null));
		jpSub1.add(Arrays.asList(jl1, jtf1, jb1));

		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl2, jtf2, null, null, jb2, null));
		jpSub1.add(Arrays.asList(jl2, jtf2, jb2));

		/*** 上半部，第二區 ***/
		jpSub2.add(Arrays.asList(new JScrollPane(jta_input)));
		/*** 下半部 ***/
		jpSub3.add(Arrays.asList(new JScrollPane(jta_output)));

		jp.add(Arrays.asList(jpSub1, jpSub2, jpSub3));
	}

	private static void setComponent() {

	}

	private static void setListener() {
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					AppTimer.setTimerWork(true);
					Tab05_TestController.doMyFormat();
					showStatus(MSG_SUCCESS);
				} catch (Exception e) {
					showSatus(e);
				}
			}
		});

		// 雙擊複製
		setDbClickForCopy(jta_input);
		setDbClickForCopy(jta_output);
	}

	private static void setEnd() {

	}

}
