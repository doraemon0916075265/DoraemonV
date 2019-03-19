package notes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileInputOutput {
	public static void textFile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/cathay/Desktop/output.txt"));
		Console console = System.console();
		String input = console.readLine();
		while (input != null) {
			System.out.println("input=" + input);
			bw.write(input);
			bw.newLine();

			input = console.readLine();
		}
		bw.close();
	}

	public static void consoleInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/cathay/Desktop/output.txt"));

		String input = br.readLine();
		while (input != null) {
			System.out.println("input=" + input);
			bw.write(input);
			bw.newLine();

			input = br.readLine();
		}
		br.close();
		bw.close();
	}

	public static void main(String[] args) throws Exception {
		FileInputOutput.consoleInput();

//		FileInputOutput.textFile();
//		FileInputOutput.�p�ɮ�();
//		FileInputOutput.�j�ɮ�();
	}

	public static void tryWithResource() {
		String inputFilePath = "C:/Users/cathay/Desktop/01.jpg";
		byte[] buffer = new byte[1024 * 8];
		try (FileInputStream fis = new FileInputStream(inputFilePath);
				FileOutputStream fos = new FileOutputStream("C:/Users/cathay/Desktop/02.jpg");) {
			int size = fis.read(buffer);
			while (size != -1) {
				System.out.println(size);
				fos.write(buffer, 0, size);
				size = fis.read(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void tryCatchFinally() {
		String inputFilePath = "C:/Users/cathay/Desktop/01.jpg";
		byte[] buffer = new byte[1024 * 8];

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(inputFilePath);
			fos = new FileOutputStream("C:/Users/cathay/Desktop/02.jpg");
			int size = fis.read(buffer);
			while (size != -1) {
				System.out.println(size);
				fos.write(buffer, 0, size);
				size = fis.read(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void xxx() throws Exception {
		String inputFilePath = "C:/Users/cathay/Desktop/01.jpg";
		byte[] buffer = new byte[1024 * 8];

		FileInputStream fis = new FileInputStream(inputFilePath);
		FileOutputStream fos = new FileOutputStream("C:/Users/cathay/Desktop/02.jpg");
		int size = fis.read(buffer);
		while (size != -1) {
			System.out.println(size);
			fos.write(buffer, 0, size);
			size = fis.read(buffer);
		}
		fis.close();
		fos.close();
	}

	public static void OO() throws Exception {
		String inputFilePath = "C:/Users/cathay/Desktop/01.jpg";
		File inputFile = new File(inputFilePath);
		long length = inputFile.length();
		byte[] content = new byte[(int) length];

		FileInputStream fis = new FileInputStream(inputFile);
		int size = fis.read(content);
		fis.close();

		System.out.println(length + ":" + size);

		FileOutputStream fos = new FileOutputStream("C:/Users/cathay/Desktop/02.jpg");
		fos.write(content);
		fos.close();
	}
}
