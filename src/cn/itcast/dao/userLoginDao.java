package cn.itcast.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.itcast.web.UserBean;
public class userLoginDao {
	public static int loginDao(UserBean user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?serverTimezone=GMT%2B8", 
				"root", "luleili");
		String sql = "select * from user where username=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, user.getUsername());
		ResultSet rSet = pStatement.executeQuery();
		if(rSet.next()) {
			String sql2 = "select * from user where password=?";
			PreparedStatement pStatement2 = connection.prepareStatement(sql2);
			pStatement2.setString(1, user.getPassword());
			ResultSet rSet2 = pStatement2.executeQuery();
			if(rSet2.next()) {
				rSet2.close();
				pStatement2.close();
				rSet.close();
				pStatement.close();
				connection.close();
				return 0;
			}
			rSet2.close();
			pStatement2.close();
			rSet.close();
			pStatement.close();
			connection.close();
			return 1;
		}
		rSet.close();
		pStatement.close();
		connection.close();
		return 2;
	}
}
