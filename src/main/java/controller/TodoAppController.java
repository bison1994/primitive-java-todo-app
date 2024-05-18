package controller;

import DAO.TodoDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class TodoAppController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TodoService todoService = new TodoService();

        List<JSONObject> list = todoService.getTodoList().stream().map(x -> new JSONObject(x)).toList();

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
