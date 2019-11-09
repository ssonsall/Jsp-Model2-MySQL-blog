package blog.action.board;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.dao.CommentDao;
import blog.dto.View;
import blog.model.Comment;
import blog.model.User;
import blog.util.Script;
import blog.util.Utils;

public class BoardDetailAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.equals("")) {
			return;
		}

		BoardDao dao = new BoardDao();
		CommentDao commentDao = new CommentDao();
		
		Vector<Comment> commentList =  new Vector<Comment>();
		commentList = commentDao.findByBoardId(Integer.parseInt(id));
		
		View view = new View();
		view = dao.findByUserJoinBoardForDetail(Integer.parseInt(id));
		
		if (view != null) {
			// 조회수 증가 - 쿠키를 저장해서(1일) 쿠키가 있을때 새로고침해도 조회수 증가 안되도록
			int result = -1;
			BoardDao bDao = new BoardDao();
			HttpSession session  = request.getSession();
			User user = (User)session.getAttribute("user");
			String curUsername = "";
			
			Cookie cookieList[] = request.getCookies();
			for (Cookie c : cookieList) {
				if(user == null) {
					User userTemp = new User();
					userTemp.setUsername("");					
					user = userTemp;
				}				
				if(c.getValue().equals(id+"a"+user.getUsername())) {
					curUsername = c.getValue();
				}				
			}	
			
			if(!user.getUsername().equals("") && !curUsername.equals(id+"a"+user.getUsername())) {
				result = bDao.increaseReadCount(Integer.parseInt(id));
				Cookie cookie = new Cookie(id+"salt"+user.getUsername(), id+"a"+user.getUsername());
				cookie.setMaxAge(86400); //24시간
				response.addCookie(cookie);
			}else {
				result = 1;
			}
			
			if (result == 1) {
				// 유튜브 주소 파싱 (글 내용에 유튜브 주소가 있으면 유튜브 영상 띄우기)
				Utils.setPreviewYoutube(view); // 레퍼런스 넘기니깐 리턴 안받아도 됨 넘겨서 변경되면 값이 변경된거임

				// 상세보기 정보 담아서 디테일 페이지로 이동
				request.setAttribute("view", view);
				request.setAttribute("commentList", commentList);
				RequestDispatcher dis = request.getRequestDispatcher("/board/detail.jsp");
				dis.forward(request, response);
				
			} else {
				Script.back(response);
			}
		} else {
			// 상세보기 페이지 불러오기 에러 처리
			Script.back(response);
		}
	}
}
