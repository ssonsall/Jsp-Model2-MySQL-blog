package blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.Script;

public class UserAuthUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDao dao = new UserDao();
		int result = dao.updateEmailCheck(Integer.parseInt(id));
		HttpSession session = request.getSession();

		// 인증처리하고 index페이지로 갈때는 성공했는지 실패했는지 알림창 띄워주기 위해 쿼리스트링 날려줌 1 = 성공, 0 = 실패
		if (result == 1) {
			// email인증 최종완료
			// 조인 직후 이메일 인증처리를 한 경우(세션이 없다) 세션이 없으면 null이다.
			if (null == session.getAttribute("user")) {
				response.sendRedirect("http://localhost:8000/blog/user/login.jsp?authStatus=1"); // 로그인해야하니깐 로그인으로 보낸다.
			} else {
				// 로그인 상태에서 이메일 인증처리를 한 경우(세션이 있다) -> 리스트로 보낸다.
				User user = new User();
				user = dao.findById(Integer.parseInt(id));
//				session.invalidate();
//				session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("http://localhost:8000/blog/index.jsp?authStatus=1");
			}
		} else {
			// 조인 직후 이메일 인증처리를 한 경우(세션이 없다) 세션이 없으면 null이다.
			if (null == session.getAttribute("user")) { // id는 Integer가 아니라 int형이라 null check가 안된다.
				response.sendRedirect("http://localhost:8000/blog/user/login?authStatus=0.jsp"); // 로그인해야하니깐 로그인으로 보낸다.
			} else {
				// 로그인 상태에서 이메일 인증처리를 한 경우(세션이 있다) -> 리스트로 보낸다.
				response.sendRedirect("http://localhost:8000/blog/index.jsp?authStatus=0");
			}
		}
	}
}
