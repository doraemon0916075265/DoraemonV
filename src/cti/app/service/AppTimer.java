package cti.app.service;

import cti.app.constant.AppConstant;

public class AppTimer {
	private static boolean isTimerOn;
	private static long now;
	private static long timerLong;
	private static String timerString;

	public static void setTimerWork(boolean isOn) {
		setTimerLong(0L);
		setTimerString("");
		setTimerOn(isOn);
		if (isOn) {
			setNow(System.currentTimeMillis());
		} else {
			setTimerLong(System.currentTimeMillis() - getNow());
			setTimerString((getTimerLong() >= 1000L) ? (String.format(AppConstant.FORMAT_MSG_TIMER_S, getTimerLong() / 1000d)) : (String.format(AppConstant.FORMAT_MSG_TIMER_MS, getTimerLong())));
		}
		System.out.println("isTimerWork=" + isTimerOn() + getTimerString());
	}

	public static boolean isTimerOn() {
		return isTimerOn;
	}

	public static void setTimerOn(boolean isTimerOn) {
		AppTimer.isTimerOn = isTimerOn;
	}

	public static long getNow() {
		return now;
	}

	public static void setNow(long now) {
		AppTimer.now = now;
	}

	public static long getTimerLong() {
		return timerLong;
	}

	public static void setTimerLong(long timerLong) {
		AppTimer.timerLong = timerLong;
	}

	public static String getTimerString() {
		return timerString;
	}

	public static void setTimerString(String timerString) {
		AppTimer.timerString = timerString;
	}

}
