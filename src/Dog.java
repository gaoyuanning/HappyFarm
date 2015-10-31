import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;


public class Dog {
	int x;
	int y;
	boolean isLive = true;
	boolean isFood = true;
	boolean isLittle = true;
	int cnt = 0;
	int life = 20;
	
	int index = 0;	//上限为6
	int step = 1;
	int direction = 0; //0代表向右，1代表向左
	
	public Dog(int x, int y) {
		this.x = x;
		this.y = y;
		startThread();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setIsFood(boolean isFood) {
		this.isFood = isFood;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void eat() {
		this.life += 10;
		this.isFood = true;
		this.cnt = 0;
		this.isFoodTime = 0;
		if(!isFoodThread) {
			startThread();
		}
		if(this.life >= 60 && isLittle) {
			DialogFactory.createDialog(x + 150, y - 50, "噢耶，我长大了！", 3000);
			isLittle = false;
		}
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image[] DogImages = null;
	static {
		DogImages = new Image[] {
	//	toolkit.getImage(Dog.class.getResource("images/littleDog.png")),
		toolkit.getImage(Dog.class.getResource("images/dogL.gif")),
	//	toolkit.getImage(Dog.class.getResource("images/bigDog.png")),
		toolkit.getImage(Dog.class.getResource("images/dogR.gif")),
		toolkit.getImage(Dog.class.getResource("images/dogBL.png")),
		toolkit.getImage(Dog.class.getResource("images/dogBR.png")),
		};
	}
	
	public void draw(Graphics g) {
		if(isLive) {
			if(life < 60) {
				if (direction == 0) {
					g.drawImage(DogImages[1], x, y, null);
				} else {
					g.drawImage(DogImages[0], x, y, null);
				}
			} else {
				if (direction == 0) {
					g.drawImage(DogImages[3], x, y, null);
				} else {
					g.drawImage(DogImages[2], x, y, null);
				}
			}
			move();
			cnt += 1;
		}
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
	
	public Rectangle getRec() {
		if(this.life < 60) {
			return new Rectangle(x, y, DogImages[0].getWidth(null), DogImages[0].getHeight(null));
		} else {
			return new Rectangle(x, y, DogImages[1].getWidth(null), DogImages[1].getHeight(null));
		}
		
	}
	
	public void startThread() {
		new Thread(new FoodThread()).start();
	}
	
	boolean isFoodThread = false;
	class FoodThread implements Runnable {
		public void run() {
			isFoodThread = true;
			while(isFood) {
				if(cnt == 300) {
					DialogFactory.createDialog(x + 100, y - 50, "主人，我快饿死了！");
					isFood = false;
					if(!isIsFoodThread) {
						new Thread(new IsFoodThread()).start();
					}
				}
			}
			isFoodThread = false;
		}	
	}
	
	int isFoodTime = 0;
	boolean isIsFoodThread = false;
	class IsFoodThread implements Runnable {
		
		public void run() {
			isIsFoodThread = true;
			while(isFood == false) {
				if(isFoodTime == 20) {
					DialogFactory.createDialog(x + 100, y - 50, "我这次是真饿死了！", 3000);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					isLive = false;
				}
				
				isFoodTime += 1;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isIsFoodThread = true;
		}
		
	}
}




























