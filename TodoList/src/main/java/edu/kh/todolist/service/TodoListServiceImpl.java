package edu.kh.todolist.service;

import static edu.kh.todolist.common.JDBCTemplate.*;

import static edu.kh.todolist.common.JDBCTemplate.close;
import static edu.kh.todolist.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dao.TodoListDao;
import edu.kh.todolist.dao.TodoListDaoImpl;
import edu.kh.todolist.dto.Todo;


public class TodoListServiceImpl implements TodoListService{
	
	private TodoListDao dao = new TodoListDaoImpl();
	

	@Override
	public Map<String, Object> todoListFullView() throws SQLException {
		
		Connection conn = getConnection();
		
		Map<String, Object> map = dao.todoListFullView(conn);	
		
		List<Todo> todoList = (List<Todo>)map.get("todoList");
		
		close(conn);
		
		return map;
	}
	
	
	@Override
	public int todoAdd(String title, String detail) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.todoAdd(conn, title, detail);
		
		close(conn);
		
		return result;
	}
	
	
	@Override
	public Todo todoDetailView(int index) throws SQLException {
		
		Connection conn = getConnection();
		
		Todo todo = dao.todoDetailView(conn, index);
		
		close(conn);
		
		return todo;
	}
	
	
	@Override
	public int todoComplete(int workNo) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.todoComplete(conn, workNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return result;
	}
	
	
	@Override
	public int todoUpdate(int workNo, String title, String detail) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.todoUpdate(conn, workNo, title, detail);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return result;
	}
	
	
	
	@Override
	public int todoDelete(int workNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.todoDelete(conn, workNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	
}
