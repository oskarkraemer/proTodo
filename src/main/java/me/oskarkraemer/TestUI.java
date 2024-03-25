package me.oskarkraemer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestUI extends JFrame{
    private JPanel jpMainPanel;

    private JTextField jtfLoginUsername;
    private JPasswordField jtfLoginPassword;
    private JButton jbLogin;

    private JTextField jtfRegisterEmail;
    private JTextField jtfRegisterUsername;
    private JPasswordField jtfRegisterPassword;
    private JButton jbRegister;

    public TestUI() {
        setTitle("TestUI");
        setContentPane(jpMainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfLoginUsername.getText().isEmpty()) return;
                if(jtfLoginPassword.getPassword().length == 0) return;

                JOptionPane.showMessageDialog(TestUI.this, String.format("E-Mail: %s, Password: %s", jtfLoginUsername.getText(), new String(jtfLoginPassword.getPassword())));
            }
        });

        jbRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfRegisterEmail.getText().isEmpty()) return;
                if(jtfRegisterUsername.getText().isEmpty()) return;
                if(jtfRegisterPassword.getPassword().length == 0) return;

                JOptionPane.showMessageDialog(TestUI.this, String.format("E-Mail: %s, Username: %s, Password: %s", jtfRegisterEmail.getText(), jtfRegisterUsername.getText(), new String(jtfRegisterPassword.getPassword())));
            }
        });
    }
}
