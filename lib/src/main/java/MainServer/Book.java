package MainServer;

import java.io.Serializable;

import javafx.scene.image.Image;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String imageURL;

	public Book(String name) {
		this.name = name;
		this.imageURL = "bookcover.jpg";
	}

	public String getName() {
		return name;
	}
	public String getURL() {
		return imageURL;

	}

}
