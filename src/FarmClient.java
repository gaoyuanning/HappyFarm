import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;


public class FarmClient extends Frame{
	
	public static final int GAME_WIDTH = 1140;	//1010
	public static final int GAME_HEIGHT = 750;
	
	public int fruitsSum = 0;
	public int money = 1000;
	
	ArrayList<Plant> plants = new ArrayList<Plant>();
	
	Land land = new Land(0, 0);
	Bird bird = new Bird(700, 30);
	Dog dog = new Dog(50, 600);
	Sun sunWeather = new Sun(250, 30, this);
	Rain rainWeather = new Rain(0, 0, this);
	Bucket bucket = new Bucket(1010, 25, this);
	Fertilizer fertilizer = new Fertilizer(1010, 110, this);
	Spade spade = new Spade(1020, 180, this);
	Gain gain = new Gain(1030, 250, this);
	Apple apple  = new Apple(1020, 310);
	Pepper pepper = new Pepper(1015, 410);
	Eggplant eggplant = new Eggplant(1015, 510);
	DogFood dogFood = new DogFood(1035, 630, this);
	Bucket tempBucket = null;
	Fertilizer tempFertilizer = null;
	Spade tempSpade = null;
	Gain tempGain = null;
	Apple tempApple = null;
	Pepper tempPepper = null;
	Eggplant tempEggplant = null;
	DogFood tempDogFood = null;
	
	DragPicListener listener=new DragPicListener();  //����¼�����
	Toolkit toolkit = this.getToolkit();
	Image cursorImg1 = toolkit.getImage(this.getClass().getResource("images/hand11.png"));
	Image cursorImg2 = toolkit.getImage(this.getClass().getResource("images/hand12.png"));
	Image gameImage = toolkit.getImage(this.getClass().getResource("images/game.png"));
	
	PaintThread paintThread = null;
	GameClient gameClient = null;
	
	static FarmClient farmClient = null;
	public static void main(String[] args) {
		farmClient = new FarmClient();
		farmClient.launchFrame();
		new StartGame(farmClient);
	}
	
