package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 를 입력 받아
		// 아이디, 비밀번호가 일치하는 사용자(TB_USER)의
		// 이름을 수정(UADTE) 
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		// ?(placeholder)에 값을 대입할 준비된 Statement
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			String userName = "KH_LDH";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			// AutoCommit 끄기
			conn.setAutoCommit(false);
			
			// sql 작성
			Scanner sc = new Scanner(System.in);
			
			System.out.println("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.println("비밀번호 입력 : ");
			String pw = sc.nextLine();
			
			System.out.println("수정할 이름 입력 : ");
			String name = sc.nextLine();
			
			String sql = """
				UPDATE TB_USER
				SET
					USER_NAME = ?
				WHERE 	
					USER_ID = ?
				AND 
					USER_PW = ?
					""";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?에 알맞은 값 세팅
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			// -> SQL 세팅 끝! -> 실행
			
			// prepareStatement를 이용하여 SQL 실행 시
			// 매개변수 자리에 아무것도 없어야 한다!
			int result = pstmt.executeUpdate();
			
			// 성공 시 "성공" 메시지 + COMMIT
			if(result > 0) {
				System.out.println("수정 성공!");
				conn.commit();
			}
 			// 실패 시 "아이디 또는 비밀번호 불일치" 메시지
			// + ROLLBACK
			else {
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
			}
			
		} catch(Exception e) {
				e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
	}
}
