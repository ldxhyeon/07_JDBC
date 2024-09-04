package edu.kh.todolist.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	Map<String, Object> todoListFullView() throws SQLException;

	int todoAdd(String title, String detail) throws SQLException;

	Todo todoDetailView(int index) throws SQLException;

	int todoComplete(int workNo) throws SQLException;

	int todoUpdate(int workNo, String title, String detail) throws SQLException;

	int todoDelete(int workNo) throws SQLException;
	

	
}
