package cti.app.controller;

import javax.swing.JPanel;

import cti.app.main.FindFileMain;

public class FindFileController extends FindFileMain {

	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}
}
