package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import blog.model.Comment;
import blog.util.DBClose;

//싱글톤으로 만들어야 하는데 일단 그냥 함
public class CommentDao {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;

	
	
	public Comment findByMaxId() {		
		StringBuffer sb = new StringBuffer();
		sb.append("select comment.id, comment.userId, comment.boardId, comment.content, comment.createDate, user.username, user.userProfile ");
		sb.append("from comment JOIN user ");
		sb.append("where comment.userId = user.id ");
		sb.append("AND comment.id = (select max(id) from comment)");
		final String SQL = sb.toString();
		
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUserId(rs.getInt("userId"));
				comment.setBoardId(rs.getInt("boardId"));
				comment.setContent(rs.getString("content"));
				comment.setCreateDate(rs.getTimestamp("createDate"));
				comment.getUser().setUsername(rs.getString("username"));
				comment.getUser().setUserProfile(rs.getString("userProfile"));
				return comment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int save(Comment comment) {
		final String SQL = "INSERT INTO comment (userId, boardId, content, createDate) values (?,?,?,now())";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, comment.getUserId());
			pstmt.setInt(2, comment.getBoardId());
			pstmt.setString(3, comment.getContent());			
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴					
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public int delete(int commentId) {
		final String SQL = "DELETE FROM comment WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentId);			
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴	
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
		
	}
	
	public Vector<Comment> findByBoardId(int boardId) {
		//final String SQL = "SELECT comment.id,comment.userId,comment.boardId,comment.content,comment.createDate,user.username FROM comment JOIN user WHERE boardId=?";
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT comment.id, comment.userId, comment.boardId, comment.content, comment.createDate, user.username, user.userprofile ");
		sb.append("FROM comment JOIN user ");
		sb.append("WHERE comment.userId = user.id AND boardId=? ");
		sb.append("ORDER BY comment.id DESC");
		final String SQL = sb.toString();
		
		conn = DBConn.getConnection();
		Vector<Comment> commentList = new Vector<Comment>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setBoardId(rs.getInt("boardId"));
				comment.setUserId(rs.getInt("userId"));
				comment.setContent(rs.getString("content"));
				comment.setCreateDate(rs.getTimestamp("createDate"));
				comment.getUser().setUsername(rs.getString("username"));
				comment.getUser().setUserProfile(rs.getString("userprofile"));
				commentList.add(comment);
			}
			return commentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt, rs);;
		}
	}
	
	
}
