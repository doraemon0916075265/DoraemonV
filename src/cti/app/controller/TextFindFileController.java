package cti.app.controller;

import javax.swing.JPanel;

import cti.app.main.TextFindFileMain;

public class TextFindFileController extends TextFindFileMain {

	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}
}
