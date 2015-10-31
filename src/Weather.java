import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Weather {
	int x;
	int y;
	int index = 0;
	FarmClient farmClient = null;
	
	WeatherKind weatherKind = WeatherKind.Sunny;
//	WeatherKind weatherKind = WeatherKind.Rainy;
	
	public Weather(int x, int y, FarmClient farmClient) {
		this.x = x;
		this.y = y;
		this.farmClient = farmClient;
	}
	
	public WeatherKind getWeather() {
		return this.weatherKind;
	}
	
	public void setWeather(WeatherKind weatherKind) {
		this.weatherKind = weatherKind;
	}
	
	public void sunnyDay() {
		this.farmClient.land.addLife();
	}
	
	public void rainyDay() {
		this.farmClient.land.reduceLife();
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
		if(weatherKind == WeatherKind.Sunny) {
			g.drawImage(sunImages[index], x, y, null);
			index = (index + 1) % sunImages.length;
		} else {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, farmClient.GAME_WIDTH, farmClient.GAME_HEIGHT);
			g.drawImage(rainImages[index], x, y, null);
			index = (index + 1) % rainImages.length;
			g.setColor(c);
		}
	}
}






















