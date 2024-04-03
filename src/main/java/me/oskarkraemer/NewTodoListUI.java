package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoListUpdatedListener;
import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
public class NewTodoListUI extends JDialog {
    private TodoListUpdatedListener todoListUpdatedListener;
    private JPanel contentPane;
    private JButton buttonADD;
    private JButton buttonCancel;
    private JLabel jlErrorMessage;
    private JButton jbSelectFile;
    private JTextField jtfIntervalDays;

    public NewTodoListUI(TodoListUpdatedListener todoListUpdatedListener) {
        this.todoListUpdatedListener = todoListUpdatedListener;

        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("New Todo list");

        getRootPane().setDefaultButton(buttonADD);

        buttonADD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tryAdd();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    private void tryAdd() {

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
