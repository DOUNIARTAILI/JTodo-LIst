package todo.todos.todos.todo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    @GetMapping()
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addTodo(@RequestBody Todo todo) {
        todoService.addNewTodo(todo);
        ApiResponse response = new ApiResponse("Todo added successfully");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping(path = "{todoId}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.deleteTodo(todoId);
        ApiResponse response = new ApiResponse("Todo deleted successfully");
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "{todoId}")
    public ResponseEntity<ApiResponse> updateTodo(
            @PathVariable("todoId") String todoId,
            @RequestBody Todo todo) {
        System.out.println("Received todo: " + todo);
        try {
            todoService.updateTodo(todoId, todo);
            ApiResponse response = new ApiResponse("Todo updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to update todo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
