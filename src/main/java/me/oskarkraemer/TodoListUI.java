package me.oskarkraemer;

import javax.swing.*;
import java.awt.*;

public class TodoListUI extends JFrame {
    public JPanel jpListPanel;

    public TodoListUI() {
        TodoItemUI todoItemUI =  new TodoItemUI();

        jpListPanel.setLayout(new GridLayout(50, 1));
        jpListPanel.add(todoItemUI.getTodoItemPanel(), 0, 0);

    }
}
