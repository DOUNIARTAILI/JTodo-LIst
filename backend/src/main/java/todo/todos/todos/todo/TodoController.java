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
@RequestMapping(path = "/api/todos", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3001")
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
    public ResponseEntity<String> addTodo(@RequestBody Todo todo) {
        todoService.addNewTodo(todo);
        return ResponseEntity.ok("Todo added successfully!");
    }
    @DeleteMapping(path= "{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!");
    }
    @PutMapping(path = "{todoId}")
    public ResponseEntity<String> updateTodo(
        @PathVariable("todoId") String todoId,
        @RequestBody Todo todo
    ) {
        System.out.println(todo);
        try {
            todoService.updateTodo(todoId, todo);
            return ResponseEntity.ok("Todo updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update todo: " + e.getMessage());
        }
    }
}
