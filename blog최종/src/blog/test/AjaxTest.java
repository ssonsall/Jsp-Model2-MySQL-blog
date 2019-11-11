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

@WebServlet("/test")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 1;

	public AjaxTest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=UTF-8"); // MIME 타입 : 약속되어 있는것.
		
		// 요청온 데이터 처리
		BufferedReader in = request.getReader();
		String replyJsonString = in.readLine();
		System.out.println("요청온 데이터 >> " + replyJsonString);

		// 응답할 데이터 처리
		Gson gson = new Gson();
		Comment reply = gson.fromJson(replyJsonString, Comment.class);

		System.out.println("id " + reply.getId()); // 숫자 null은 0으로 떨어짐
		System.out.println("boardId " + reply.getBoardId());
		System.out.println("userId " + reply.getUserId());
		System.out.println("content " + reply.getContent());
		System.out.println("createDate " + reply.getCreateDate());

		String jsonData = "{\"name\":\"손흥민\",\"sal\":100}";
		PrintWriter out = response.getWriter();
		out.println(jsonData);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
