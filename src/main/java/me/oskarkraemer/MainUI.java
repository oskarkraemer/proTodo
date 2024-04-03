package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.EventListeners.TodoListUpdatedListener;
import me.oskarkraemer.Events.TodoListUpdatedEvent;
import me.oskarkraemer.Events.UPDATE_STATE;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MainUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel jpMainPanel;
    private JButton jbOpenList;
    private JButton jbAbout;
    private JList jlTodoLists;
    private JButton jbNewList;
    private JButton jbNewTodo;

    private final HashMap<String, JPanel> tabsPanels;

    public MainUI(){
        setTitle("proTodo");

        ImageIcon img = new ImageIcon("pTDIcon.png");
        setIconImage(img.getImage());

        setContentPane(jpMainPanel);

        jlTodoLists.setFixedCellHeight(30);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        this.tabsPanels = new HashMap<>();
    }

    public void addTab(String title, JPanel content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(content, BorderLayout.CENTER);

        JButton closeButton = new JButton("X");
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tabbedPane1.indexOfComponent(panel);
                if (index != -1) {
                    tabbedPane1.removeTabAt(index);
                }

                jbNewTodo.setEnabled(tabbedPane1.getTabCount() > 0);
            }
        });

        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        tabHeader.add(new JLabel(title));
        tabHeader.add(closeButton);
        tabbedPane1.addTab(title, panel);
        tabbedPane1.setTabComponentAt(tabbedPane1.getTabCount() - 1, tabHeader);

        this.jbNewTodo.setEnabled(tabbedPane1.getTabCount() > 0);

        this.tabsPanels.put(title, panel);
    }

    public void updateTab(String title, JPanel newContent) {
        JPanel tabPanel = this.tabsPanels.get(title);
        if(tabPanel == null) throw new IllegalArgumentException("Tab of title '" + title + "' could not be found.");

        tabPanel.removeAll();
        tabPanel.add(newContent, BorderLayout.CENTER);

        tabPanel.validate();
        tabPanel.repaint();

        tabbedPane1.revalidate();
        tabbedPane1.repaint();
    }

    public String getSelectedTabTitle() {
        return tabbedPane1.getTitleAt(tabbedPane1.getSelectedIndex());
    }

    private void createUIComponents() {
        tabbedPane1 = new JTabbedPane();
    }

    public void initAddTodoModal(TodoUpdatedListener todoUpdatedListener, TodoListUpdatedListener todoListUpdatedListener, List<TodoList> todoLists, SelectedTodoListGetter selectedTodoListGetter) {
        this.jbNewList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewTodoListUI(todoListUpdatedListener);
            }
        });

        this.jbOpenList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Markdown file", "md"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                int result = fileChooser.showOpenDialog(jpMainPanel);
                if(result == JFileChooser.APPROVE_OPTION) {
                    try {
                        todoListUpdatedListener.todoListUpdated(new TodoListUpdatedEvent(null, TodoList.readFromFile(fileChooser.getSelectedFile().getAbsolutePath()), UPDATE_STATE.CREATED));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(jpMainPanel, "Error reading ToDo list file:\n" + exception.getMessage(),
                                "An error occurred!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.jbNewTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTodoUI(todoUpdatedListener, todoLists, selectedTodoListGetter.getSelectedTodoList());
            }
        });
    }
}
