package blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void back(HttpServletResponse response) {		
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>"); 			
			out.println("history.back();");
			out.println("</script>");
			out.flush(); // 버퍼비우기
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void alert(HttpServletResponse response, String message) {		
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+ message +"');");
			out.println("</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
