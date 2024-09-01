package edu.kh.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static edu.kh.jdbc.common.PracticeTemplate.*;

import edu.kh.jdbc.common.PracticeTemplate;
import edu.kh.jdbc.dao.PracticeDao;
import edu.kh.jdbc.dto.User;
import oracle.jdbc.replay.ConnectionInitializationCallback;

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
	
	


	public List<User> searchUserName(String searchName) throws SQLException {
		
		Connection conn = getConnection();
		
		List<User> userList = dao.searchUserName(conn, searchName);
		
		close(conn);
		
		return userList;
	}


	public User selectUserNo(int userNo) throws SQLException {
		
		Connection conn = getConnection();
		
		User user = dao.selectUserNo(conn, userNo);
		
		close(conn);
		
		return user;
	}


	public int userNoDelete(int userNo) throws SQLException {
		
		int result = 0;;
		
		Connection conn = getConnection();
		
		result = dao.userNoDelete(conn, userNo);
		
		close(conn);
		
		return result;
	}


	public int selectUserNo(String userid, String userPw) throws SQLException {
		
		Connection conn = getConnection();
		
		int userNo = dao.selectUser(conn, userid, userPw);
		
		close(conn);
		
		return userNo;
	}


	public int updateName(String updateName, int userNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.updateName(conn, updateName, userNo);
		
		close(conn);
		
		return result;
	}

}
