package blog.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.model.Comment;

@WebServlet("/test/reply")
public class AjaxTest5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxTest5() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=UTF-8"); // MIME 타입 : 약속되어 있는것.
		
		//json data 받기
		BufferedReader in = request.getReader();
		String replyJsonData = in.readLine();
		//json data sysout 해보기
		System.out.println("replyJsonData >> "+replyJsonData);
		//reply model에 맞춰서 java object로 변경
		Gson gson = new Gson();
		Comment reply = gson.fromJson(replyJsonData, Comment.class);
		
		//java object를 sysout으로 출력
		System.out.println("id >>" + reply.getId());
		System.out.println("boardId >>" + reply.getBoardId());
		System.out.println("userId >>" + reply.getUserId());
		System.out.println("content >>" + reply.getContent());
		System.out.println("createDate >>" + reply.getCreateDate());
		
		PrintWriter out = response.getWriter();
		out.println("ok");
		out.flush();
	}
}
