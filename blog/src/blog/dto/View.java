package blog.dto;

import java.sql.Timestamp;

import blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class View {
	private int id;
	private String username;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	private String userProfile;
	private String previewImg; //DB와 상관없음
}
