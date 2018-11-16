package cti.app.test;

import java.util.Arrays;
import java.util.List;

public class SimpleTest {
	public static final List<String> DIR_NAMELIST_01 = Arrays.asList("USERS", "java");
	public static final List<String> DIR_NAMELIST_02 = Arrays.asList("USERS");
	public static final String STR1 = "AASD.csv";

	public static void main(String[] args) {
		// String test = "[\u4e00-\u9fa5\\w_]*\\.[txt]*";
		String test = "[\u4e00-\u9fa5\\w_]+\\.[\\w_]*";
		System.out.println("matches>" + STR1.matches(test));
		// System.out.println(Pattern.compile(test).matcher(STR1).replaceAll(""));
	}

}
