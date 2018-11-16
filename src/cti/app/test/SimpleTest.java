package cti.app.test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SimpleTest {
	public static final List<String> DIR_NAMELIST_01 = Arrays.asList("USERS", "java");
	public static final List<String> DIR_NAMELIST_02 = Arrays.asList("USERS");
	public static final String STR1 = "a123AASD";

	public static void main(String[] args) {
		String test = "[^0-9a-zA-Z]";
		System.out.println("matches>"+STR1.matches(test));
		System.out.println(Pattern.compile(test).matcher(STR1).replaceAll(""));
	}

}
