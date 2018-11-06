package cti.app.controller;

import javax.swing.JPanel;

import cti.app.view.TestView;

public class TestController extends TestView {
	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}
}
