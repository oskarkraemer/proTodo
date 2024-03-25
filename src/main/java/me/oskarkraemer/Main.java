package me.oskarkraemer;

import com.formdev.flatlaf.FlatDarculaLaf;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.Todo.TodoParser;
import me.oskarkraemer.TodoList.TodoList;
import me.oskarkraemer.TodoList.TodoListParser;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        TodoList todoList = TodoListParser.parseMarkdown("# ToDo: ProjectX\n" +
                "## Metadata\n" +
                "Author: mmmmm\n" +
                "\n" +
                "## List\n" +
                "- [ ] Read book | 1200s | Due by: 2025-03-25T20:05:30\n" +
                "- [ ] Touch grass\n" +
                "- [x] Exercise | 1500s | Created at: 2024-01-01T12:10:21\n" +
                "- [x] Cheeseburger");

        System.out.println(todoList);
    }
}