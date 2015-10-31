import java.awt.Graphics;


public class Utility {
	int x;
	int y;
	FarmClient farmClient = null;
	
	public Utility(int x, int y, FarmClient farmClient) {
		this.x = x;
		this.y = y;
		this.farmClient = farmClient;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void draw(Graphics g) {
	}
	
}
