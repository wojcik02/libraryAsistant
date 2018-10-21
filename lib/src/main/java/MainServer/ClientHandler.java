package MainServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	ObjectInputStream bookToClientIs;
	ObjectOutputStream bookToClientOs;
	
	public ClientHandler(Socket clientSocket) {
		
		
        try {
        	
        	Socket socket = clientSocket;
    		
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
			//socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
