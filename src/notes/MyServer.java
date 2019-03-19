package notes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		System.out.println(MyServer.class.getSimpleName() + "is start");
		// Object
//		try (ObjectInputStream ois = new ObjectInputStream(new ServerSocket(9999).accept().getInputStream());) {
//			ois.readObject();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//String
		Socket s = new ServerSocket(9999).accept();
		
		BufferedReader bw = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String line = bw.readLine();
	}

}
