package me.oskarkraemer;

import javax.swing.*;

public class TodoItemUI extends JFrame {
    private JPanel jpTodoItem;

    public JCheckBox jcbTodoCheckbox;
    public JButton jbTodoStartTimer;
    public JLabel jlTodoDue;
    public JLabel jlTodoDueWords;
    public JLabel jlTodoCreated;
    public JLabel jlTodoTimeBudget;

    public JPanel getTodoItemPanel() {
        return this.jpTodoItem;
    }
}
