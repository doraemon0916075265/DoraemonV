package cti.app;

import cti.app.service.AppTimer;
import cti.app.view.AppView;

public class App extends AppView {
	public static void main(String[] args) {
		AppTimer.setTimerWork(true);
		executeApp();
		showStatus(MSG_INITIAL);
	}
}