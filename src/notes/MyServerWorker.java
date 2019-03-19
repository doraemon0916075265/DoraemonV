package notes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyServerWorker implements Runnable {

	private Socket socket;

	public MyServerWorker(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try (PrintStream output = new PrintStream(socket.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			String line; // 2
			while ((line = input.readLine()) != null) {
				System.out.println(socket + ":" + line);
				output.println("From Server:" + line); // 3
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
