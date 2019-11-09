package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.util.Script;

public class BoardDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		BoardDao dao = new BoardDao();
		int result = dao.delete(Integer.parseInt(id));
		
		if(result == 1) {
			//삭제 성공했으니깐
			//리스트 페이지로 다시 이동
			response.sendRedirect("/blog/index.jsp");
		}else {
			//삭제 실패
			Script.alert(response, "글을 삭제하는 도중에 문제가 발생하여 완료하지 못했습니다.");
			Script.back(response);
		}

	}
}
