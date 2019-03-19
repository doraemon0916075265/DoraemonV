package notes;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
//	cd C:\Java201903\eclipse-workspace\ThreadDemo\bin
//	set PATH=C:\Java201903\openjdk11\bin;%PATH%
//	java networking.Client
	public static void main(String[] args) {
		System.out.println(MyClient.class.getSimpleName() + "is start");
		try (Socket socket = new Socket("127.0.0.1", 9999);
				PrintStream output = new PrintStream(socket.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			Console console = System.console();
			String keyboardInput;
			while ((keyboardInput = console.readLine()) != null) {
				output.println(keyboardInput); // 1
				String line = input.readLine(); // 4
				System.out.println(line);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
