package cti.app.controller;

import javax.swing.JPanel;

import cti.app.view.FindFileView;

public class FindFileController extends FindFileView {

	public static void setJPanel(JPanel jp) {
		setBegin(jp);
		setPosition(jp);
		setComponent(jp);
		setListener(jp);
		setEnd(jp);
	}
}
