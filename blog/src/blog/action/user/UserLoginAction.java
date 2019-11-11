package blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

public class UserLoginAction implements Action{
	
	private static final String TAG = "UserLoginAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 하고 리스트 페이지로 이동	
				//null 값 처리해야함, 유효성(Validation 검사)
				String username = request.getParameter("username");
				String rawPassword = request.getParameter("password");
				String rememberMe = request.getParameter("rememberMe");
				String password = SHA256.getEncrypt(rawPassword, "cos");
				
				User user = new User();
				user.setUsername(username);
				user.setPassword(password); //암호화해서 저장해야 함.

				System.out.println(TAG + "username : " + username);
				System.out.println(TAG + "password : " + password);
							
				UserDao dao = new UserDao();
				int result = dao.findByUsernameAndPassword(user);
				if(result == 1) {
					//로그인 성공

					//Remember me 처리 쿠키저장
					//쿠키는 저장할 시간을 지정해야됨
					if(rememberMe != null) {
						Cookie cookie = new Cookie("username", username);
						cookie.setMaxAge(6000); // 100분
						response.addCookie(cookie);
					}else {
						//쿠키 삭제
						Cookie cookie = new Cookie("username", null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					
					//세션 등록
					//세션은 "이미 떠있는것". 싱글톤으로 구현되어 있음.
					HttpSession session = request.getSession();
					user = dao.findByUsername(username);
					session.setAttribute("user", user);
					
					response.sendRedirect("/blog/index.jsp");
					
					//RequestDispatcher dis = request.getRequestDispatcher("/board/list.jsp");
					//dis.forward(request, response);
				}else if(result == 0){
					//로그인 실패
					Script.alert(response, "Login Fail");
					Script.back(response);
				}else {
					//DB 오류
					System.out.println("DB 오류");
					Script.alert(response, "Login Fail");
					Script.back(response);
				}
			}
	}
