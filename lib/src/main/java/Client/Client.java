package Client;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import MainServer.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
				System.out.println("Pobieram now¹ ksi¹¿kê");
				Book obj = (Book) bookToClientIs.readObject();
				System.out.println("Pobra³em ksi¹¿ke: " + obj.getName());
				
			//Pytanie o ok³adkê
			dout.writeUTF(obj.getURL());
			BufferedImage image = ImageIO.read(bookToClientIs);
			System.out.println(image);
			
			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JLabel picLabel = new JLabel(new ImageIcon(image));
			JOptionPane.showMessageDialog(dialog, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
			
			}

			// Odpowied¿ dla serwera po pobraniu ksi¹¿ek
			System.out.println("Skoñczy³em obieraæ ksi¹¿ki");
			dout.writeUTF("Dziêki za ksi¹zki!");

			dout.writeUTF("Co dalej?");
		//	bookToClientIs.close();
		//	bookToClientOs.close();
		//	socket.close();
			

			
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
