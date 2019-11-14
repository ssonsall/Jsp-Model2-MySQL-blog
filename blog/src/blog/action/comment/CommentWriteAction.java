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
import blog.util.Script;

public class CommentWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//null泥섎━ �븘�슂
		int userId = Integer.parseInt(request.getParameter("userId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String content = request.getParameter("content");
		CommentDao commentDao = new CommentDao();
		Comment comment = new Comment();
		
		//留μ뒪�븘�씠�뵒濡� 李얠쓣�븣 �룞�떆�젒洹쇳빐�꽌 臾몄젣諛쒖깮�븷 寃쎌슦 ��鍮꾪븯�젮硫�
		//service 留뚮뱾�뼱�꽌 �븯�굹濡� 臾띠쓬 synchronized �뜥�꽌
		//�븘�옒�� 媛숈� 諛⑹떇�쑝濡�. service�뒗 留뚮뱾�뼱���쑝�땲 蹂대㈃ �씠�빐�븷�닔 �엳�쓬
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
			System.out.println("�궇由ш린 吏곸쟾 >> " + commentJson);
			out.print(commentJson);
			out.flush();
		}else {
			Script.back(response);
		}
		
		
	}
}
