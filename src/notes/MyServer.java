package notes;

import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		System.out.println(MyServer.class.getSimpleName() + "is start");
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket socket = serverSocket.accept();
			Runnable r = new MyServerWorker(socket);
			Thread t = new Thread(r);
			t.start();
		}
	}

}
