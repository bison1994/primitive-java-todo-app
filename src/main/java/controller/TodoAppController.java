package controller;

import org.json.JSONArray;
import org.json.JSONObject;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class TodoAppController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TodoService todoService = new TodoService();

        List<JSONObject> list = null;
        try {
            list = todoService.getTodoList().stream().map(x -> new JSONObject(x)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("list", new JSONArray(list));
        res.put("code", 0);
        res.put("msg", "success");
        res.put("data", data);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }
}

