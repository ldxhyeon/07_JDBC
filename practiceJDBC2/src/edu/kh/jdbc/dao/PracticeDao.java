package edu.kh.jdbc.dao;

import static edu.kh.jdbc.common.PracticeTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.dto.User;

public class PracticeDao {
	
	//test
	// 쿼리작성
	private Statement stmt = null;
	// ? 쿼리 작성
	private PreparedStatement pstmt =null;
	// SQL 수행 결과 받는 객체
	private ResultSet rs = null;
	
	
	public boolean insertUser(Connection conn, User user) throws SQLException {
		
		boolean result = false;
		
		try {
			
			String sql = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
				""";

			// 데이터베이스에 값 넣으려면 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			
			// SQL 쿼리가 실행된 후 영향을 받은 행(row)의 수
			int rowsAffected = pstmt.executeUpdate();
			
			// 
			result = (rowsAffected > 0);
					
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public List<User> selectAll(Connection conn) throws SQLException {
		
		List<User> list = new ArrayList<User>();
		
		try {
			
			String sql = """
						SELECT
							USER_NO,
							USER_ID,
							USER_PW,
							USER_NAME,
							TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"')ENROLL_DATE
						FROM
							TB_USER
						ORDER BY
							1
					""";
			
			// 데이터베이스에 SQL 명령을 전달
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//  SQL 수행 결과 행 읽어오기
			while(rs.next()) {
					int userNumber = rs.getInt("USER_NO");
					String userId = rs.getString("USER_ID");
					String userPw = rs.getString("USER_PW");
					String userName = rs.getString("USER_NAME");
					
					String enrollDate = rs.getString("ENROLL_DATE");
					
					User user = new User(userNumber, userId, userPw, userName, enrollDate);
					
					list.add(user);
					
			}
			
			
		} finally {
			close(rs);
		}
		return list;
	}
	
	
	


	public List<User> searchUserName(Connection conn, String searchName) throws SQLException {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			
			String sql = """
					SELECT
						USER_NO,
						USER_ID,
						USER_PW,
						USER_NAME,
						TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"')ENROLL_DATE
					FROM
						TB_USER
					WHERE
						 USER_NAME LIKE '%' || ? || '%'
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId  = rs.getString("USER_ID");
				String userPw  = rs.getString("USER_PW");
				String userName  = rs.getString("USER_NAME");
				String enrollDate  = rs.getString("ENROLL_DATE");
				
		
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				
				userList.add(user);
			}
			
			
			
			
		} finally {
				close(pstmt);
				close(rs);
		}
		
		
		
		
		return userList;
	}
	
	
	
	


	public User selectUserNo(Connection conn, int userNo) throws SQLException {
		
		User user = null;
		
		try {
			
			String sql = """
					SELECT
						USER_NO,
						USER_ID,
						USER_PW,
						USER_NAME,
						TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"')ENROLL_DATE
					FROM
						TB_USER
					WHERE
						 USER_NO = ?
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String userId  = rs.getString("USER_ID");
				String userPw  = rs.getString("USER_PW");
				String userName  = rs.getString("USER_NAME");
				String enrollDate  = rs.getString("ENROLL_DATE");
				
		
				user = new User(userNo, userId, userPw, userName, enrollDate);
				
			}
			
			
			
			
		} finally {
				close(pstmt);
				close(rs);
		}
		
		
		return user;
	}

	
	
	
	
	

	public int userNoDelete(Connection conn, int userNo) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = """
					DELETE
					FROM
						TB_USER
					WHERE
						USER_NO = ?
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
				close(pstmt);
		}
		
		
		return result;
	}


	public int selectUser(Connection conn, String userid, String userPw) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = """
					SELECT
						USER_NO
					FROM
						TB_USER
					WHERE
						USER_ID = ?
						AND
						USER_PW = ?
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("USER_NO");
			}
			
			
		} finally {
				close(pstmt);
				close(rs);
		}
		
		
		return result;
	}

	
	
	
	

	public int updateName(Connection conn, String updateName, int userNo) throws SQLException {
		
		
		int result = 0;
		
		try {
			
			String sql = """
					UPDATE
						TB_USER
					SET
						USER_NAME = ?
					WHERE
						USER_NO = ?
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateName);
			pstmt.setInt(2, userNo);
			
			// 쿼리 실행 후 영향을 받은 행의 수
			result = pstmt.executeUpdate();
			
		} finally {
				close(pstmt);
				close(rs);
		}
		
		return result;
	}


	public int idCheck(Connection conn, String userId) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = """
					SELECT
						COUNT(*)
					FROM
						TB_USER
					WHERE
						USER_ID = ?
				""";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			// rs객체 반환
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
			
		} finally {
				close(pstmt);
				close(rs);
		}
		
		
		
		
		return result;
	}
	
	
	
	
	

}
	