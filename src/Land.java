import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.util.ArrayList;


public class Land {
	
	int x;
	int y;
	
	int life = 50; //代表干旱程度
	
	boolean init = false;
	
	ArrayList<Plant> plants = new ArrayList<Plant>();
	
	public Land(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void addLife() {
		this.life += 10;
	}
	
	public void reduceLife() {
		this.life -= 10;
	}
	
	public void seed(Plant plant) {
		plants.add(plant);
	}

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	Image landImage = toolkit.getImage(this.getClass().getResource("images/background.jpg"));
	
	public void draw(Graphics g) {
		g.drawImage(landImage, x, y, null);
	}
}
