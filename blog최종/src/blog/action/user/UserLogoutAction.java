package blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;

public class UserLogoutAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		
		//list이동
		response.sendRedirect("/blog/index.jsp"); // list로 이동하는건 무조건 인덱스로 이동시킨 다음에 인덱스에서 서블릿타게 만들 예정
	}
}
