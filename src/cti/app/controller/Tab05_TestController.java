package cti.app.controller;

import cti.app.bean.TestBean;
import cti.app.view.Tab05_TestView;

public class Tab05_TestController extends Tab05_TestView {

	private static TestBean tb = new TestBean();

	public static void doMyFormat() {
		getAllProperties();
		tb = MyFormats(tb);
		setAllProperties();
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		tb.setFormat(jtf1.getText());
		tb.setInput(jta_input.getText());
		tb.setOutput(jta_output.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jtf1.setText(tb.getFormat());
		jta_input.setText(tb.getInput());
		jta_output.setText(tb.getOutput());
	}
}
