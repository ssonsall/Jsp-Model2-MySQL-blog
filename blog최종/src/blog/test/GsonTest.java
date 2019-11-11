package blog.test;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import blog.model.Comment;

public class GsonTest {
	@Test
	public void gsonTest1() {
		Comment comment = new Comment();
		comment.setId(1);
		comment.setBoardId(10);
		comment.setUserId(5);
		comment.setContent("test");
		comment.setCreateDate(null);
		comment.getResponseData().setStatus("ok");
		comment.getUser().setUsername("ssar");
		
		Gson gson = new Gson();
		String json = gson.toJson(comment);
		System.out.println("됨? >> " + json);
	}
}
