package cti.app;

import cti.app.view.AppView;

public class App extends AppView {
	public static void main(String[] args) {
		isTimerWork(true);
		executeApp();
		showSatus(MSG_INITIAL);
	}
}