	public void launchFrame() {
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setLocation(120, 0);
		this.setCursor(this.createCursor(cursorImg1, "hand3"));
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				new EndGame(farmClient);
				System.exit(0);
			}
		});
		
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addKeyListener(new RestartGame());
		paintThread = new PaintThread();
		paintThread.start();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	Image offScreenImage = null;	//����Ļ���
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(new Color(236, 170, 34));
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		g.drawImage(gameImage, 1035, 700, null);
		g.setColor(c);
		land.draw(g);
		bird.draw(g);
		dog.draw(g);
		sunWeather.draw(g);
		rainWeather.draw(g);
		bucket.draw(g);
		fertilizer.draw(g);
		spade.draw(g);
		gain.draw(g);
		apple.draw(g);
		pepper.draw(g);
		eggplant.draw(g);
		dogFood.draw(g);
		if(tempBucket != null) tempBucket.draw(g); 
		if(tempFertilizer != null) tempFertilizer.draw(g); 
		if(tempSpade != null) tempSpade.draw(g); 
		if(tempGain != null) tempGain.draw(g);  
		if(tempDogFood != null) tempDogFood.draw(g);  
		for(int i = 0; i < plants.size(); i++) {
			plants.get(i).draw(g);
		}
		g.setColor(c);
		g.drawString("�������: " + money, 8, 45);
		g.drawString("��ʵ��Ŀ: " + fruitsSum, 8, 65);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);	//����
		}
		
		Graphics graphicsOffScreen = offScreenImage.getGraphics();	//�û��ʸ����������ϻ�������g������Ļ�ϻ���
		paint(graphicsOffScreen);
		
		g.drawImage(offScreenImage, 0, 0, null);	//�ڰѻ����ϵĻ�����Ļ��
	}
	
	public class PaintThread extends Thread {  
		  
	    private boolean suspend = false;  
	  
	    private String control = ""; // ֻ����Ҫһ��������ѣ��������û��ʵ������  
	  
	    public void setSuspend(boolean suspend) {  
	        if (!suspend) {  
	            synchronized (control) {  
	                control.notifyAll();  
	            }  
	        }  
	        this.suspend = suspend;  
	    }  
	  
	    public boolean isSuspend() {  
	        return this.suspend;  
	    }  
	  
	    public void run() {  
	        while (true) {  
	            synchronized (control) {  
	                if (suspend) {  
	                    try {  
	                        control.wait();  
	                    } catch (InterruptedException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	            this.runPersonelLogic();
	        	try {
					Thread.sleep(1000 / 30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }  
	    }  
	  
	    protected void runPersonelLogic() {
	    	repaint();
	    }
	}      
	
	class DragPicListener implements MouseInputListener
	   { 
	      Point point=new Point(0,0); //�����
	      
	      public void mousePressed(MouseEvent e)
	      {
	    	  if ((e.getX() > 1010 && e.getX() < 1110) && (e.getY() > 25 && e.getY() < 90)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	         tempBucket = new Bucket(bucket.getX() - 10, bucket.getY(), FarmClient.this);
	 	         setCursor(createCursor(cursorImg2, "hand1"));
	    	  } else if ((e.getX() > 1010 && e.getX() < 1110) && (e.getY() > 110 && e.getY() < 180)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempFertilizer = new Fertilizer(fertilizer.getX() - 10, fertilizer.getY(), FarmClient.this);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  } else if ((e.getX() > 1020 && e.getX() < 1110) && (e.getY() > 180 && e.getY() < 240)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempSpade = new Spade(spade.getX() - 10, spade.getY(), FarmClient.this);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  } else if ((e.getX() > 1030 && e.getX() < 1110) && (e.getY() > 250 && e.getY() < 300)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempGain = new Gain(gain.getX() - 10, gain.getY(), FarmClient.this);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  }  else if ((e.getX() > 1020 && e.getX() < 1110) && (e.getY() > 310 && e.getY() < 410)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempApple = new Apple(apple.getX(), apple.getY(), 0);
	 	          plants.add(tempApple);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  }  else if ((e.getX() > 1015 && e.getX() < 1110) && (e.getY() > 410 && e.getY() < 510)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempPepper = new Pepper(pepper.getX(), pepper.getY(), 0);
	 	          plants.add(tempPepper);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  }  else if ((e.getX() > 1015 && e.getX() < 1110) && (e.getY() > 510 && e.getY() < 620)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempEggplant = new Eggplant(eggplant.getX(), eggplant.getY(), 0);
	 	          plants.add(tempEggplant);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  } else if ((e.getX() > 1035 && e.getX() < 1110) && (e.getY() > 630 && e.getY() < 690)) {
	    		  point=e.getPoint(); //�õ���ǰ�����
	 	          tempDogFood = new DogFood(dogFood.getX() - 10, dogFood.getY(), FarmClient.this);
	 	          setCursor(createCursor(cursorImg2, "hand1"));
	    	  } 
	       } 
	      
	      public void mouseDragged(MouseEvent e)
	      {
	    	  Point newPoint=e.getPoint(); //ת������ϵͳ
	    	  if(tempBucket != null) {
	   	         tempBucket.setLocation(tempBucket.getX()+(newPoint.x-point.x),tempBucket.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
	   	         point=newPoint; //���������
	    	  } else if(tempFertilizer != null) {
	    		  tempFertilizer.setLocation(tempFertilizer.getX()+(newPoint.x-point.x),tempFertilizer.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
		   	      point=newPoint; //���������
	    	  } else if(tempSpade != null) {
	    		  tempSpade.setLocation(tempSpade.getX()+(newPoint.x-point.x),tempSpade.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
	    		  point=newPoint; //���������
	    	  } else if(tempGain != null) {
	    		  tempGain.setLocation(tempGain.getX()+(newPoint.x-point.x),tempGain.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
		   	      point=newPoint; //���������
	    	  } else if(tempApple != null) {
	    		  tempApple.setLocation(tempApple.getX()+(newPoint.x-point.x),tempApple.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
	    		  point=newPoint; //���������
	    	  } else if(tempPepper != null) {
	    		  tempPepper.setLocation(tempPepper.getX()+(newPoint.x-point.x),tempPepper.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
	    		  point=newPoint; //���������
	    	  } else if(tempEggplant != null) {
	    		  tempEggplant.setLocation(tempEggplant.getX()+(newPoint.x-point.x),tempEggplant.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
	    		  point=newPoint; //���������
	    	  } else if(tempDogFood != null) {
	    		  tempDogFood.setLocation(tempDogFood.getX()+(newPoint.x-point.x),tempDogFood.getY()+(newPoint.y-point.y)); //���ñ�ǩͼƬ����λ��
		   	      point=newPoint; //���������
	    	  } 
	       }
	      
	      public void mouseReleased(MouseEvent e){
	    	  if (tempBucket != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  tempBucket.water();
	        	  tempBucket = null;
	    	  } else if(tempFertilizer != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  tempFertilizer.food();
	    		  tempFertilizer = null;
	    	  } else if(tempSpade != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  tempSpade.uproot();
	    		  tempSpade = null;
	    	  }  else if(tempGain != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  if(tempGain.harvest()) {
	    			  fruitsSum += 1;
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "ժȡ�ɹ�", 1000);
	    		  }
	    		  tempGain = null;
	    	  } else if(tempApple != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  if(!PointInRec.judgeInRec(e)) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ��ִ�ط���!");
	    			  plants.remove(plants.size() - 1);  
	    		  } else if(money - 100 < 0) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ�Ǯ�����ˣ�", 1000);
	    			  plants.remove(plants.size() - 1); 
	    		  } else {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "����100���", 1000);
	    			  money -= 100;
	    			  tempApple.startThread();
	    		  }
	    		  tempApple = null;
	    	  } else if(tempPepper != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  if(!PointInRec.judgeInRec(e)) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ��ִ�ط���!");
	    			  plants.remove(plants.size() - 1);  
	    		  } else if(money - 200 < 0) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ�Ǯ�����ˣ�", 1000);
	    			  plants.remove(plants.size() - 1); 
	    		  } else {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "����200���", 1000);
	    			  money -= 200;
	    			  tempPepper.startThread();
	    		  }
	    		  tempPepper = null;
	    	  } else if(tempEggplant != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  if(!PointInRec.judgeInRec(e)) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ��ִ�ط���!");
	    			  plants.remove(plants.size() - 1);  
	    		  } else if(money - 300 < 0) {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "���ˣ�Ǯ�����ˣ�", 1000);
	    			  plants.remove(plants.size() - 1); 
	    		  } else {
	    			  DialogFactory.createDialog(e.getX(), e.getY(), "����300���", 1000);
	    			  money -= 300;
	    			  tempEggplant.startThread();
	    		  }
	    		  tempEggplant = null;
	    	  } else if(tempDogFood != null) {
	    		  setCursor(createCursor(cursorImg1, "hand2"));
	    		  tempDogFood.food();
	    		  tempDogFood = null;
	    	  } 
	      }
	      
	      public void mouseEntered(MouseEvent e){}
	      
	      public void mouseExited(MouseEvent e){}
	      
	      public void mouseClicked(MouseEvent e){
	    	  if ((e.getX() > 1035 && e.getX() < 1110) && (e.getY() > 700 && e.getY() < 750)) {
	    			gameClient = new GameClient();
	      		  	gameClient.launchFrame(fruitsSum, money);
	      		  	paintThread.setSuspend(true);  
	    	  }
	      }
	      
	      public void mouseMoved(MouseEvent e){}
	   }
	
	class RestartGame extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_F2) {
				paintThread.setSuspend(false);
				fruitsSum = gameClient.lifeSum;
				money = gameClient.money;
			}
		}
		
	}
		
	public Cursor createCursor(Image img,String name){
		return this.getToolkit().createCustomCursor(img,new Point(16,16),name);
	}
		
}












