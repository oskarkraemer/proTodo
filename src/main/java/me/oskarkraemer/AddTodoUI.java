package me.oskarkraemer;

import com.raven.datechooser.DateChooser;
import me.oskarkraemer.EventListeners.TodoAddedListener;
import me.oskarkraemer.Todo.Todo;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class AddTodoUI extends JDialog {
    private final TodoAddedListener todoAddedListener;
    private final Pattern TWELVE_HOUR_PATTERN = Pattern.compile("^(1[0-2]|0?[1-9]):[0-5][0-9]$");
    private final Pattern TWENTYFOUR_HOUR_PATTERN = Pattern.compile("^([01][0-9]|2[0-3]):([0-5][0-9])$");

    private JPanel contentPane;
    private JButton buttonADD;
    private JButton buttonCancel;
    private JTextField jtfDescription;
    private JTextField jtfDue;
    private JTextField jtfDueTime;
    private JComboBox jcbAMPM;
    private JTextField jtfTimeBudget;
    private JLabel jlErrorMessage;
    private final DateChooser dcDateChooser;

    private enum ADD_TODO_STATUS {
        OK,
        MISSING_REQUIRED_FIELDS,
        DUE_BEFORE_NOW,
        WRONG_TIME_FORMAT
    }


    public AddTodoUI(TodoAddedListener todoAddedListener) {
        this.todoAddedListener = todoAddedListener;

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
                ADD_TODO_STATUS addTodoStatus = tryAdd();
                if(addTodoStatus != ADD_TODO_STATUS.OK) jlErrorMessage.setVisible(true);

                switch (addTodoStatus) {
                    case WRONG_TIME_FORMAT -> jlErrorMessage.setText("Time is formatted incorrectly.");
                    case DUE_BEFORE_NOW ->  jlErrorMessage.setText("The due date has to lie after the current date.");
                    case MISSING_REQUIRED_FIELDS -> jlErrorMessage.setText("Please fill out every required field.");
                }
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

    private ADD_TODO_STATUS tryAdd() {
        if(this.jtfDescription.getText().trim().isEmpty()) return ADD_TODO_STATUS.MISSING_REQUIRED_FIELDS;
        if(!this.jtfDueTime.getText().isEmpty() && !TWELVE_HOUR_PATTERN.matcher(this.jtfDueTime.getText()).matches()) return ADD_TODO_STATUS.WRONG_TIME_FORMAT;

        LocalDateTime dueDatetime = null;
        if(this.dcDateChooser.isDateSelected()) {
            LocalDateTime dueDate;
            dueDate = this.dcDateChooser.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            int hour = 0, minute = 0;

            if(!this.jtfDueTime.getText().isEmpty()) {
                String[] timeSplit = this.jtfDueTime.getText().split(":");
                hour = Integer.parseInt(timeSplit[0]);
                minute = Integer.parseInt(timeSplit[1]);
            }

            dueDatetime = LocalDateTime.of(dueDate.getYear(), dueDate.getMonth(), dueDate.getDayOfMonth(), hour, minute);

            if(!dueDate.isAfter(LocalDateTime.now())) return ADD_TODO_STATUS.DUE_BEFORE_NOW;
        }

        int timeBudget = 0;
        String timeBudgetStr = this.jtfTimeBudget.getText();
        if(!timeBudgetStr.isEmpty()) {
            if(!timeBudgetStr.matches("-?\\d+")) return ADD_TODO_STATUS.WRONG_TIME_FORMAT;

            timeBudget = Integer.parseInt(timeBudgetStr);
        }

        Todo addedTodo = new Todo.TodoBuilder(this.jtfDescription.getText())
                .due(dueDatetime)
                .timeBudget(timeBudget)
                .build();

        this.todoAddedListener.todoAdded(addedTodo);
        dispose();

        return ADD_TODO_STATUS.OK;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
