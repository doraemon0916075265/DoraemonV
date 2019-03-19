package notes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println(MyClient.class.getSimpleName() + "is start");
		// Object
//		try (ObjectOutputStream oos = new ObjectOutputStream(new Socket("20.2.6.57", 9999).getOutputStream());) {
//			oos.writeObject(oos);
//			oos.flush();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// String
		Socket s = new Socket("20.2.6.57", 9999);
		PrintStream ps = new PrintStream(s.getOutputStream(), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		ps.println("hahaha");
		String line = br.readLine();
		System.out.println("line=" + line);
	}

}
