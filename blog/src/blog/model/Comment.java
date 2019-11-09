package blog.model;

import java.sql.Timestamp;

import blog.dao.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//id int auto_increment primary key,
//userId int,
//boardId int,
//content varchar(300) not null,
//createDate timestamp,

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private ResponseData responseData = new ResponseData(); //DB와 무관
	private int id;
	private int userId;
	private int boardId;
	private String content;
	private Timestamp createDate;
	private User user = new User(); //DB와 무관
}
