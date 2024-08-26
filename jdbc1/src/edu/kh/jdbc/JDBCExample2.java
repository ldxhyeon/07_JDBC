package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		// 입력 받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 조회
		
		/* 1. JDBC 객체 참조용 변수 선언*/
		Connection conn = null; // DB 연결 정보 저장 객체
		Statement stmt = null; 	// SQL 수행 후 반환 객체
		ResultSet rs = null;		// SELECT 조회 결과를 저장하는 객체
		
		try {
			
			/* 2. DriverManager 객체를 이용해서 Connection 객체 생성하기 */
			/* 2-1) Oracle JDOC Driver 객체를 메모리에 로드(적재) 하기 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			/* 2-2) DB 연결 정보 작성 */
			String type = "jdbc:oracle:thin:@";
			
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주석
														 // localhoost == 현재 컴퓨터
			
			String port = ":1521"; // 프로그램 연결을 위한 구분 번호
			String dbName = ":XE"; // DMBS 이름(XE == eXpress Edition)
			
			String userName = "KH_LDH";
			String password = "KH1234";
			/* 2-3) DB 연결 정보와 DriverManager를 이용해서 
			 * Connection 객체 생성 */
			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);
			
			/* 3. SQL 작성 */
			Scanner sc = new Scanner(System.in);
			
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = """
					SELECT 
						EMP_ID,
						EMP_NAME,
						SALARY
					FROM 
						EMPLOYEE
					WHERE
						SALARY > """ + input;
			
			/* 4. Statement 객체 생성 */
			stmt = conn.createStatement();
			
			/* 5. Statement 객체를 이용해서 SQL 수행 후 결과 반환 받기 */
			rs = stmt.executeQuery(sql);
			
			// executeQuery() : SELECT 실행, ResultSet 반환
			// executeUpdate() : DML 실행, 결과 행의 개수 반환(int)
			
		 /* 6.조회 결과가 담겨 있는 ResultSet을 
		  * 커서(Cursor)를 이용해 1행씩 접근해
		  * 각 행에 작성된 컬럼 값 얻어오기
		  * */
			while(rs.next()) {
				// rs.next() : 커서를 다음 행으로 이동
				// 값이 있으면 true, 없으면 false 반환
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				
				System.out.printf("%s / %s / %d원 \n",empId, empName, salary);
				
			}
			
		} catch(Exception e) {
			// 최상위 예외인 Exception을 이용해서 모든 예외를 처리
			// -> 다형성 업캐스팅 적용
			e.printStackTrace();
		} finally {
			/* 7. 사용 완료된 JDBC 객체 자원 반환(close) */
			/* 만들어진 역순으로 close 수행하는 것을 추천 */
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
}
