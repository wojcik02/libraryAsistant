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
			System.out.println("Po��czy�em z :" + socket);

			bookToClientOs = new ObjectOutputStream(socket.getOutputStream());
			bookToClientIs = new ObjectInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());

			// Ilo�� ksi��ek do pobrania z serwera
			int qty = din.readInt();

			System.out.println("Ilo�� ksi��ek od serwera: " + qty);

			for (int i = 0; i < qty; i++) {
				System.out.println("Pobieram now� ksi��k�");
				Book obj = (Book) bookToClientIs.readObject();
				System.out.println("Pobra�em ksi��ke: " + obj.getName());
				
			//Pytanie o ok�adk�
			dout.writeUTF(obj.getURL());
			BufferedImage image = ImageIO.read(bookToClientIs);
			System.out.println(image);
			
			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JLabel picLabel = new JLabel(new ImageIcon(image));
			JOptionPane.showMessageDialog(dialog, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
			
			}

			// Odpowied� dla serwera po pobraniu ksi��ek
			System.out.println("Sko�czy�em obiera� ksi��ki");
			dout.writeUTF("Dzi�ki za ksi�zki!");

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
