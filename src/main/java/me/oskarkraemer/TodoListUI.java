package me.oskarkraemer;

import me.oskarkraemer.Todo.Todo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListUI extends JFrame {
    public JPanel jpListPanel;

    private List<Todo> todos;

    public TodoListUI(List<Todo> todos) {
        this.todos = todos;

        List<TodoItemUI> todoItemUIS = new ArrayList<>();

        jpListPanel.setLayout(new GridLayout(50, 1));

        for(int i =  0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            TodoItemUI todoItemUI = new TodoItemUI(todo);

            todoItemUIS.add(todoItemUI);
            jpListPanel.add(todoItemUIS.get(i).getTodoItemPanel(), 0, i);
        }
    }
}
