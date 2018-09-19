package cti.app.controller;

import cti.app.main.AppFrameMain;

public class AppFrame extends AppFrameMain {

	public static void executeApp() {
		isTimerWork(true);
		doCreateFrame();
		showMsg(MSG_INITIAL);
	}

	private static void doCreateFrame() {
		setFrameBegin();
		setFramePosition();
		setFrameComponent();
		setFrameListener();
		setFrameEnd();
	}

}
