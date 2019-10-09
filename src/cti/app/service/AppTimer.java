package cti.app.service;

import cti.app.appService.Converter;

public class AppTimer {
	private static long now;
	public static boolean isTimerOn;
	public static String timerString;

	public static void setTimerWork(boolean isOn) {
		timerString = "";
		isTimerOn = isOn;
		if (isOn) {
			now = System.currentTimeMillis();
		} else {
			timerString = Converter.millisToSeconds(System.currentTimeMillis() - now);
		}
		System.out.println("isTimerWork=" + isTimerOn + timerString);
	}

}
