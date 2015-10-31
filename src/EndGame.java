import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

import com.mysql.jdbc.PreparedStatement;


public class EndGame {
	FarmClient farmClient = null;
	public EndGame(FarmClient farmClient) {
		this.farmClient = farmClient;
		update();
	}
	

	Connection conn = null;
	Statement statement = null;
	String url = "jdbc:mysql://localhost:3306/farm?useUnicode=true&characterEncoding=gbk";
	String user = "root";
	String password = "123456";
	String sql = null;
	ResultSet rs = null;
	java.sql.PreparedStatement preparedStatement = null;
	
	public void update() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载驱动");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();		
			
			sql = "delete from baseinfo";
			statement.executeUpdate(sql);
			preparedStatement = conn.prepareStatement("insert into baseinfo values(?,?)");
			preparedStatement.setInt(1, farmClient.fruitsSum);
			preparedStatement.setInt(2, farmClient.money);
			preparedStatement.executeUpdate();
			
			
			sql = "delete from dog";
			statement.executeUpdate(sql);
			preparedStatement = conn.prepareStatement("insert into dog values(?,?,?)");
			preparedStatement.setInt(1, farmClient.dog.getX());
			preparedStatement.setInt(2, farmClient.dog.getY());
			preparedStatement.setInt(3, farmClient.dog.getLife());
			preparedStatement.executeUpdate();
			
			sql = "delete from plant";
			statement.executeUpdate(sql);
			preparedStatement = conn.prepareStatement("insert into plant values(?,?,?,?)");
			for(int i = 0; i < farmClient.plants.size(); i++) {
				Plant plant = farmClient.plants.get(i);
				preparedStatement.setInt(1, plant.getX());
				preparedStatement.setInt(2, plant.getY());
				System.out.println(plant.getIndex());
				preparedStatement.setInt(3, plant.getIndex());
				preparedStatement.setInt(4, plant.getId());
				preparedStatement.executeUpdate();
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
