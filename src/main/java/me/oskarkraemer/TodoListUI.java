package me.oskarkraemer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TodoListUI extends JFrame {
    private JList list1;
    public JPanel panel1;

    public TodoListUI() {
        this.list1.setFixedCellHeight(50);
        this.list1.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
