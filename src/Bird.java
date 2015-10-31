import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Bird {
	int x;
	int y;
	int life = 20;
	
	int index = 0;	//上限为6
	int step = 1;
	int direction = 0; //0代表向右，1代表向左
	
	public Bird(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image birdLImage = toolkit.getImage(Bird.class.getResource("images/bird1L.png"));
	static Image birdRImage = toolkit.getImage(Bird.class.getResource("images/bird1R.png"));
	
	public void draw(Graphics g) {
		if(direction == 0) {
			g.drawImage(birdRImage, x, y, null);
		} else {
			g.drawImage(birdLImage, x, y, null);
		}
		move();
	}
	
	public void move() {
		if (index <= 150) {
			index += 1;
			if (0 == direction) {
				x += step;
			} else {
				x -= step;
			}
		} else {
			index = 0;
			if (0 == direction) {
				direction = 1;
			} else {
				direction = 0;
			}
		}
	}
}
