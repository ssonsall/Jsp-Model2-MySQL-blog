package blog.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import blog.action.Action;
import blog.dao.ReplyDao;
import blog.model.Reply;
import blog.util.Script;

public class ReplyWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String commentId = request.getParameter("commentId");
		String content = request.getParameter("content");

		Reply reply = new Reply();
		reply.setUserId(Integer.parseInt(userId));
		reply.setCommentId(Integer.parseInt(commentId));
		reply.setContent(content);
		ReplyDao dao = new ReplyDao();
		int result = dao.save(reply);
		
		if(result == 1) {			
			reply = dao.findByMaxId();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
			String replyJson = gson.toJson(reply);
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			System.out.println(replyJson);
			out.print(replyJson);
			out.flush();

		}else {
			Script.back(response);
		}
		
	}
}
