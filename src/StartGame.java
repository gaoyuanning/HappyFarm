import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;


public class StartGame {
	FarmClient farmClient = null;
	public StartGame(FarmClient farmClient) {
		this.farmClient = farmClient;
		init();
	}
	

	Connection conn = null;
	Statement statement = null;
	String url = "jdbc:mysql://localhost:3306/farm?useUnicode=true&characterEncoding=gbk";
	String user = "root";
	String password = "123456";
	String sql = null;
	ResultSet rs = null;
	
	public void init() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载驱动");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();		
			
			sql = "select * from baseinfo";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int fruitsum = rs.getInt(1);
				int money = rs.getInt(2);
				farmClient.fruitsSum = fruitsum;
				farmClient.money = money;
			}
			
			sql = "select * from dog";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int x = rs.getInt(1);
				int y = rs.getInt(2);
				int life = rs.getInt(3);
				farmClient.dog.setLocation(x, y);
				farmClient.dog.setLife(life);
			}
			
			sql = "select * from plant";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int x = rs.getInt(1);
				int y = rs.getInt(2);
				int age = rs.getInt(3);
				int id = rs.getInt(4);
				if(id == 1) {
					Apple apple = new Apple(x, y, age);
					farmClient.plants.add(apple);
					if(age != 5) apple.startThread();
					
				} else if(id == 2) {
					Eggplant eggplant = new Eggplant(x, y, age);
					farmClient.plants.add(eggplant);
					if(age != 5) eggplant.startThread();
				} else if(id == 3) {
					Pepper pepper = new Pepper(x, y, age);
					farmClient.plants.add(pepper);
					if(age != 5) pepper.startThread();
				}
			}
	
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("MySQL错误");
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
