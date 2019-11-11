package blog.action.board;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.dto.View;
import blog.model.Board;
import blog.util.Script;
import blog.util.Utils;

public class BoardSearchAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("page") == null) {
			return;
		}

		if (request.getParameter("keyword") == null) {
			response.sendRedirect("/blog/index.jsp");
			return;
		}

		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");
		BoardDao dao = new BoardDao();
		List<View> viewList = new Vector<View>();
		int countRow = dao.countSearchRow(keyword); // 스칼라 서브쿼리 사용하는 방식으로 변경해보자(스칼라서브쿼리? 셀렉트 절에 셀렉트를 쓰는 것)
		int count = 0;

		if (countRow % 3 == 0) {
			count = countRow / 3;
		} else {
			count = (countRow / 3) + 1;
		}

		if (page <= 0) {
			page = 1;
			response.sendRedirect("/blog/board?cmd=search&keyword=" + keyword + "page=" + page);
			return;
		} else if (page > count) {
			if (count != 0) {
				page = count;
				response.sendRedirect("/blog/board?cmd=search&keyword=" + keyword + "page=" + page);
				return;
			}
		}

		List<Board> hotBoardList = new Vector<Board>();

		hotBoardList = dao.findOrderByReadCountDesc();

		viewList = dao.findByKeyWord(page, keyword);

		// reference로 넘겼으니깐 리턴 받을 필요 없음.
		Utils.setPreviewImg(viewList);
		Utils.setHotBoardPreviewImg(hotBoardList);
		Utils.setPreviewContent(viewList);

		if (viewList != null) {
			request.setAttribute("viewList", viewList);
			request.setAttribute("countRow", countRow);
			request.setAttribute("count", count);
			request.setAttribute("keyword", keyword);
			request.setAttribute("hotBoardList", hotBoardList);
			// request.setAttribute("page", page); 이렇게 해도 됨
			// jstl 이용해서 쿼리스트링에 접근해서 페이지 얻는 방법으로 할 것임 (list.jsp 파일에서)
			RequestDispatcher dis = request.getRequestDispatcher("/board/searchResult.jsp");
			dis.forward(request, response);
		} else {
			// 리스트 페이지 불러오기 에러 처리
			Script.back(response);
		}
	}
}
