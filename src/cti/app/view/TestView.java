package cti.app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.TestConstant;

public class TestView extends TestConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JLabelSimple jl1 = new JLabelSimple("btn1");
	protected static JTextFieldSimple jtf1 = new JTextFieldSimple(jl1);
	private static JButtonFilePath jb1 = new JButtonFilePath(jtf1);
	private static JButtonSimple jb2 = new JButtonSimple("btn1");

	private static JLabelSimple jl2 = new JLabelSimple("btn2");
	protected static JTextFieldSimple jtf2 = new JTextFieldSimple(jl2);
	private static JButtonFilePath jb3 = new JButtonFilePath(jtf2);
	private static JButtonSimple jb4 = new JButtonSimple("btn2");

	private static JLabelSimple jl3 = new JLabelSimple("btn3");
	protected static JTextFieldSimple jtf3 = new JTextFieldSimple(jl3);
	private static JLabelSimple jl4 = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb5 = new JButtonSimple("btn3");

	private static JLabelSimple jl5 = new JLabelSimple("btn4");
	protected static JTextFieldSimple jtf4 = new JTextFieldSimple(jl5);
	private static JLabelSimple jl6 = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb6 = new JButtonSimple("btn4");

	private static JTextAreaSimple jta_result = new JTextAreaSimple("Test內容", 10, true);
	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

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
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 325));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
	}

	private static void setPosition() {
		/*** 上半部，第一區 ***/
		Style.resetRow();
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl1, jtf1, jb1, null, jb2, null));
		jpSub1.add(Arrays.asList(jl1, jtf1, jb1, jb2));
		/*** 第二區 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl2, jtf2, jb3, null, jb4, null));
		jpSub1.add(Arrays.asList(jl2, jtf2, jb3, jb4));
		/*** 第三區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl3, jtf3, null, jl4, jb5, null));
		jpSub1.add(Arrays.asList(jl3, jtf3, jl4, jb5));
		/*** 第四區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl5, jtf4, null, jl6, jb6, null));
		jpSub1.add(Arrays.asList(jl5, jtf4, jl6, jb6));

		/*** 下半部 ***/
		jpSub2.add(Arrays.asList(new JScrollPane(jta_result)));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {

	}

	private static void setListener() {
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String endpoint = "http://dicagap01.cathdevelop.intra.uwccb/CTIService/CTIService.svc?wsdl";
			}
		});

	}

	private static void setEnd() {

	}

}
