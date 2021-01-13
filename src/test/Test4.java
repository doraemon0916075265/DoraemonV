package test;

import java.util.ArrayList;
import java.util.List;

import cti.app.service.AppService;

public class Test4 {

	public static void main(String[] args) {
		String format = "132======%s==%s==%s";
		String sa [] = {"123131","123131"};
		
		String Input = "123131";
List<String> s = new ArrayList();
s.add(Input);
s.add(Input);
s.add(Input);
		StringBuffer sOutput = new StringBuffer();
		for (String sInputLine : Input.split("\\r?\\n")) {
			sInputLine = sInputLine.trim();
			sOutput.append(String.format(format, s) + System.lineSeparator());
		}
		 System.out.println(sOutput.toString().trim());
//		System.out.println("" + appearCount(format, "%s"));
	}

	public static int appearCount(String str, String key) {
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