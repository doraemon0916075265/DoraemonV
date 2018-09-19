package cti.app.controller;

import javax.swing.JPanel;

import cti.app.main.CutterMain;

public class CutterController extends CutterMain {

	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}

}
