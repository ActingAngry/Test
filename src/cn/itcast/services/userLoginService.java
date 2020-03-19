package cn.itcast.services;
import java.sql.SQLException;
import cn.itcast.dao.userLoginDao;
import cn.itcast.web.UserBean;
public class userLoginService {
	public static int loginService(UserBean user) throws ClassNotFoundException, SQLException {
		return userLoginDao.loginDao(user);
	}
}
