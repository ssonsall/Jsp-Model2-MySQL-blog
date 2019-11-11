package blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.model.Board;

public class BoardUpdateFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BoardDao dao = new BoardDao();
		Board board = dao.findById(Integer.parseInt(id));
		
		if(board != null) {
			request.setAttribute("board", board);
			RequestDispatcher dis = request.getRequestDispatcher("/board/updateForm.jsp");
			dis.forward(request, response);
		}else {
			return;
		}
		
		
	}
}
