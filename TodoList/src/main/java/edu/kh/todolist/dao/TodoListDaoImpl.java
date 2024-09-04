package edu.kh.todolist.dao;

import static edu.kh.todolist.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.todolist.common.JDBCTemplate;
import edu.kh.todolist.dto.Todo;


public class TodoListDaoImpl implements TodoListDao {
	
	// 필드
	// JDBC 객체 참조 변수 + Properties 참조 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop; 
	// ->  K,V가 모두 String인 Map, 파일 입출력이 쉬움
	
	
	// 기본 생성자
	public TodoListDaoImpl() {
		
		// 객체 생성 시 
		// 외부에 존재하는 sql.xml 파일을 읽어와
		// prop에 저장
		
		try {
			String filePath = 
					JDBCTemplate.class
					.getResource("/edu/kh/todolist/sql/sql.xml").getPath();
			
			// 지정된 경로의 XML 파일 내용을 읽어와
			// Properties 객체에 K:V 세팅
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public Map<String, Object> todoListFullView(Connection conn) throws SQLException {
		
		// 결과 저장용 변수 선언
		List<Todo> list = new ArrayList<Todo>();
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int completeCount = 0;
		
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				int workNo = rs.getInt("LIST_NO");
				String title  = rs.getString("LIST_TITLE");
				String detail  = rs.getString("LIST_DETAIL");
				boolean complete  = rs.getBoolean("LIST_COMPLETE");
				String enrollDate  = rs.getString("ENROLL_DATE");
				
			
				Todo user = new Todo(workNo, title, detail, complete, enrollDate);
					
				list.add(user);
			}
			
			for(Todo todo : list) {
				if(todo.isComplete()) {
					completeCount++;
				}
			}
			
			
			 resultMap.put("todoList", list);
			 resultMap.put("completeCount", completeCount);
			 
			 System.out.println(list);
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		// test
		
		return resultMap;
	}
	
	
	
	
	
	@Override
	public int todoAdd(Connection conn, String title, String detail) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoAdd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, detail);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	@Override
	public Todo todoDetailView(Connection conn, int index) throws SQLException {
		
		Todo todo = null;
		
		try {
			
			
			String sql = prop.getProperty("todoDetailView");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				int workNo = rs.getInt("LIST_NO");
				String title  = rs.getString("LIST_TITLE");
				String detail  = rs.getString("LIST_DETAIL");
				boolean complete  = rs.getBoolean("LIST_COMPLETE");
				String enrollDate  = rs.getString("ENROLL_DATE");
			
				todo = new Todo(workNo, title, detail, complete, enrollDate);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}
	
	
	@Override
	public int todoComplete(Connection conn, int workNo) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoComplete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, workNo);
			
			result = pstmt.executeUpdate();
					
			
			
		} finally {
			close(pstmt);
		}


		return result;
	}
	
	
	
	@Override
	public int todoUpdate(Connection conn, int workNo, String title, String detail) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoUpdate");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, detail);
			pstmt.setInt(3, workNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	@Override
	public int todoDelete(Connection conn, int workNo) throws SQLException {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoDelete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, workNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
