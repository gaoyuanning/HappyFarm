import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Plant {
	
	public void draw(Graphics g) {
	}
	
	public int getId() {
		return 0;
	}
	
	public Rectangle getRec() {
		return null;
	}
	int x;
	int y;
	boolean isWater = false;
	boolean isFood = false;
	boolean isLive = true;
	int index = 4;
	FarmClient farmClient = null;

	public Plant(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Plant(int x, int y, int index) {
		this.x = x;
		this.y = y;
		this.index = index;
	}
	
	public void setLive(boolean live) {
		this.isLive = live;
	}
	
	public boolean getLive() {
		return this.isLive;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	int flag;
	public void startThread() {
		Random random = new Random();
		int a = random.nextInt(2);
		if(a == 0) {
			new Thread(new ThirstyThread()).start();
			flag = 0;
		} else {
			new Thread(new HungerThread()).start();
			flag = 1;
		}
	}
	
	public Plant(int x, int y, FarmClient farmClient) {
		this(x, y);
		this.farmClient = farmClient;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
		
	public void setIndex(int index) {
		this.index = index;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int life = 40;
	public void water() {
		if(!isLive || flag == 1) return;
		life += 20;
		if(life == 100 && isLive) {
			if(index == 3) {
				DialogFactory.createDialog(x + 200, y, "主人，可以收获了", 3000);
			} else {
				index += 1;
			}
			life = 40;
		}
		if(!isThirstyThreadStart) {
			new Thread(new ThirstyThread()).start();
		}
		isWater = true;
	}
	
	public void eat() {
		if(!isLive || flag == 0) return;
		life += 20;
		if(life == 100) {
			if(index == 3) {
				DialogFactory.createDialog(x + 200, y, "主人，可以收获了", 3000);
			} else {
				index += 1;
			}
			life = 40;
		}
		if(!isHungerThreadStart) {
			new Thread(new HungerThread()).start();
		}
		isFood = true;
	}
	
	boolean isThirstyThreadStart = false;
	class ThirstyThread implements Runnable {
		public void run() {
			if(!isLive) return;
			isThirstyThreadStart = true;
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(isLive && !isWater) {
				isWater = false;
				DialogFactory.createDialog(x + 35 , y + 30, "主人，我快渴死了！", 1000);
				if(!isIsWaterThread) {
					new Thread(new IsWaterThread()).start();
				}	
			}
			isThirstyThreadStart = false;
		}		
	}
	
	boolean isHungerThreadStart = false;
	class HungerThread implements Runnable {
		public void run() {
			if(!isLive) return;
			isHungerThreadStart = true;
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(isLive && !isFood) {
				isFood = false;
				DialogFactory.createDialog(x + 35, y + 30, "主人，我快饿死了！", 1000);
				if(!isHungerThreadStart) {
					new Thread(new IsHungerThread()).start();
				}
			}
			isHungerThreadStart = false;
		}		
	}
	
	
	boolean isIsWaterThread = false;
	class IsWaterThread implements Runnable {
		
		public void run() {
			isIsWaterThread = true;
			if(!isLive) return;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!isWater && isLive) {
				DialogFactory.createDialog(x + 35, y + 30, "我这次是真渴死了！", 3000);
				index = 5;
				isLive = false;
			}
			isIsWaterThread = false;
		}
		
	}
	
	boolean isIsHungerThread = false;
	class IsHungerThread implements Runnable {
		
		public void run() {
			isIsHungerThread = true;
			if(!isLive) return;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!isFood && isLive) {
				DialogFactory.createDialog(x + 35, y + 30, "我这次是真饿死了！", 3000);
				index = 5;
				isLive = false;
			}
			isIsHungerThread = false;
		}
		
	}

}












