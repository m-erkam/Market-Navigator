
package question;

public class House {
	
	private String name;
	private int x;
	private int y;
	
	/* Create Getters, setters for the House class for each attribute */
	
	public House(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	public String getName() {	// Getter method for name attribute
		return name;
	}

	public void setName(String name) {	// Setter method for name attribute
		this.name = name;
	}

	public int getX() {	// Getter method for X attribute
		return x;
	}

	public void setX(int x) {	// Setter method for X attribute
		this.x = x;
	}

	public int getY() {	// Getter method for Y attribute
		return y;
	}

	public void setY(int y) {	// Setter method for Y attribute
		this.y = y;
	}

	@Override
	public String toString() {	// I create to string method to see what I did
		return "House [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	
	
}

