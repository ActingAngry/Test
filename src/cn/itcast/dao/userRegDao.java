package cn.itcast.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.itcast.web.UserBean;
public class userRegDao {
	public static int regUser(UserBean user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?serverTimezone=GMT%2B8", "root", "luleili");
		String sql1 = "select * from user where username=?";
		PreparedStatement pStatement = connection.prepareStatement(sql1);
		pStatement.setString(1, user.getUsername());
		ResultSet rSet = pStatement.executeQuery();
		if(rSet.next()) {
			rSet.close();
			pStatement.close();
			connection.close();
			//’À∫≈“—¥Ê‘⁄
			return 1;
		}else {
			String sql2 = "select * from user where nickname=?";
			PreparedStatement pStatement2 = connection.prepareStatement(sql2);
			pStatement2.setString(1, user.getNickname());
			ResultSet rSet2 = pStatement2.executeQuery();
			if (rSet2.next()) {
				rSet.close();
				pStatement.close();
				rSet2.close();
				pStatement2.close();
				connection.close();
				return 2;
			}
			String sql3 = "insert into user values(null,?,?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(sql3);
			pStatement3.setString(1, user.getUsername());
			pStatement3.setString(2, user.getPassword());
			pStatement3.setString(3, user.getNickname());
			pStatement3.executeUpdate();
			rSet.close();
			pStatement.close();
			rSet2.close();
			pStatement2.close();
			pStatement3.close();
			connection.close();
			return 0;
		}
	}
}
