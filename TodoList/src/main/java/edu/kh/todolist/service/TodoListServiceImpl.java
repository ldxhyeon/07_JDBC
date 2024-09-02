package edu.kh.todolist.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static edu.kh.todolist.common.JDBCTemplate.*;

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
	
}
