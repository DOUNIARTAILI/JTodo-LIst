package todo.todos.todos.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TodoConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoRepository repository) {
        return args -> {
            Todo task1 = new Todo(
                "title heeere",
                "secription also heere",
                false
            );
            Todo task2 = new Todo(
                "Build Todo App",
                "Implement a full-stack application using Next.js and Spring Boot",
                false // isCompleted
                );
            Todo task3 = new Todo(
                "Learn Spring Boot",
                "Understand basics of Spring Boot and REST API development",
                false // isCompleted
            );

            repository.saveAll(List.of(task1,task2, task3));
        };
    }
}
