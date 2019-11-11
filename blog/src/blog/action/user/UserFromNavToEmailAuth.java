package blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.model.User;

public class UserFromNavToEmailAuth implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		User user = new User();
		user = (User)session.getAttribute("user");
		request.setAttribute("user", user);
		RequestDispatcher dis = request.getRequestDispatcher("/mail/mailAuthentication.jsp");
		dis.forward(request, response);
	}
}
