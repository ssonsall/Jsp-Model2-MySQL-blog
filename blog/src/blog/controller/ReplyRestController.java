package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.action.reply.ReplyFactory;

@WebServlet("/api/reply")
public class ReplyRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyRestController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//빼먹지 말자. 빼먹으면 한글깨짐.
		response.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		System.out.println("cmddd >>" + cmd);
		if(cmd == null || cmd.equals("")) {
			return;
		}
		
		Action action = ReplyFactory.getAction(cmd);
		
		if (action != null)
			action.execute(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

