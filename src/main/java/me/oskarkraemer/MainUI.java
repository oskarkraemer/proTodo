package me.oskarkraemer;

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
        setContentPane(jpMainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTab(String title, String content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(content);
        panel.add(label, BorderLayout.CENTER);

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

        addTab("Test", "Content");
    }
}
