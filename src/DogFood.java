import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;


public class DogFood extends Utility{
	
	public DogFood(int x, int y, FarmClient farmClient) {
		super(x, y, farmClient);
	}
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	Image dogFoodImage = toolkit.getImage(this.getClass().getResource("images/dogfood.png"));
	
	public void draw(Graphics g) {
		g.drawImage(dogFoodImage, x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, dogFoodImage.getWidth(null), dogFoodImage.getHeight(null));
	}
	
	public boolean food() {
		if(this.getRec().intersects(farmClient.dog.getRec())) {
			DialogFactory.createDialog(x + 150, y - 50, "³Ô±¥ÁË£¬Ð»Ð»Ö÷ÈË£¡");
			farmClient.dog.eat();
			return true;
		}
		return false;
	}
}















