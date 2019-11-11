package blog.util;

import blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthUser {
	private int id;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expire_in;
	
	private User user = new User(); //username은 email에서 앞부분 따와서 집어넣어버리면 된다 (사용자한테 선택권 안줌)
	
	
}
