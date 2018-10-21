package MainServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
	DataOutputStream dout;
	DataInputStream din;
	ObjectInputStream bookToClientIs;
	ObjectOutputStream bookToClientOs;
	Socket socket;

	public ClientHandler(Socket clientSocket) {
		socket = clientSocket;
	}

	public void run() {

		try {
			System.out.println("Po��czy�em z :" + socket);

			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());

			// Ilo�� ksi��ek jak� wy�lemy do klienta z DB
			int qty = 2;
			dout.writeInt(qty);

			// Wysy�anie ksi��ek wersja 1
			Book book = new Book("Ma�y Ksi��e");
			bookToClientOs.writeObject(book);
			System.out.println("Serwer wys�a� ksi��k�: " + book.getName());

			Book book2 = new Book("Moby Dick");
			bookToClientOs.writeObject(book2);
			System.out.println("Serwer wys�a� ksi��k�: " + book2.getName());

			// Odpowied klienta po pobraniu ksi��ek
			System.out.println("Client message: " + din.readUTF());

			while (true) {
				String cMessage = din.readUTF();
				if (cMessage.equals("end")) {
					bookToClientIs.close();
					bookToClientOs.close();
					socket.close();
					System.out.println("Koniec po��czenia");
					break;
				} else {
					System.out.println(cMessage);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
