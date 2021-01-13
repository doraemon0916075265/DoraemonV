package cti.app.service;

import cti.app.bean.TestBean;

public class Tab05_TestService extends AppService {
	protected static TestBean MyFormats(TestBean tb) {
		StringBuffer sOutput = new StringBuffer();
		String apperArray[] = new String[appearCount(tb.getFormat(), "%s")];

		for (String sInputLine : tb.getInput().split("\\r?\\n")) {
			sInputLine = sInputLine.trim();

			for (int i = 0; i < apperArray.length; i++) {
				apperArray[i] = sInputLine;
			}

			sOutput.append(String.format(tb.getFormat(), apperArray) + System.lineSeparator());
		}
		tb.setOutput(sOutput.toString().trim());
		return tb;
	}

	//
	private static int appearCount(String str, String key) {
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(key, index)) != -1) {
			str = str.substring(index + key.length());
			index = 0;
			count++;
		}
		return count;
	}
}
