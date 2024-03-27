package me.oskarkraemer;

import com.raven.datechooser.DateChooser;

import javax.swing.*;
import java.awt.event.*;

public class AddTodoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonADD;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField jtfDue;
    private JTextField jtfDueTime;
    private JComboBox comboBox1;
    private JTextField textField2;
    private DateChooser dcDateChooser;

    public AddTodoUI() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Add Todo");

        getRootPane().setDefaultButton(buttonADD);

        dcDateChooser = new DateChooser();
        dcDateChooser.setTextField(jtfDue);
        dcDateChooser.clearDate();

        buttonADD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onADD();
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

    private void onADD() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
