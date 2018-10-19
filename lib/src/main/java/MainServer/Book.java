package MainServer;

import java.io.Serializable;

public class Book implements Serializable {
	String name;
	
	public Book(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

}
