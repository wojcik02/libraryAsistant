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
			
			while(bookToClientIs != null) {
			Book obj1 = (Book) bookToClientIs.readObject();
			System.out.println("Pobra�em ksi��ke: " + obj1.getName());
			}
			
			bookToClientIs.close();
			bookToClientOs.close();
			//socket.close();

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
