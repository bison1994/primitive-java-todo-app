package service;

import DAO.TodoDAO;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/todo_app?useSSL=false&characterEncoding=utf8";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "123456";

    public List<TodoDAO> getTodoList () throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = conn.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from todo_list")) {
                List<TodoDAO> res = new ArrayList<>();
                while (resultSet.next()) {
                    TodoDAO todoDAO = new TodoDAO(
                            resultSet.getLong("id"),
                            resultSet.getString("content"),
                            resultSet.getInt("status")
                    );
                    res.add(todoDAO);
                }
                return res;
            }
        }

    }

    public int addTodo (String content) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = conn.createStatement()) {
            // don't forget quotation mark around %s
            return statement.executeUpdate(String.format("INSERT INTO todo_list (content, status) VALUES ('%s', 0)", content));
        }
    }

    public static void main(String[] args) throws SQLException {
        TodoService todoService = new TodoService();
//        List<JSONObject> list = todoService.getTodoList().stream().map(x -> new JSONObject(x)).toList();
//        System.out.println(list);

        int code = todoService.addTodo("111");
        System.out.println(code);
    }
}
