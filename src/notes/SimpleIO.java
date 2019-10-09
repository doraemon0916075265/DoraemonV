package notes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SimpleIO {
	private static final String PATH1 = "C:/Users/cathay/Desktop/01.png";
	private static final String PATH2 = "C:/Users/cathay/Desktop/02.png";
	private static final String PATH3 = "C:/Users/cathay/Desktop/03.png";
//	private static final String PATH_TXT1 = "C:/Users/cathay/Desktop/01.txt";
	private static final String PATH_TXT2 = "C:/Users/cathay/Desktop/02.txt";
	private static final String PATH_TXT3 = "C:/Users/cathay/Desktop/03.txt";

	private static void consoleInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TXT3));) {
			String line;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void textFileWriteFix() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_TXT2));) {
			Scanner sc = new Scanner(System.in);
			String line;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if ("#".equals(line)) {
					break;
				} else {
					bw.write(line);
					bw.newLine();
				}
			}
			bw.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void textFileWrite() {
		try (FileWriter fw = new FileWriter(PATH_TXT2);) {
			Scanner sc = new Scanner(System.in);
			StringBuffer sb = new StringBuffer();
			String line;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if ("#".equals(line)) {
					break;
				} else {
					sb.append(line + System.lineSeparator());
				}
			}
			fw.write(sb.toString());
			fw.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	// 小檔案
	public static void smallFile4FisFos() {
		File f = new File(PATH1);
		byte[] content = new byte[(int) f.length()];
		try (FileInputStream fis = new FileInputStream(f); FileOutputStream fos = new FileOutputStream(PATH2);) {
			fis.read(content);
			fos.write(content);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	// 大檔案
	public static void largeFile4FisFos() {
		byte[] bffer = new byte[1024];
		int size;
		try (FileInputStream fis = new FileInputStream(PATH1); FileOutputStream fos = new FileOutputStream(PATH3);) {
			while ((size = fis.read(bffer)) != -1) {
				fos.write(bffer, 0, size);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void main(String[] args) {
//		SimpleIO.smallFile4FisFos();
//		SimpleIO.largeFile4FisFos();
//		SimpleIO.textFileWrite();
//		SimpleIO.textFileWriteFix();
		SimpleIO.consoleInput();
	}

}
