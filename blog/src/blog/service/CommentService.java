package blog.service;

import blog.dao.CommentDao;
import blog.model.Comment;

public class CommentService {
	
	private CommentDao dao = new CommentDao();
	
	synchronized public Comment write() {
		int result = dao.save(null);
		if(result == 1) {
			Comment comment = dao.findByMaxId();
			return comment;
		}
		return null;
	}
}
