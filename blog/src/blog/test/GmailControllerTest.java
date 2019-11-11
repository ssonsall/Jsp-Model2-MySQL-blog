package blog.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/gmail")
public class GmailControllerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GmailControllerTest() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		
		//ID값 가져와서 ID값 가져와서 SELECT, 쿼리스트링으로 개인정보 넘기면 안됨
		
		//Result가 정상일때
		response.sendRedirect("/blog/test/gmailSendActionTest.jsp?email=" + email);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
