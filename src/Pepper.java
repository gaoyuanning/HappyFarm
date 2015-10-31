
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Pepper extends Plant{
	
	int id = 3;
	public int getId() {
		return 3;
	}

	public Pepper(int x, int y) {
		super(x, y);
	}
	
	public Pepper(int x, int y, int index) {
		super(x, y, index);
	}
	
	public Pepper(int x, int y, int index, FarmClient farmClient) {
		super(x, y, farmClient);
		this.index = index;
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image[] pepperImages = null;
	static {
		pepperImages = new Image[] {
			toolkit.getImage(Apple.class.getResource("images/pepper/pepper1.png")),
			toolkit.getImage(Apple.class.getResource("images/pepper/pepper2.png")),
			toolkit.getImage(Apple.class.getResource("images/pepper/pepper3.png")),
			toolkit.getImage(Apple.class.getResource("images/pepper/pepper4.png")),
			toolkit.getImage(Apple.class.getResource("images/pepper/pepper5.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/sere.png"))
		};
	}
	
	public void draw(Graphics g) {
		g.drawImage(pepperImages[index], x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, pepperImages[index].getWidth(null), pepperImages[index].getHeight(null));
	}
}










