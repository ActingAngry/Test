package cn.itcast.web;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import cn.itcast.services.userLoginService;
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String checkCode = request.getParameter("code");
		Map<String, String[]> map = request.getParameterMap();
		UserBean user = new UserBean();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			int login = userLoginService.loginService(user);
			if(login==0) {
				if(checkCode.equalsIgnoreCase((String)session.getAttribute("checkcode"))) {
					session.setAttribute("username", user.getUsername());
					response.sendRedirect("/day17/login/success.jsp");
				}else {
					request.setAttribute("codeError", "验证码错误！");
					request.getRequestDispatcher("/login/Login.jsp").forward(request, response);
				}
			}else if(login==1) {
				request.setAttribute("passError", "密码错误！");
				request.getRequestDispatcher("/login/Login.jsp").forward(request, response);
			}else {
				request.setAttribute("userError", "用户名不存在！");
				request.getRequestDispatcher("/login/Login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
