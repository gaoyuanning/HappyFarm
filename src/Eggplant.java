import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Eggplant extends Plant{
	
	public int getId() {
		return 2;
	}
	
	public Eggplant(int x, int y) {
		super(x, y);
	}
	
	public Eggplant(int x, int y, int index) {
		super(x, y, index);
	}
	
	public Eggplant(int x, int y, int index, FarmClient farmClient) {
		super(x, y, farmClient);
		this.index = index;
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image[] eggplantImages = null;
	static {
		eggplantImages = new Image[] {
			toolkit.getImage(Apple.class.getResource("images/eggplant/eggplant1.png")),
			toolkit.getImage(Apple.class.getResource("images/eggplant/eggplant2.png")),
			toolkit.getImage(Apple.class.getResource("images/eggplant/eggplant3.png")),
			toolkit.getImage(Apple.class.getResource("images/eggplant/eggplant4.png")),
			toolkit.getImage(Apple.class.getResource("images/eggplant/eggplant5.png")),
			toolkit.getImage(Apple.class.getResource("images/apple/sere.png"))
		};
	}
	
	public void draw(Graphics g) {
		g.drawImage(eggplantImages[index], x, y, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, eggplantImages[index].getWidth(null), eggplantImages[index].getHeight(null));
	}

}









