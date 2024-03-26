package me.oskarkraemer;

import me.oskarkraemer.TodoList.TodoList;
import me.oskarkraemer.TodoList.TodoListParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel jpMainPanel;
    private JButton openListButton;
    private JButton aboutButton;
    private JList list1;

    public MainUI() {
        setTitle("proTodo");

        ImageIcon img = new ImageIcon("pTDIcon.png");
        setIconImage(img.getImage());

        setContentPane(jpMainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTab(String title, JPanel content) {
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
            }
        });

        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        tabHeader.add(new JLabel(title));
        tabHeader.add(closeButton);
        tabbedPane1.addTab(null, panel);
        tabbedPane1.setTabComponentAt(tabbedPane1.getTabCount() - 1, tabHeader);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tabbedPane1 = new JTabbedPane();

        TodoList todoList = TodoListParser.parseMarkdown("# ToDo: ProjectX\n" +
                "## Metadata\n" +
                "Author: mmmmm\n" +
                "\n" +
                "## List\n" +
                "- [ ] Read book | 1200s | Due by: 2025-03-25T20:05:30\n" +
                "- [ ] Touch grass\n" +
                "- [x] Exercise | 1500s | Created at: 2024-01-01T12:10:21\n" +
                "- [x] Cheeseburger");

        addTab("Test", new TodoListUI(todoList.getTodos()).jpListPanel);
    }
}
