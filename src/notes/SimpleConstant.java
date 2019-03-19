package notes;

import java.io.File;

public class SimpleConstant {

	public static void main(String[] args) {
		String f = System.getProperty("file.separator");
		String p = System.getProperty("path.separator");
		System.out.println(f + "," + File.separator);
		System.out.println(p);

	}

}
