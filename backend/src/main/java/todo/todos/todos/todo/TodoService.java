package todo.todos.todos.todo;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Sort;

@Service
@Validated
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
    public void addNewTodo(Todo todo){
        todoRepository.save(todo);
    }
    public void deleteTodo(String todoId) {
        boolean exists = todoRepository.existsById(todoId);
        if (!exists) {
            throw new IllegalStateException("todo with id " + todoId + " does not exists!");
        }
        todoRepository.deleteById(todoId);
    }
    @Transactional
    public void updateTodo(String todoId, Todo todoUpdated) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalStateException("todo with id " + todoId + " does not exists!"));
        if (todoUpdated.getTitle() != null && todoUpdated.getTitle().length() > 0 && !Objects.equals(todo.getTitle(), todoUpdated.getTitle())) {
            todo.setTitle(todoUpdated.getTitle());
        }
        if (todoUpdated.getDescription() != null && todoUpdated.getDescription().length() > 0 && !Objects.equals(todo.getDescription(), todoUpdated.getDescription())) {
            todo.setDescription(todoUpdated.getDescription());
        }
        if (!Objects.equals(todo.isCompleted(), todoUpdated.isCompleted())) {
            todo.setCompleted(todoUpdated.isCompleted());
        }
    }
}
