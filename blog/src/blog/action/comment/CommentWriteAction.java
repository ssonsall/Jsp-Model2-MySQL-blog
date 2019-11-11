package blog.action.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.action.Action;
import blog.dao.CommentDao;
import blog.model.Comment;
import blog.service.CommentService;
import blog.util.Script;

public class CommentWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//null처리 필요
		int userId = Integer.parseInt(request.getParameter("userId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String content = request.getParameter("content");
		CommentDao commentDao = new CommentDao();
		Comment comment = new Comment();
		
		//맥스아이디로 찾을때 동시접근해서 문제발생할 경우 대비하려면
		//service 만들어서 하나로 묶음 synchronized 써서
		//아래와 같은 방식으로. service는 만들어뒀으니 보면 이해할수 있음
		//CommentService service = new CommentService();
		//Comment comment = service.write();
		
		
		comment.setUserId(userId);;
		comment.setBoardId(boardId);
		comment.setContent(content);
		
		int result = commentDao.save(comment);
		
		if(result == 1) {
			Comment responseComment = commentDao.findByMaxId();
			responseComment.getResponseData().setStatusCode(1);
			responseComment.getResponseData().setStatus("ok");
			responseComment.getResponseData().setStatusMessage("Write success");
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			Gson gson = new Gson();
			String commentJson = gson.toJson(responseComment);
			System.out.println("날리기 직전 >> " + commentJson);
			out.print(commentJson);
			out.flush();
		}else {
			Script.back(response);
		}
		
		
	}
}
