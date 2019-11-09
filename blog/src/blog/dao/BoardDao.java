package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import blog.dto.View;
import blog.model.Board;
import blog.util.DBClose;

public class BoardDao {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public Board findById(int id) {
		final String SQL = "SELECT * FROM board WHERE id=?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			Board board = new Board();
			if(rs.next()) {
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("UserId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				return board;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt, rs);;
		}
		return null;
	}
	
	public int delete(int id) {
		final String SQL = "DELETE FROM board WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);			
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴	
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
		
	}
	
	public int save(Board board) {
		final String SQL = "INSERT INTO board (userId, title, content, createDate) values (?,?,?,now())";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴					
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public Vector<Board> findByAll() {
		final String SQL = "SELECT * FROM board";
		conn = DBConn.getConnection();
		Vector<Board> boardList = new Vector<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));				
				board.setUserId(rs.getInt("UserId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boardList.add(board);
			}
			return boardList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt, rs);;
		}
	}
	

	
	//3건만 가져오기 인기있는거
	public List<Board> findOrderByReadCountDesc(){
		final String SQL = "SELECT * FROM board ORDER BY readCount DESC LIMIT 3";
		conn = DBConn.getConnection();
		Vector<Board> boardList = new Vector<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("UserId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boardList.add(board);
			}
			return boardList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt, rs);;
		}
	}
	
	public int increaseReadCount(int id) {
		final String SQL = "UPDATE board SET readCount = readCount + 1 WHERE id = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);			
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴					
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public int update(Board board) {
		final String SQL = "UPDATE board SET userId=?, title=?, content=?, createDate=now() WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getId());
			int result = pstmt.executeUpdate();	//변경된 튜플의 갯수를 리턴					
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;						
		} finally { //위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public List<View> findByUserJoinBoard() {
		final String SQL = "SELECT user.username, board.id, board.title, board.content, board.readCount, board.createDate FROM board JOIN user WHERE user.id = board.userId ORDER BY board.id DESC";
		conn = DBConn.getConnection();
		List<View> viewList = new Vector<View>();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
			View view = new View();
			view.setId(rs.getInt("id"));
			view.setUsername(rs.getString("username"));
			view.setTitle(rs.getString("title"));
			view.setContent(rs.getString("content"));
			view.setReadCount(rs.getInt("readCount"));
			view.setCreateDate(rs.getTimestamp("createDate"));
			viewList.add(view);
			}
			return viewList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
	
	public List<View> findByUserJoinBoard(int page) {
		final String SQL = "SELECT user.username, board.id, board.title, board.content, board.readCount, board.createDate FROM board JOIN user WHERE user.id = board.userId ORDER BY board.id DESC LIMIT ?,3";
		conn = DBConn.getConnection();
		List<View> viewList = new Vector<View>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page-1)*3);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
			View view = new View();
			view.setId(rs.getInt("id"));
			view.setUsername(rs.getString("username"));
			view.setTitle(rs.getString("title"));
			view.setContent(rs.getString("content"));
			view.setReadCount(rs.getInt("readCount"));
			view.setCreateDate(rs.getTimestamp("createDate"));
			viewList.add(view);
			}
			return viewList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
	
	public List<View> findByKeyWord(int page, String keyword) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT board.*, user.username ");
		sb.append("FROM board JOIN user ");
		sb.append("WHERE board.userId = user.id ");
		sb.append("AND (board.title LIKE ? OR board.content LIKE ? OR user.username LIKE ?) ");
		sb.append("ORDER BY board.id DESC LIMIT ?,3");
		
		keyword = "%"+keyword+"%";
		System.out.println("keyword dao >> " + keyword);
//		System.out.println("full query >>" + sb.toString());
		final String SQL = sb.toString();
		
		conn = DBConn.getConnection();
		List<View> viewList = new Vector<View>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			pstmt.setInt(4, (page-1)*3);
			System.out.println("full query >>" + pstmt.toString());
			rs = pstmt.executeQuery();			
			while(rs.next()) {
			View view = new View();
			view.setId(rs.getInt("id"));
			view.setUsername(rs.getString("username"));
			view.setTitle(rs.getString("title"));
			view.setContent(rs.getString("content"));
			view.setReadCount(rs.getInt("readCount"));
			view.setCreateDate(rs.getTimestamp("createDate"));
			viewList.add(view);
			}
			return viewList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
	
	public View findByUserJoinBoardForDetail(int id) {
		//?에 현재 누른 게시글의 ID값
		final String SQL = "SELECT user.username, user.userProfile ,board.title, board.content, board.readCount, board.createDate FROM board JOIN user WHERE board.id = ? AND board.userId = user.id";
		conn = DBConn.getConnection();
		View detailView = new View();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				detailView.setId(id);
				detailView.setUsername(rs.getString("username"));
				detailView.setUserProfile(rs.getString("userProfile"));
				detailView.setTitle(rs.getString("title"));
				detailView.setContent(rs.getString("content"));
				detailView.setReadCount(rs.getInt("readCount"));
				detailView.setCreateDate(rs.getTimestamp("createDate"));
			}
			return detailView;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
	
	public int countRow() {
		final String SQL = "SELECT COUNT(id) AS dataNum FROM board";
		conn = DBConn.getConnection();
		int count = 0;
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				count = rs.getInt("dataNum");
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
	
	public int countSearchRow(String keyword) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(board.id) As dataNum ");
		sb.append("FROM board JOIN user ");
		sb.append("WHERE board.userId = user.id ");
		sb.append("AND (board.title LIKE ? OR board.content LIKE ? OR user.username LIKE ?)");
		final String SQL = sb.toString();
		keyword = "%"+keyword+"%";
		conn = DBConn.getConnection();
		int count = 0;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				count = rs.getInt("dataNum");
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	}
}
