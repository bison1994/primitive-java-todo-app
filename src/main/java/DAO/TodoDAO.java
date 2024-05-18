package DAO;

public class TodoDAO {
    private String content;
    private Long id;
    private int status;

    public TodoDAO (Long id, String content, int status) {
        this.content = content;
        this.id = id;
        this.status = status;
    }

    public String getContent() {
        return this.content;
    }

    public Long getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

}
