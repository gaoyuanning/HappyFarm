import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Spade extends Utility{
	
	public Spade(int x, int y,  FarmClient farmClient) {
		super(x, y, farmClient);
	}

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image spadeImage = toolkit.getImage(this.getClass().getResource("images/spade.png"));
	
	public void draw(Graphics g) {
		g.drawImage(spadeImage, x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, spadeImage.getWidth(null), spadeImage.getHeight(null));
	}
	
	
	public boolean uproot() {
		for (int i = 0; i < farmClient.plants.size(); i++) {
			Plant plant = farmClient.plants.get(i);
			if(this.getRec().intersects(plant.getRec())) {
				farmClient.plants.remove(i);
				plant.setLive(false);
				return true;
			}
		}
		return false;
	}

}
