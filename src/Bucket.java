import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;



public class Bucket extends Utility{
	
	public Bucket(int x, int y, FarmClient farmClient) {
		super(x, y, farmClient);
	}

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	Image bucketImage = toolkit.getImage(this.getClass().getResource("images/shuihu.png"));
	
	public void draw(Graphics g) {
		g.drawImage(bucketImage, x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, bucketImage.getWidth(null), bucketImage.getHeight(null));
	}
	
	public boolean water() {
		for (int i = 0; i < farmClient.plants.size(); i++) {
			Plant plant = farmClient.plants.get(i);
			if(!plant.getLive()) continue;
			if(this.getRec().intersects(plant.getRec())) {
				plant.water();
				DialogFactory.createDialog(plant.getX() + 35, plant.getY() + 30, "Ð»Ð»Ö÷ÈË£¡", 1000);
				return true;
			}
		}
		return false;
	}
}















