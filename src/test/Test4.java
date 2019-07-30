package test;

import org.apache.commons.lang3.StringUtils;

public class Test4 {

	public static void main(String[] args) {
		String str = "";
		for (int i = 0; i < 10; i++) {
			str =str + "xx幣" + i+",";
		}
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isBlank("　"));
		//System.out.println(String.format("Hello%sMen", str));

	}

}
