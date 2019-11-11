package blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.UserDao;
import blog.model.User;


@WebServlet("/auth")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//빼먹지 말자. 빼먹으면 한글깨짐.
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		UserDao dao = new UserDao();
		User user = dao.findById(Integer.parseInt(id));
		String email = user.getEmail();
		//세 건이니깐 그냥 셋 어트리뷰트.. 그냥 귀찮아서
		request.setAttribute("id", id);
		request.setAttribute("code", code);
		request.setAttribute("email", email);
		
		RequestDispatcher dis = request.getRequestDispatcher("/mail/gmailAuthCheck.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
