package cti.app;

import cti.app.controller.AppFrame;

public class App extends AppFrame {
	public static void main(String[] args) {
		isTimerWork(true);
		executeApp();
		showMsg(MSG_INITIAL);
	}
}