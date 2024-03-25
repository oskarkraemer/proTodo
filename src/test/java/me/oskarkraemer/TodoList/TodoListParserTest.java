package me.oskarkraemer.TodoList;

import me.oskarkraemer.Todo.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListParserTest {

    @Test
    @DisplayName("Parse a ToDo-List markdown string.")
    void parseMarkdown() {
        TodoList targetTodoList = new TodoList("ProjectX");

        Todo todo1 = new Todo.TodoBuilder("Read book")
                .timeBudget(1200)
                .due(LocalDateTime.parse("2025-03-25T20:05:30"))
                .build();

        Todo todo2 = new Todo.TodoBuilder("Touch grass")
                .build();

        Todo todo3 = new Todo.TodoBuilder("Exercise")
                .timeBudget(1500)
                .createdAt(LocalDateTime.parse("2024-01-01T12:10:21"))
                .completed(true)
                .build();

        Todo todo4 = new Todo.TodoBuilder("Cheeseburger")
                .completed(true)
                .build();

        targetTodoList.addTodo(todo1);
        targetTodoList.addTodo(todo2);
        targetTodoList.addTodo(todo3);
        targetTodoList.addTodo(todo4);

        TodoList parsedTodoList = TodoListParser.parseMarkdown("# ToDo: ProjectX\n" +
                "## Metadata\n" +
                "Author: xxxxxx\n" +
                "\n" +
                "## List\n" +
                "- [ ] Read book | 1200s | Due by: 2025-03-25T20:05:30\n" +
                "- [ ] Touch grass\n" +
                "- [x] Exercise | 1500s | Created at: 2024-01-01T12:10:21\n" +
                "- [x] Cheeseburger");

        assertEquals(targetTodoList.getName(), parsedTodoList.getName());

        List<Todo> targetTodos = targetTodoList.getTodos();
        List<Todo> parsedTodos = parsedTodoList.getTodos();
        assertEquals(targetTodos.size(), parsedTodos.size());

        for(int i = 0; i < targetTodos.size(); i++) {
            assertEquals(targetTodos.get(i), parsedTodos.get(i));
        }
    }
}