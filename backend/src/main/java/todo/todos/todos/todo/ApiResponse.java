package todo.todos.todos.todo;

public class ApiResponse {
    private String message;

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse [message=" + message + "]";
    }
    
}
