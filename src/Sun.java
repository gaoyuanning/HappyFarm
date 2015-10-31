import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;


public class Sun {
	int x;
	int y;
	int index = 0;
	boolean isSun = true;
	FarmClient farmClient = null;
	
	public Sun(int x, int y, FarmClient farmClient) {
		this.x = x;
		this.y = y;
		this.farmClient = farmClient;
		new Thread(new SunThread()).start();
	}
	
	public void sunnyDay() {
		this.farmClient.land.addLife();
	}
	
	public void rainyDay() {
		this.farmClient.land.reduceLife();
	}
	
	public void setSun(boolean sun) {
		this.isSun = sun;
		new Thread(new SunThread()).start();
	}
	
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image sunGifImage = toolkit.getImage(this.getClass().getResource("images/Sun.gif"));
//	Image sunGifImage = toolkit.getImage(this.getClass().getResource("images/flash.swf"));
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
		if(isSun) {
//			g.drawImage(sunImages[index], x, y, null);
//			index = (index + 1) % sunImages.length;
			g.drawImage(sunGifImage, x, y, null);
		}
	}
	
	class SunThread implements Runnable {
		public void run() {
			try {
				Thread.sleep(20000);
				DialogFactory.createDialog(x + 320, y + 30, "ÒªÏÂÓêà¶£¡", 3000);
				Thread.sleep(3000);
				Sun.this.isSun = false;
				farmClient.rainWeather.setRain(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}






















