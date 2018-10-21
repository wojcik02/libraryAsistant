package MainServer;

import java.net.*;

public class Server {

	Socket socket;
	Book book;

	@SuppressWarnings("resource")
	public Server() {

		int clientsNumber;

		try {
			clientsNumber = 2;
			ServerSocket server = new ServerSocket(1000);
			Socket socket = new Socket();

			System.out.println("Server Start");

			for (int i = 1; i <= clientsNumber; i++) {

				socket = server.accept();

				// Nowy w¹tek dla klienta
				ClientHandler newClient = new ClientHandler(socket);
				newClient.start();

			}
			// socket.close();

		} catch (Exception e) {
		}

	}

	public static void main(String as[]) {
		new Server();
	}

}
