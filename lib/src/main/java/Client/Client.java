package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

		try {

			socket = new Socket(ip, 1000);
			System.out.println("Po³¹czy³em z :" + socket);
			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			Book obj1 = (Book) bookToClientIs.readObject();
			System.out.println("Pobra³em ksi¹¿ke: " + obj1.getName());
			Book obj2 = (Book) bookToClientIs.readObject();
			System.out.println("Pobra³em ksi¹¿ke: " + obj2.getName());
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
