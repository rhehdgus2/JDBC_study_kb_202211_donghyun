package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

// @RequiredArgsConstructor	: Argument가 필수라는 의미
public class UserDao {		// UserDao : DB에 접근하는 객체
	
//	private final DBConnectionMgr pool;		// final은 상수라 값이 항상 초기화되야함
	
//	public UserDao(DBConnectionMgr pool) {		// 11 ~ 13 : @RequiredArgsConstructor
//		this.pool = pool;
//	}
	
	private DBConnectionMgr pool;
	
	public UserDao() {
		pool = DBConnectionMgr.getInstance();
	}

	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(0, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			successCount = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();		// getGeneratedKeys : 오토 인크리먼트된 키 값을 가져와준다.
			if(rs.next()) {
				user.setUser_id(rs.getInt(1));		// 키값을 바로 유저에다가 Set해준다.
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);	// 객체 소멸시켜준다.
		}
		
		return successCount;
	}
	
	public User getUser(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		User user = null;
		
		try {
			con = pool.getConnection();
			sql = "select id, username, name, email, phone from user_mst"
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = User.builder()
						.user_id(rs.getInt(1))
						.username(rs.getString(2))
						.name(rs.getString(3))
						.email(rs.getString(4))
						.phone(rs.getString(5))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return user;
		}
	}
}
