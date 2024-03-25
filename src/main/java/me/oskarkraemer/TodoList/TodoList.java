package me.oskarkraemer.TodoList;

import me.oskarkraemer.Todo.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private String name;
    private String markdownFile;
    private final List<Todo> todos;

    public TodoList(String name) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be empty.");

        this.name = name;
        this.todos = new ArrayList<>();
    }

    public TodoList(String name, String markdownFile) {
        this(name);

        if(markdownFile == null || markdownFile.isEmpty()) throw new IllegalArgumentException("A link to a markdown file must be provided.");
        this.markdownFile = markdownFile;
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(String.format("List name: %s\nMarkdown file: %s\n\n[Todos]\n", this.name, this.markdownFile));

        for(Todo todo: this.todos) {
            str.append(todo.toString()).append("\n");
        }

        return str.toString();
    }
}
