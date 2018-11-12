package cti.app.test;

import java.util.Arrays;
import java.util.List;

public class SimpleTest {
	public static final List<String> DIR_NAMELIST_01 = Arrays.asList("USERS", "java");
	public static final List<String> DIR_NAMELIST_02 = Arrays.asList("USERS");

	public static void main(String[] args) {
		System.out.println(DIR_NAMELIST_01.contains("123"));
		System.out.println(DIR_NAMELIST_01.contains("users"));
		System.out.println(DIR_NAMELIST_01.contains("USERS"));
	}

}
