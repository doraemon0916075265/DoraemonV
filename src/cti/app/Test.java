package cti.app;

public class Test {
	public static void main(String[] args) {
		String s = "~$cket_FNSODO0000.doc**vvvv";
//		System.out.println(s.matches("~\\$.*"));
		System.out.println(s.replaceAll("\\*",".*"));
	}
}
