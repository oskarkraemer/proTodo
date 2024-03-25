package me.oskarkraemer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TodoListUI extends JFrame {
    private JList todoList;
    public JPanel jpListPanel;

    public TodoListUI() {
        this.todoList.setFixedCellHeight(50);
        this.todoList.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
