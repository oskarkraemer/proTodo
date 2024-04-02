package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.Events.TodoUpdatedEvent;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TodoItemUI extends JFrame {
    private JPanel jpTodoItem;

    private JCheckBox jcbTodoCheckbox;
    private JButton jbTodoStartTimer;
    private JLabel jlTodoDue;
    private JLabel jlTodoDueWords;
    private JLabel jlTodoCreated;
    private JLabel jlTodoTimeBudget;
    private JButton jbEditTodo;

    public TodoItemUI(Todo todo, TodoList belongingTodoList, TodoUpdatedListener todoUpdatedListener) {
        this.jbEditTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTodoUI(todoUpdatedListener, null, belongingTodoList, todo);
            }
        });

        this.updateUI(todo);
        jcbTodoCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Todo newTodo = new Todo.TodoBuilder(todo.getDescription())
                        .completed(!todo.isCompleted())
                        .due(todo.getDue())
                        .createdAt(todo.getCreatedAt())
                        .timeBudget(todo.getTimeBudget())
                        .build();
                todoUpdatedListener.todoUpdated(new TodoUpdatedEvent(todo, newTodo, belongingTodoList, TodoUpdatedEvent.TODO_UPDATE_STATE.CHANGED));
            }
        });
    }

    public void updateUI(Todo todo) {
        this.jcbTodoCheckbox.setText(todo.getDescription());
        this.jcbTodoCheckbox.setSelected(todo.isCompleted());

        this.setDue(todo.getDue());
        this.setCreated(todo.getCreatedAt());
        this.setTimeBudget(todo.getTimeBudget());
    }

    private void setDue(LocalDateTime due) {
        if(due == null) {
            this.jlTodoDue.setText("");
            this.jlTodoDueWords.setText("");

            return;
        }

        this.jlTodoDue.setText("Due: " + due.format(DateTimeFormatter.ofPattern("MM.dd.yyyy HH:mm")));

        LocalDateTime now = LocalDateTime.now();
        long monthsUntil = now.until(due, ChronoUnit.MONTHS);
        long weeksUntil = now.until(due, ChronoUnit.WEEKS);
        long daysUntil = now.until(due, ChronoUnit.DAYS);
        long hoursUntil = now.until(due, ChronoUnit.HOURS);
        long minutesUntil = now.until(due, ChronoUnit.MINUTES);


        if(monthsUntil > 0) {
            this.jlTodoDueWords.setText("(" + monthsUntil + " months)");
        }
        else if(weeksUntil > 0) {
            this.jlTodoDueWords.setText("(" + weeksUntil + " weeks)");
        }
        else if(daysUntil > 0) {
            this.jlTodoDueWords.setText("(" + daysUntil + " days)");
        }
        else if(hoursUntil > 0) {
            this.jlTodoDueWords.setText("(" + hoursUntil + " hours)");
        }
        else if(minutesUntil > 0) {
            this.jlTodoDueWords.setText("(" + minutesUntil + " minutes)");
        }
    }

    private void setCreated(LocalDateTime created) {
        if(created == null) {
            this.jlTodoCreated.setText("");

            return;
        }

        this.jlTodoCreated.setText("Created: " + created.format(DateTimeFormatter.ofPattern("MM.dd.yyyy")));
    }

    private void setTimeBudget(int timeBudget) {
        if(timeBudget == 0) {
            this.jlTodoTimeBudget.setText("");
            this.jbTodoStartTimer.setEnabled(false);

            return;
        }

        this.jlTodoTimeBudget.setText(timeBudget + "min");
    }

    public JPanel getTodoItemPanel() {
        return this.jpTodoItem;
    }
}
