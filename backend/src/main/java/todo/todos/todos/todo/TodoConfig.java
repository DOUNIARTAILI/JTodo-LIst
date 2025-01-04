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
                "Healthy Recipes",
                "Easy and nutritious meal ideas for a balanced diet.",
                false
            );
            Todo task2 = new Todo(
                "Summer Vacation",
                "A collection of ideas for your perfect summer getaway.",
                false // isCompleted
                );
            Todo task3 = new Todo(
                "Home Organization",
                "Practical tips for decluttering and organizing your living space.",
                false // isCompleted
            );

            repository.saveAll(List.of(task1,task2, task3));
        };
    }
}
