package cti.app.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import cti.app.constant.TestConstant;

public class TestView extends TestConstant {
	private static JPanel jp = new JPanel();

	private static JTextArea jta_result = new JTextArea(10, 80);
	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();

	public static JPanel createView() {
		setBegin();
		setPosition();
		setComponent();
		setListener();
		setEnd();
		return jp;
	}

	private static void setBegin() {
	}

	private static void setPosition() {
	}

	private static void setComponent() {
		jp.setBorder(new LineBorder(Color.RED));
		jta_result.setBorder(new LineBorder(Color.BLUE));

		jpSub1.setBorder(new LineBorder(Color.YELLOW));
		jpSub2.setBorder(new LineBorder(Color.ORANGE));

		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
		jpSub2.add(new JScrollPane(jta_result));

		jp.add(jpSub1);
		jp.add(jpSub2);
	}

	private static void setListener() {

	}

	private static void setEnd() {

	}

}
