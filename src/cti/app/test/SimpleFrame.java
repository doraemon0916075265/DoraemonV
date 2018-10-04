package cti.app.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleFrame {
	private static JFrame jf = new JFrame();
	private static JPanel jp = new JPanel();
	private static JPanel jpSub1 = new JPanel();
	private static JPanel jpSub2 = new JPanel();
	private static JTextField jtxf1 = new JTextField();
	private static JTextField jtxf2 = new JTextField();
	private static JButton jb = new JButton("執行");

	public static void main(String[] args) {
		setFrameBegin();

		jtxf1.setBounds(20, 20, 900, 25);
		jtxf2.setBounds(20, 60, 900, 25);
		jb.setBounds(950, 60, 80, 25);

		jpSub1.add(jtxf1);
		jpSub1.add(jtxf2);
		jpSub1.add(jb);

		jpSub1.setLayout(null);
		jpSub2.setLayout(null);

		jpSub1.setPreferredSize(new Dimension(1080, 300));
		jpSub2.setPreferredSize(new Dimension(1080, 300));

		jp.add(jpSub1);
		jp.add(jpSub2);
		jf.add(jp);

		jtxf1.setText("你好$阿");
		jtxf2.setText("$");

		setListener();
		setFrameEnd();
	}

	public static void setFrameBegin() {
		jf = new JFrame("");
		jf.setLocale(JComponent.getDefaultLocale());
		jf.setSize(1080, 720);// 視窗大小
		jf.setLocationRelativeTo(null);// 視窗置中
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(new BorderLayout());
		jf.setResizable(false);
		jf.setLayout(new BorderLayout());
	}

	public static void setListener() {
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String t1 = jtxf1.getText();
				String t2 = jtxf2.getText();
				System.out.println();
				try {
					t2 = t2.replaceAll("\\\\", "\\\\\\\\");
					t2 = t2.replaceAll("\\^", "\\\\\\^");
					t2 = t2.replaceAll("\\$", "\\\\\\$");
					t2 = t2.replaceAll("\\*", "\\\\\\*");
					t2 = t2.replaceAll("\\+", "\\\\\\+");
					t2 = t2.replaceAll("\\?", "\\\\\\?");
					t2 = t2.replaceAll("\\{", "\\\\\\{");
					t2 = t2.replaceAll("\\}", "\\\\\\}");
					t2 = t2.replaceAll("\\.", "\\\\\\.");
					t2 = t2.replaceAll("\\(", "\\\\\\(");
					t2 = t2.replaceAll("\\)", "\\\\\\)");
					t2 = t2.replaceAll("\\|", "\\\\\\|");
					t2 = t2.replaceAll("\\[", "\\\\\\[");
					t2 = t2.replaceAll("\\]", "\\\\\\]");
					t2 = ".*" + t2 + ".*";
					System.out.println("t2=" + t2);
					System.out.println(t1.matches(t2));
				} catch (Exception e) {
					System.out.println("有Exception" + e.getClass());
				}
			}
		});
	}

	public static void setFrameEnd() {
		jf.setVisible(true);
	}

}

// JCheckBox checkbox = new JCheckBox("JCheckBox");
// JRadioButton radiobutton = new JRadioButton("JRadiobutton");
// JButton button = new JButton("JButton");
// JLabel label = new JLabel("JLabel");
// JTextArea textarea = new JTextArea("JTextArea");