package me.oskarkraemer.TodoList;

import me.oskarkraemer.Todo.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public String getName() {
        return name;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodo(int index, Todo newTodo) {
        this.todos.set(index, newTodo);
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }

    public String getMarkdownFile() {
        return this.markdownFile;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(String.format("# ToDo: %s\n## List\n", this.name));

        for(int i = 0; i < this.todos.size(); i++) {
            str.append(this.todos.get(i).toString());

            if(i < this.todos.size() - 1) {
                str.append("\n");
            }
        }

        return str.toString();
    }

    public static TodoList readFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        byte[] bytes = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String linesString = String.join("\n", allLines);

        TodoList parsedTodoList = TodoListParser.parseMarkdown(linesString);
        parsedTodoList.markdownFile = filePath;

        return parsedTodoList;
    }

    public void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter(this.markdownFile, StandardCharsets.UTF_8);
        writer.print(this);
        writer.close();
    }
}
