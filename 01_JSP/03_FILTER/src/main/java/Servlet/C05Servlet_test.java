package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class C05Servlet_test extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������ ����
		System.out.println("GET /login.do..");
		
		
		HttpSession session = req.getSession();	
		if(session.getAttribute("message")!=null ) {
			String message = (String)session.getAttribute("message");
			session.removeAttribute("message");
			
			req.setAttribute("message", message);
		}
		
		
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ȸ������ ó��
		System.out.println("POST /login.do..");
		//01 �Ķ���� 
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//02 ��ȿ��
		if(!isValid(username,password,req)) {
			
			//�α��� ������ �̵�(message ����)
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			return;
		}
		
		//03 ����ó��(�α��� ó��)
		
		
		
		try {
			
			DbUtils.conn();
			UserDto dto = DbUtils.selectUser(username);
			// ����� ID Ȯ��
			if(dto==null) {
				req.setAttribute("message", "������ ������ �������� �ʽ��ϴ�.");
				req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
				return;
			}
			// �Է��� PW ����
			if(!password.equals(dto.getPassword())) {
				req.setAttribute("message", "�н����尡 ��ġ���� �ʽ��ϴ�.");
				req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
				return;				
			}
			DbUtils.disConn();
			
			
			// �����Ϸ� ���� + ROLE ���� - SESSION
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setAttribute("isAuth", true);
			session.setAttribute("role", "ROLE_USER");
			
			//��� �̵�
			session.setAttribute("message", "�α��� ����!");
			resp.sendRedirect(req.getContextPath() + "/main.do");
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally {
					
		}		
		
		//04 ����̵�(��� ������ ����) - 
		
		
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
