package Tennis;

import java.io.Serializable;

public class Player implements Serializable{
	

	private static final long serialVersionUID = -3206379370190979737L;
	String name = "";
	
	

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
