package test;

import java.io.FileInputStream;
import org.mozilla.universalchardet.UniversalDetector;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("file.encoding"));

		/* 讀取檔案 */
		FileInputStream fis = new FileInputStream("C:\\Users\\NT83382\\Desktop\\log0914\\tt.txt");

		/* 建立分析器 */
		UniversalDetector detector = new UniversalDetector(null);

		int nread;
		byte[] buf = new byte[4096];
		while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
			/* 分析資料 */
			detector.handleData(buf, 0, nread);
		}
		fis.close();

		detector.dataEnd();

		/* 取得編碼格式 */
		String encode = detector.getDetectedCharset();
		System.out.println(encode);
	}
}