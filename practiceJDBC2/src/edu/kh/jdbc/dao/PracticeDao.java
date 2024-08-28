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
							USER_NO ASC
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

}
