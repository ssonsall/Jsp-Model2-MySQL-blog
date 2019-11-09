package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.User;
import blog.util.DBClose;

//싱글톤으로 만들어야 하는데 일단 그냥 함
public class UserDao {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;

	public int findByUsernameAndPassword(User user) {
		final String SQL = "SELECT COUNT(id) FROM user WHERE username = ? AND password = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int result = rs.getInt(1); // 로그인 성공
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}

	public int save(User user) {
		final String SQL = "INSERT INTO user (username, password, email, address, userProfile, createDate ) values (?,?,?,?,?,now())";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserProfile());
			int result = pstmt.executeUpdate(); // 변경된 튜플의 갯수를 리턴
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally { // 위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public int update(User user) {
		final String SQL = "UPDATE user SET password=?,address=?,userProfile=? WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress());
			pstmt.setString(3, user.getUserProfile());
			pstmt.setInt(4, user.getId());
			int result = pstmt.executeUpdate(); // 변경된 튜플의 갯수를 리턴
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally { // 위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}

	public User findByUsername(String username) {
		final String SQL = "SELECT * FROM user WHERE username = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setEmailCheck(rs.getInt("emailCheck"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int updateEmailCheck(int id) {
		final String SQL = "UPDATE user SET emailCheck = 1 WHERE id= ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate(); // 변경된 튜플의 갯수를 리턴
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally { // 위에서 리턴 때려도 무조건 실행
			DBClose.close(conn, pstmt);
		}
	}
	
	public User findById(int id) {
		final String SQL = "SELECT * FROM user WHERE id = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setEmailCheck(rs.getInt("emailCheck"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
}
