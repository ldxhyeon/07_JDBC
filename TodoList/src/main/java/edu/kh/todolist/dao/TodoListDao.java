package edu.kh.todolist.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

//DAO (Data Access Object) : 데이터가 저장된 파일/DB에 접근하는 역할
public interface TodoListDao {

	Map<String, Object> todoListFullView(Connection conn) throws SQLException;

	int todoAdd(Connection conn, String title, String detail) throws SQLException;

	Todo todoDetailView(Connection conn, int index) throws SQLException;

	int todoComplete(Connection conn, int workNo) throws SQLException;

	int todoUpdate(Connection conn, int workNo, String title, String detail) throws SQLException;

	int todoDelete(Connection conn, int workNo) throws SQLException;
	
	
}
