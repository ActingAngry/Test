package cn.itcast.services;
import java.sql.SQLException;
import cn.itcast.dao.userRegDao;
import cn.itcast.web.UserBean;
public class userRegService {
	public static int user(UserBean user) throws ClassNotFoundException, SQLException {
		return userRegDao.regUser(user);
	}
}
