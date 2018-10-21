package MainServer;

import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;

	public Book(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
