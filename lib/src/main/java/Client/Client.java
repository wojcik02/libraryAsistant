package Client;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import MainServer.Book;

public class Client {
	Socket socket;
	DataInputStream din;
	DataOutputStream dout;
	ObjectInputStream bookToClientIs;
	ObjectOutputStream bookToClientOs;

	public Client() {
		try {
			
			
			 	while(true) {
		            socket = new Socket("localhost", 1000);
		           bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
		            bookToClientIs = new ObjectInputStream(socket.getInputStream());
		            Book obj1 =  (Book) bookToClientIs.readObject();
		            System.out.println("Pobra³em ksi¹¿ke: "+obj1.getName());
		            Book obj2 =  (Book) bookToClientIs.readObject();
		            System.out.println("Pobra³em ksi¹¿ke: "+obj2.getName());
		            bookToClientIs.close();
		            bookToClientOs.close();
		            socket.close();
			 	} 
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
