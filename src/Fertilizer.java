
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Fertilizer{
	int x;
	int y;
	FarmClient farmClient = null;

	public Fertilizer(int x, int y, FarmClient farmClient) {
		this.x = x;
		this.y = y;
		this.farmClient =farmClient;
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
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image fertilizerImage = toolkit.getImage(this.getClass().getResource("images/fertilizer.png"));
	
	public void draw(Graphics g) {
		g.drawImage(fertilizerImage, x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, fertilizerImage.getWidth(null), fertilizerImage.getHeight(null));
	}
	
	
	public boolean food() {
		for (int i = 0; i < farmClient.plants.size(); i++) {
			Plant plant = farmClient.plants.get(i);
			if(!plant.getLive()) continue;
			if(this.getRec().intersects(plant.getRec())) {
				plant.eat();
				DialogFactory.createDialog(plant.getX() + 35, plant.getY() + 30, "Ð»Ð»Ö÷ÈË£¡", 1000);
				return true;
			}
		}
		return false;
	}

}
