import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Gain extends Utility{
	public Gain(int x, int y, FarmClient farmClient) {
		super(x, y, farmClient);
	}
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image gainImage = toolkit.getImage(this.getClass().getResource("images/gain.png"));
	
	public void draw(Graphics g) {
		g.drawImage(gainImage, x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, gainImage.getWidth(null), gainImage.getHeight(null));
	}
	
	public boolean harvest() {
		for (int i = 0; i < farmClient.plants.size(); i++) {
			Plant plant = farmClient.plants.get(i);
			if(!plant.getLive()) continue;
			if(this.getRec().intersects(plant.getRec())) {
				if(plant.getIndex() != 3) {
					DialogFactory.createDialog(plant.getX() + 35, plant.getY() + 30, "主人,还没有熟呢！", 500);
					return false;
				} else {
					plant.setIndex(2);
					plant.startThread();
					return true;
				}
			}
		}
		return false;
	}

}
