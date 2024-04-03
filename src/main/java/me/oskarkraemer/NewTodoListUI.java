package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoListUpdatedListener;
import me.oskarkraemer.Events.TodoListUpdatedEvent;
import me.oskarkraemer.Events.UPDATE_STATE;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

public class NewTodoListUI extends JDialog {
    private TodoListUpdatedListener todoListUpdatedListener;
    private JPanel jpMainPanel;
    private JButton buttonADD;
    private JButton buttonCancel;
    private JLabel jlErrorMessage;
    private JButton jbSelectFile;
    private JTextField jtfIntervalDays;
    private JTextField jtfListName;

    private File fileToSave;

    public NewTodoListUI(TodoListUpdatedListener todoListUpdatedListener) {
        this.todoListUpdatedListener = todoListUpdatedListener;

        setContentPane(jpMainPanel);
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

        jbSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Markdown file", "md"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                int result = fileChooser.showSaveDialog(jpMainPanel);
                if(result == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    jbSelectFile.setText(fileToSave.getAbsolutePath());
                }
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
        jpMainPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    private void tryAdd() {
        TodoList newTodoList = new TodoList(jtfListName.getText(), fileToSave.getAbsolutePath());
        try {
            todoListUpdatedListener.todoListUpdated(new TodoListUpdatedEvent(null, newTodoList, UPDATE_STATE.CREATED));
            dispose();
        } catch (RuntimeException ignored) {}
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
