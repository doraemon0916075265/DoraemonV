package cti.app.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSimple {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public static void main(String[] args) {

		// "2018/10/2"
		Date d = new Date();
		try {
			System.out.println(sdf.parse("2018/10/1").getTime());
			System.out.println(sdf.parse("2018/10/2").getTime());
			System.out.println(sdf.parse("2018/10/3").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Calendar c = new Calendar("2018/10/2");
		// System.out.println(new C("2018/10/2"));
	}
}
