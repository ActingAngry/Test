package cn.itcast.web;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/checkCode")
public class checkCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		BufferedImage paper = new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
		Graphics2D printPen = (Graphics2D)paper.getGraphics();
		printPen.setColor(Color.yellow);
		printPen.fillRect(0, 0, 120, 40);
		String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random random = new Random();
		int x = 20;
		StringBuffer sBuffer = new StringBuffer();
		for(int i=0;i<4;i++) {
			int index = random.nextInt(string.length());
			char c = string.charAt(index);
			sBuffer.append(c);
			printPen.setColor(Color.blue);
			printPen.setFont(new Font("¿¬Ìå",Font.BOLD,25));
			int dushu = random.nextInt(60)-30;
			double thete = dushu * Math.PI / 180;
			printPen.rotate(thete,x,random.nextInt(40));
			printPen.drawString(""+c, x, 28);
			printPen.rotate(-thete,x,random.nextInt(40));
			x=x+20;
			int x1 = random.nextInt(120);
			int x2 = random.nextInt(120);
			int y1 = random.nextInt(40);
			int y2 = random.nextInt(40);
			printPen.setColor(Color.green);
			printPen.drawLine(x1, y1, x2, y2);
		}
		String checkcode = sBuffer.toString();
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", checkcode);
		ImageIO.write(paper, "jpg", response.getOutputStream());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
