import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;


public class Apple extends Plant{

	public int getId() {
		return 1;
	}
	
	public Apple(int x, int y) {
		super(x, y);
	}
	
	public Apple(int x, int y, int index) {
		super(x, y, index);
	}
	
	public Apple(int x, int y, int index, FarmClient farmClient) {
		super(x, y, farmClient);
		this.index = index;
	}
	
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image[] appleImages = null;
	static {
		appleImages = new Image[] {
			toolkit.getImage(Apple.class.getResource("images/apple/apple1.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/apple2.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/apple3.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/apple4.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/apple5.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/sere.png"))
		};
	}
	
	public void draw(Graphics g) {
		g.drawImage(appleImages[index], x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, appleImages[index].getWidth(null), appleImages[index].getHeight(null));
	}

}










