package blog.test;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test2")
public class AjaxTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxTest2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=UTF-8"); // MIME 타입 : 약속되어 있는것.
		
//		try {
//			Thread.sleep(3000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		PrintWriter out = response.getWriter();
		out.println(100);
		out.flush();
	}

}
