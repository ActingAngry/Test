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
import org.apache.commons.beanutils.BeanUtils;
import cn.itcast.services.userRegService;
@WebServlet("/regServlet")
public class regServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		UserBean user = new UserBean();
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			int reg = userRegService.user(user);
			if(reg==0) {
				response.sendRedirect("/day17/register/success.jsp");
			}else if(reg==1) {
				request.setAttribute("usernamed", "账号已存在！");
				request.getRequestDispatcher("/register/Register.jsp").forward(request, response);
			}else {
				request.setAttribute("nicknamed", "昵称已存在！");
				request.getRequestDispatcher("/register/Register.jsp").forward(request, response);;
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
