package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoAddedListener;
import me.oskarkraemer.Todo.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MainUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel jpMainPanel;
    private JButton openListButton;
    private JButton aboutButton;
    private JList jlTodoLists;
    private JButton newListButton;
    private JButton newTodoButton;

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
            }
        });

        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        tabHeader.add(new JLabel(title));
        tabHeader.add(closeButton);
        tabbedPane1.addTab(null, panel);
        tabbedPane1.setTabComponentAt(tabbedPane1.getTabCount() - 1, tabHeader);

        this.tabsPanels.put(title, panel);
    }

    public void updateTab(String title, JPanel newContent) {
        JPanel tabPanel = this.tabsPanels.get(title);
        if(tabPanel == null) throw new IllegalArgumentException("Tab of title '" + title + "' could not be found.");

        tabPanel.removeAll();
        tabPanel.add(newContent, BorderLayout.CENTER);
    }

    private void createUIComponents() {
        tabbedPane1 = new JTabbedPane();
    }

    public void setTodoAddedListener(TodoAddedListener todoAddedListener) {
        this.newTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTodoUI(todoAddedListener);
            }
        });
    }
}
