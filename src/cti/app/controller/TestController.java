package cti.app.controller;

import javax.swing.JPanel;

import cti.app.main.TestMain;

public class TestController extends TestMain {
	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}
}