package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PracticeTemplate {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			// 객체가 이미 존재하고 닫혀있지 않다면
			// 재사용
			if(conn != null && !conn.isClosed()) {
				return conn;
			}

			// 파일이나 XML 파일에서 설정 값을 읽어오거나 저장할 때 사용
			Properties prop = new Properties();
			
			String filePath = "driver.xml";
			
			// XML에 읽어온것들을 From안에 적재
			// inputStream 드라이비 xml에 있는것을 읽어온다
			//  driver.xml 파일 내용을 읽어와 prop 에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
			// KEY가 일치하는 value를 반환
			Class.forName(prop.getProperty("driver"));
			String url = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			
			// 사용자 정보를 이용하여 DB에 연결
			conn = DriverManager.getConnection(url, userName, password);
			
			// AutoCommit 끄기
			conn.setAutoCommit(false);
			
			
		} catch(Exception e) {
				e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// 전달받은 커넥션에서 수행한 SQL을 COMMIT하는 메서드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	
	public static void rollback(Connection conn) {
		 try {
			 if(conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		 try {
			 if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Statement stmt) {
		 try {
			 if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(ResultSet rs) {
		 try {
			 if(rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
