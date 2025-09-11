package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/join.do")
public class C04Servlet_test extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������ ����
		System.out.println("GET /join.do..");
		req.getRequestDispatcher("/WEB-INF/join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ȸ������ ó��
		System.out.println("POST /join.do..");
		//01 �Ķ���� 
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//02 ��ȿ��
		if(!isValid(username,password,req)) {
			
			//�α��� ������ �̵�(message ����)
			req.getRequestDispatcher("/WEB-INF/join.jsp").forward(req, resp);
			return;
		}
		//03 ����ó��(ȸ������ó��)
		try {
			
			DbUtils.conn();
			int result = DbUtils.insertUser(username, password);
			if(result<=0) {
				req.getRequestDispatcher("/WEB-INF/join.jsp").forward(req, resp);
				return;
			}
			DbUtils.disConn();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally {
					
		}
		//04 ����̵�(��� ������ ����) - 
		HttpSession  session = req.getSession();
		session.setAttribute("message", "ȸ������ ����!");
		resp.sendRedirect(req.getContextPath() + "/login.do");
		
	}
	
	public boolean isValid(String username,String password, HttpServletRequest req) 
	{
		boolean flag=true;
		if(username.isEmpty()) {
			req.setAttribute("username_msg","�������� �Է��ϼ���");
			flag=false;
		}
		if(password.isEmpty()) {
			req.setAttribute("password_msg","�н����带 �Է��ϼ���");
			flag=false;
		}
			
		return flag;
	}
	
}
