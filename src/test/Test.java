package test;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test extends JFrame {
	static String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static JLabel[] labelList = new JLabel[fontList.length];

	public Test() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(fontList.length, 1));
		for (int i = 0; i < fontList.length; i++) {
			labelList[i] = new JLabel(fontList[i]);
			labelList[i].setFont(new Font(fontList[i], Font.PLAIN, 24));
			panel.add(labelList[i]);
		}
		add(new JScrollPane(panel));
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		Test f = new Test();
		for (String i : fontList)
			System.out.println(i);
	}
	// public static void main(String[] args) throws Exception {
	// System.out.println(StringUtils.leftPad("ks", 10, ' '));
	//
	// // System.out.println(System.getProperty("file.encoding"));
	// //
	// // /* 讀取檔案 */
	// // FileInputStream fis = new
	// // FileInputStream("C:\\Users\\NT83382\\Desktop\\log0914\\tt.txt");
	// //
	// // /* 建立分析器 */
	// // UniversalDetector detector = new UniversalDetector(null);
	// //
	// // int nread;
	// // byte[] buf = new byte[4096];
	// // while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
	// // /* 分析資料 */
	// // detector.handleData(buf, 0, nread);
	// // }
	// // fis.close();
	// //
	// // detector.dataEnd();
	// //
	// // /* 取得編碼格式 */
	// // String encode = detector.getDetectedCharset();
	// // System.out.println(encode);
	// }
}