package me.oskarkraemer;

import javax.swing.*;
import java.awt.*;

public class TodoListUI extends JFrame {
    public JPanel jpListPanel;

    public TodoListUI() {
        TodoItemUI todoItemUI =  new TodoItemUI();
        TodoItemUI todoItemUI2 =  new TodoItemUI();
        TodoItemUI todoItemUI3 =  new TodoItemUI();

        jpListPanel.setLayout(new GridLayout(50, 1));

        jpListPanel.add(todoItemUI.getTodoItemPanel(), 0, 0);
        jpListPanel.add(todoItemUI2.getTodoItemPanel(), 1, 0);
        jpListPanel.add(todoItemUI3.getTodoItemPanel(), 2, 0);
    }
}
