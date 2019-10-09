package cti.app.appService;

import cti.app.constant.AppConstant;

public class Converter {

	public static String millisToSeconds(long millis) {
		if (millis > 1000L) {
			return String.format(AppConstant.FORMAT_MSG_TIMER_S, millis / 1000d);
		} else {
			return String.format(AppConstant.FORMAT_MSG_TIMER_MS, millis);
		}
	}

}
