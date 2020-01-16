package test;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FontList extends JFrame {
	static String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static JTextArea[] labelList = new JTextArea[fontList.length];

	public FontList() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(fontList.length, 1));
		for (int i = 0; i < fontList.length; i++) {
			labelList[i] = new JTextArea(fontList[i] + System.lineSeparator() + "abc12 3ABC中文" + System.lineSeparator() + "123AB Cabc中文");
			labelList[i].setFont(new Font(fontList[i], Font.PLAIN, 24));
			panel.add(labelList[i]);
		}
		add(new JScrollPane(panel));
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		FontList f = new FontList();
		for (String i : fontList)
			System.out.println(i);
	}
}