package cti.app;

import cti.app.service.AppTimer;
import cti.app.view.MainView;

public class App extends MainView {
	public static void main(String[] args) {
		AppTimer.setTimerWork(true);
		executeApp();
		showStatus(MSG_INITIAL);
	}
}