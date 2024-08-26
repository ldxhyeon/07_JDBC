package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// 부서명을 입력 받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을
		// 직급코드 오름 차순으로 조회
		
		// hint : SQL에서 문자열은 양쪽에 ''(홑따욤표)필요
		// ex) 총무부 입력 => '총무부'
		
		// DB 정보 저장할 객체 생성
		Connection conn = null;
		// SQL DB 전달
		Statement stmt = null;
		// SELECT 조회 결과를 저장하는 객체
		ResultSet rs = null;
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			String type = "jdbc:oracle:thin:@";
			
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주석
														 // localhoost == 현재 컴퓨터
			
			String port = ":1521"; // 프로그램 연결을 위한 구분 번호
			String dbName = ":XE"; // DMBS 이름(XE == eXpress Edition)
			
			String userName = "KH_LDH";
			String password = "KH1234";
													
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("부서명을 입력하세요.");
			String deptTitle = sc.next(); 
			
			String sql = """
					SELECT
						EMP_ID,
						EMP_NAME,
						DEPT_TITLE,
						JOB_NAME
					FROM
						EMPLOYEE
					JOIN
						JOB USING(JOB_CODE)
					LEFT JOIN
						DEPARTMENT ON(DEPT_ID = DEPT_CODE)
					WHERE
						DEPT_TITLE = '""" + deptTitle +
						"' ORDER BY JOB_CODE ASC";
					
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// Statement 객체를 이용해서 SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			boolean flag = true;
			
			while(rs.next()) {
				
				flag = false;
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empDeptTitlte = rs.getString("DEPT_TITLE");
				String empjobName = rs.getString("JOB_NAME");
				
				System.out.printf("%s/ %s / %s / %s \n",empId, empName, empDeptTitlte, empjobName);
			}
			
			if(flag) {
				System.out.println("일치하는 부서가 없습니다.");
			}
			
		} catch(Exception e) {
				e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) rs.close();
				if(conn != null) rs.close();
				
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		
		
	}

	
}
