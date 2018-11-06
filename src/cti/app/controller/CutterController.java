package cti.app.controller;

import javax.swing.JPanel;

import cti.app.view.CutterView;

public class CutterController extends CutterView {

	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}

}
