package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.dao.UserDao;
import blog.model.Board;
import blog.model.User;
import blog.util.Script;
import blog.util.Utils;

public class BoardWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String title  = request.getParameter("title");
		String content = request.getParameter("content");
		String searchContent = Utils.getPureContent(content);
		Board board = new Board();
		board.setUserId(user.getId());
		board.setTitle(title);		
		board.setContent(content);		
		board.setSearchContent(searchContent);
		System.out.println("searchContent >>>> " + searchContent);
		//널 처리 필요
		BoardDao dao = new BoardDao();

		//DB에 대량 글 적기		
//		for (int i = 1; i < 23; i++) { 
//			board.setUserId(45);
//			board.setTitle("테스트"+i);		
//			board.setContent("테스트 글입니다.");		
//			board.setSearchContent("테스트 글입니다.");
//			dao.save(board);
//		}
//		
//		for (int i = 23; i < 47; i++) {
//			board.setUserId(45);
//			board.setTitle("테스트"+i);		
//			board.setContent("테스트 글입니다.");		
//			board.setSearchContent("테스트 글입니다.");
//			dao.save(board);
//		}
//		int result = 1;
		
		int result = dao.save(board);

		if(result == 1) {
			//글 작성 성공
			response.sendRedirect("index.jsp");
		}else {
			//글 작성 실패
			Script.alert(response, "글 작성에 실패하였습니다");
			Script.back(response);
		}
		
		
	}
}
