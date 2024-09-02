package edu.kh.todolist.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	Map<String, Object> todoListFullView() throws SQLException;

	
}
