package MainServer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ClientHandler extends Thread {
	DataOutputStream dout;
	DataInputStream din;
	ObjectInputStream bookToClientIs;
	ObjectOutputStream bookToClientOs;
	Socket socket;
	BufferedImage bi;

	public ClientHandler(Socket clientSocket) {
		socket = clientSocket;
	}

	public void run() {

		try {
			System.out.println("Po³¹czy³em z :" + socket);

			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());

			// Iloœæ ksi¹¿ek jak¹ wyœlemy do klienta z DB
			int qty = 2;
			dout.writeInt(qty);

			// Wysy³anie ksi¹¿ek wersja 1
			Book book = new Book("Ma³y Ksi¹¿e");
			bookToClientOs.writeObject(book);
			System.out.println("Serwer wys³a³ ksi¹¿kê: " + book.getName());
			
			
			//Odpowiedna pytanie o ok³adkê
			
			String imageURL = din.readUTF();
			File inputFile = new File("images/"+imageURL);
			BufferedImage image  = ImageIO.read(inputFile);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg",baos);
			bookToClientOs.write(baos.toByteArray());
			
			
			//

			Book book2 = new Book("Moby Dick");
			bookToClientOs.writeObject(book2);
			System.out.println("Serwer wys³a³ ksi¹¿kê: " + book2.getName());

			// Odpowied klienta po pobraniu ksi¹¿ek

			while (true) {
				
				try {
					String cMessage = din.readUTF();
					if (cMessage.equals("end")) {
						bookToClientIs.close();
						bookToClientOs.close();
						socket.close();
						System.out.println("Koniec po³¹czenia");
						break;
					} else {
						System.out.println(cMessage);
					}
					
				}catch(Exception e){
					
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
