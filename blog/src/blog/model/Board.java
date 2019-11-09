package blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//id int auto_increment primary key,
//userId int,
//title varchar(100) not null,
//content longtext,
//readCount int default 0,
//createDate timestamp,

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int id;
	private int userId;
	private String username; //DB와 상관없음. search용
	private String previewImg;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
}
