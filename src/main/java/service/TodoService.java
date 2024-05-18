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

    public List<TodoDAO> getTodoList () {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TodoService todoService = new TodoService();
        List<JSONObject> list = todoService.getTodoList().stream().map(x -> new JSONObject(x)).toList();
        System.out.println(list);
    }
}
