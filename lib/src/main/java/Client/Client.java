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
			System.out.println("Po³¹czy³em z :" + socket);

			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());

			// Iloœæ ksi¹¿ek do pobrania z serwera
			int qty = din.readInt();

			System.out.println("Iloœæ ksi¹¿ek od serwera: " + qty);

			for (int i = 0; i < qty; i++) {
				Book obj = (Book) bookToClientIs.readObject();
				System.out.println("Pobra³em ksi¹¿ke: " + obj.getName());
			}

			// Odpowied¿ dla serwera po pobraniu ksi¹¿ek
			System.out.println("Skoñczy³em obieraæ ksi¹¿ki");
			dout.writeUTF("Dziêki za ksi¹zki!");

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
