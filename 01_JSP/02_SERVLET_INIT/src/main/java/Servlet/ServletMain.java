package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// main.do와 연결하는 매개체?
@WebServlet("/main.do")
// HttpServlet과 연결(상속)
public class ServletMain extends HttpServlet{

	// Source->Override/Implement Methods->HttpServlet->doGet
	// doGet 설명 :
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 작동 시 아래 출력문 출력
		System.out.println("GET /main.do...");
		
		HttpSession session = req.getSession();
		// 
		if(session.getAttribute("message")!=null) {
			String message = (String)session.getAttribute("message");
			session.removeAttribute("message");
			
			req.setAttribute("message", message);
		}
		req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
		
	}
}
