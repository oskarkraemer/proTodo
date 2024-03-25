package me.oskarkraemer;

import com.formdev.flatlaf.FlatDarculaLaf;
import me.oskarkraemer.Todo.Todo;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        TodoList todoList = new TodoList("MyTodoList", "mylist.md");

        Todo todo = new Todo.TodoBuilder("Read book")
                .due(LocalDateTime.now().plusDays(1))
                .timeBudget(20 * 60).build();

        todo.setCompleted(true);

        todoList.addTodo(todo);

        System.out.println(todoList);
    }
}