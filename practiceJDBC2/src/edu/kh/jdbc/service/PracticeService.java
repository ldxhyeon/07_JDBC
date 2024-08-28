package edu.kh.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static edu.kh.jdbc.common.PracticeTemplate.*;

import edu.kh.jdbc.common.PracticeTemplate;
import edu.kh.jdbc.dao.PracticeDao;
import edu.kh.jdbc.dto.User;

public class PracticeService {

	private PracticeDao dao = new PracticeDao();
	
	
	// 1. User 등록(INSERT)
	public boolean insertUser(User user) throws SQLException {
		
		// DB연결 객체 생성
		Connection conn = PracticeTemplate.getConnection();
		
		boolean result = dao.insertUser(conn, user);
		
		if(result) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		// DB 연결 종료
		close(conn);
		
		return result;
	}
	
	
	// 2. User 전체 조회(SELECT)
	public List<User> selectAll() throws SQLException {
		
		// DB 연결 객체
		Connection conn = getConnection();
		
		// DB 연결 종료
		List<User> user = dao.selectAll(conn);
		
		close(conn);
		
		return user;
	}

}
