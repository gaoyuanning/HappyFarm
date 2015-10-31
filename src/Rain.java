import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Rain {
	int x;
	int y;
	int index = 0;
	boolean isRain = false;
	FarmClient farmClient = null;
	
	public Rain(int x, int y, FarmClient farmClient) {
		this.x = x;
		this.y = y;
		this.farmClient = farmClient;
	}
	
	public void sunnyDay() {
		this.farmClient.land.addLife();
	}
	
	public void rainyDay() {
		this.farmClient.land.reduceLife();
	}
	
	public void setRain(boolean rain) {
		this.isRain = rain;
		new Thread(new RainThread()).start();
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Image[] sunImages = null;
	static Image[] rainImages = null;
	static {
		sunImages = new Image[] {
			toolkit.getImage(Weather.class.getResource("images/sun2/1.png")),	
			toolkit.getImage(Weather.class.getResource("images/sun2/2.png")),	
			toolkit.getImage(Weather.class.getResource("images/sun2/3.png")),	
		};
		
		rainImages = new Image[] {
			toolkit.getImage(Weather.class.getResource("images/rain/1.png")),	
			toolkit.getImage(Weather.class.getResource("images/rain/2.png")),	
			toolkit.getImage(Weather.class.getResource("images/rain/3.png")),	
			toolkit.getImage(Weather.class.getResource("images/rain/4.png")),	
			toolkit.getImage(Weather.class.getResource("images/rain/5.png")),	
			toolkit.getImage(Weather.class.getResource("images/rain/6.png")),	
		};
	}
	
	public void draw(Graphics g) {
		if(isRain) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, farmClient.GAME_WIDTH, farmClient.GAME_HEIGHT);
			g.drawImage(rainImages[index], x, y, null);
			index = (index + 1) % rainImages.length;
			g.setColor(c);
		}
	}

	class RainThread implements Runnable {
		public void run() {
			try {
				Thread.sleep(5000);
				Rain.this.isRain = false;
				farmClient.sunWeather.setSun(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}






















