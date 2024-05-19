package DAO;

public class TodoRequest {
    private String content = "";

    TodoRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
