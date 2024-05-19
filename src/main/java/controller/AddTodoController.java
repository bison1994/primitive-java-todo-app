package controller;

import DAO.TodoRequest;
import com.google.gson.Gson;
import org.json.JSONObject;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/todo/add")
public class AddTodoController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        TodoService todoService = new TodoService();
        String reqBody = "";
        JSONObject res = new JSONObject();

        try {
            reqBody = request.getReader().lines().collect(Collectors.joining());

            Gson gson = new Gson();
            TodoRequest todoRequest = gson.fromJson(reqBody, TodoRequest.class);

            int code = todoService.addTodo(todoRequest.getContent());

            if (code == 1) {
                res.put("code", 0);
                res.put("msg", "success");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            res.put("code", 1);
            res.put("msg", reqBody);
        }

        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.print(res);
            out.flush();
        } catch (Exception ignored) {

        }
    }

    public static void main (String[] args) {
        String s = "{\"content\":\"read book\"}";
        Gson gson = new Gson();
        TodoRequest todoRequest = gson.fromJson(s, TodoRequest.class);

        String content = todoRequest.getContent();
        System.out.println(String.format("INSERT INTO todo_list (content, status) VALUES (%s, 0)", content));
    }
}
