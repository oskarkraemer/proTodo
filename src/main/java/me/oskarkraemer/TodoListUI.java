package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListUI extends JFrame {
    public JPanel jpListPanel;

    private TodoList todoList;

    public TodoListUI(TodoList todos, TodoUpdatedListener todoUpdatedListener) {
        this.todoList = todos;

        List<TodoItemUI> todoItemUIS = new ArrayList<>();

        jpListPanel.setLayout(new GridLayout(50, 1));

        for(int i =  0; i < todoList.getTodos().size(); i++) {
            Todo todo = todoList.getTodos().get(i);
            TodoItemUI todoItemUI = new TodoItemUI(todo, this.todoList, todoUpdatedListener);

            todoItemUIS.add(todoItemUI);
            jpListPanel.add(todoItemUIS.get(i).getTodoItemPanel(), 0, i);
        }
    }
}
