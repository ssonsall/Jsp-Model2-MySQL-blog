package blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.UserDao;

@WebServlet("/api/gmail")
public class GmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GmailController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		System.out.println("인증에서 mail >> " + email);
		
		//Result가 정상일때
		//response.sendRedirect("/blog/test/gmailSendActionTest.jsp?email=" + email);
		
		//두 건밖에 안되니깐 그냥 setAttribute 두개 해서 날림
		request.setAttribute("id", id);
		request.setAttribute("username", username);
		request.setAttribute("email", email);
		RequestDispatcher dis = request.getRequestDispatcher("/mail/gmailSend.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
