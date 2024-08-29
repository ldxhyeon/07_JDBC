package edu.kh.jdbc.service;

// 지정된 클래스의 static 메서드를 모두 얻어와 사용
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dao.UserDaoImpl;
import edu.kh.jdbc.dto.User;

public class UserServiceImpl implements UserService{
	
	// 필드
	private UserDao dao = new UserDaoImpl();

	@Override
	public int insertUser(User user) throws Exception{
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		
		// 2. DAO 메서드 호출 후 결과 반환
		int result = dao.insertUser(conn, user);
		
		// 3. DML 수행 -> 트랜잭션 처리
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
			
		
		return result;
	}
	
	
	@Override
	public int idCheck(String userId) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}


	@Override
	public User login(String userId, String userPw) throws Exception {
		
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User loginUser = dao.login(conn, userId, userPw);
		
		close(conn);
		
		return loginUser;
	}
	
	
	@Override
	public List<User> selectAll() throws SQLException {
		
		Connection conn = getConnection();
		
		List<User> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

}
