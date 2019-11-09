package blog.action.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.CommentDao;
import blog.util.Script;

public class CommentDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader in = request.getReader();
		int commentId = Integer.parseInt(in.readLine());
		System.out.println("commentId : " + commentId);
		
		CommentDao commentDao = new CommentDao();
		int result = commentDao.delete(commentId);
		
		if(result == 1) {
			PrintWriter out = response.getWriter();
			out.print("ok"); // println쓰면 개행문자 같이 날라감
			out.flush();
		}else {
			Script.back(response);
		}
	}
}
