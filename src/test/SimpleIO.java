package test;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SimpleIO {
	private static final String PATH1 = "C:/Users/cathay/Desktop/01.png";
	private static final String PATH2 = "C:/Users/cathay/Desktop/02.png";
	private static final String PATH3 = "C:/Users/cathay/Desktop/03.png";
	private static final String PATHF1 = "C:/Users/cathay/Desktop/01.txt";
	private static final String PATHF2 = "C:/Users/cathay/Desktop/02.txt";

	public static void textFile() {
		try (FileWriter fw = new FileWriter(PATHF2);) {
			 Scanner sc = new Scanner(System.in);
			 while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			int input = System.in.read();
			System.out.println(input);
//			while ((input = c.readLine()) != null) {
//				fw.write(input + System.lineSeparator());
//			}
//			Console c = System.console();
//			String input;
//			while ((input = c.readLine()) != null) {
//				System.out.println(">>" + input);
//				fw.write(input);
//			}
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void largeFile4FisFos() {
		byte[] bffer = new byte[1024];
		try (FileInputStream fis = new FileInputStream(PATH1); FileOutputStream fos = new FileOutputStream(PATH3);) {
			int size;
			while ((size = fis.read(bffer)) != -1) {
				fos.write(bffer, 0, size);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SimpleIO.textFile();
//		SimpleIO.smallFile4FisFos();
//		SimpleIO.largeFile4FisFos();
	}

}
