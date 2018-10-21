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
				 
		         @SuppressWarnings("resource")
				ServerSocket server = new ServerSocket(1000);
		         System.out.println("Server Start");
		       for(int i=1;i<3;i++) {
		    	  
		            socket = server.accept();
		            System.out.println("W¹tek :"+i);

		            ClientHandler newClient = new ClientHandler(socket);

		            newClient.start();
		          
		       }
		       socket.close();
		           
		       
		      } catch (Exception e) {
		      }


		
	}

	public static void main(String as[]) {
		new Server();
	}

}
