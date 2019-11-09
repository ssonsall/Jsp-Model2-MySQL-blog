package blog.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
	private int statusCode; //ex) 1, -1
	private String status; //ex) ok
	private String StatusMessage; // ex) Parsing error, page not found
}
