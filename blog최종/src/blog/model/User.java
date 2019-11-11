package blog.model;
//id int auto_increment primary key,
//username varchar(100) not null unique,
//password varchar(100) not null,
//email varchar(100) not null,
//createDate timestamp

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String userProfile; //이미지 경로 ( 파일 업로드 ) DB에는 파일 경로만 넣고, 이미지 파일은 서버 하드에 저장
	private String username;
	private String password;
	private String email;
	private String address;
	private int emailCheck;
	private Timestamp createDate;
}
