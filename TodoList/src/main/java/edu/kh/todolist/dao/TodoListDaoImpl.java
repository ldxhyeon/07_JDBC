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
		
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				String title  = rs.getString("LIST_TITLE");
				String detail  = rs.getString("LIST_DETAIL");
				boolean complete  = rs.getBoolean("LIST_COMPLETE");
				String enrollDate  = rs.getString("ENROLL_DATE");
				
			
				Todo user = new Todo(title, detail, complete, enrollDate);
					
				list.add(user);
				
			}
			
			 resultMap.put("todoList", list);
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return resultMap;
	}
	
}
