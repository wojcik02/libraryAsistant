package MainServer;

import java.io.*;
import java.net.*;

public class Server {

	ServerSocket ss;
	Socket socket;
	ObjectOutputStream bookToClientOs;
	ObjectInputStream bookToClientIs;
	Book book;

	public Server() {
		
			
			 try {
				 
		         ServerSocket server = new ServerSocket(1000);
		         System.out.println("Server Start");
		       
		            socket = server.accept();
		            System.out.println("Po³¹czy³em z :"+socket);

		            bookToClientIs = new ObjectInputStream(socket.getInputStream());
		            bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
		            Book book = new Book("Ma³y Ksi¹¿e");
		            bookToClientOs.writeObject(book);
		            System.out.println("Serwer wys³a³ ksi¹¿kê: "+book.getName());

		            Book book2 = new Book("Moby Dick");
		            bookToClientOs.writeObject(book2);
		            System.out.println("Serwer wys³a³ ksi¹¿kê: "+book2.getName());
		            bookToClientIs.close();
		            bookToClientOs.close();
		            socket.close();
		       
		      } catch (Exception e) {
		      }


		
	}

	public static void main(String as[]) {
		new Server();
	}

}
