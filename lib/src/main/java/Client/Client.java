package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import MainServer.Book;

public class Client {
	Socket socket;
	DataInputStream din;
	DataOutputStream dout;
	ObjectInputStream bookToClientIs;
	ObjectOutputStream bookToClientOs;

	public Client() {

		System.out.println("Podaj IP:");
		Scanner scanner = new Scanner(System.in);
		String ip = scanner.nextLine();
		scanner.close();

		try {

			socket = new Socket(ip, 1000);
			System.out.println("Po��czy�em z :" + socket);

			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());

			// Ilo�� ksi��ek do pobrania z serwera
			int qty = din.readInt();

			System.out.println("Ilo�� ksi��ek od serwera: " + qty);

			for (int i = 0; i < qty; i++) {
				Book obj = (Book) bookToClientIs.readObject();
				System.out.println("Pobra�em ksi��ke: " + obj.getName());
			}

			// Odpowied� dla serwera po pobraniu ksi��ek
			System.out.println("Sko�czy�em obiera� ksi��ki");
			dout.writeUTF("Dzi�ki za ksi�zki!");

			dout.writeUTF("end");
			bookToClientIs.close();
			bookToClientOs.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void getBooks() {

	}

	public static void main(String as[]) {
		new Client();
	}

}
