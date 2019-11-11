package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.action.comment.CommentFactory;
import blog.action.user.UserFactory;

@WebServlet("/api/comment")
public class CommentRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentRestController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//빼먹지 말자. 빼먹으면 한글깨짐.
		response.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd == null || cmd.equals("")) {
			return;
		}
		
		Action action = CommentFactory.getAction(cmd);
		
		if (action != null)
			action.execute(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

