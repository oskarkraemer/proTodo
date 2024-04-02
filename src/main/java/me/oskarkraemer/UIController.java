package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.EventListeners.TodoListAddedListener;
import me.oskarkraemer.Events.TodoUpdatedEvent;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class UIController implements TodoUpdatedListener, TodoListAddedListener, SelectedTodoListGetter {
    private final MainUI mainUI;
    private final List<TodoList> todoLists;

    public UIController(List<TodoList> todoLists, MainUI mainUI) {
        this.todoLists = todoLists;
        this.mainUI = mainUI;

        this.mainUI.initAddTodoModal(this, this, todoLists, this);
    }

    public TodoList getSelectedTodoList() {
        String selectedTabTitle = mainUI.getSelectedTabTitle();
        for(TodoList todoList : this.todoLists) {
            if(todoList.getName().equals(selectedTabTitle)) {
                return todoList;
            }
        }

        return null;
    }

    @Override
    public void todoUpdated(TodoUpdatedEvent todoUpdatedEvent) {
        Todo updatedTodo = todoUpdatedEvent.getUpdatedTodo();
        TodoList todoListUpdatedTo = todoUpdatedEvent.getTodoListUpdatedTo();

        System.out.println(todoUpdatedEvent.getTodoListUpdatedTo());
        System.out.println(updatedTodo.getDescription());
        System.out.println(updatedTodo.getDue());
        System.out.println(updatedTodo.getTimeBudget());

        for(int i = 0; i < todoListUpdatedTo.getTodos().size(); i++) {
            Todo todo = todoListUpdatedTo.getTodos().get(i);
            if(todo == todoUpdatedEvent.getOriginalTodo()) {
                todoListUpdatedTo.setTodo(i, updatedTodo);
            }
        }

        if(todoUpdatedEvent.getTodoUpdateState() == TodoUpdatedEvent.TODO_UPDATE_STATE.CREATED) {
            todoListUpdatedTo.addTodo(updatedTodo);
        }

        try {
            todoListUpdatedTo.saveToFile();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this.mainUI, "Error saving changes:\n" + exception.getMessage(),
                    "An error occurred!", JOptionPane.ERROR_MESSAGE);
        }

        this.mainUI.updateTab(todoListUpdatedTo.getName(), new TodoListUI(todoListUpdatedTo, this).jpListPanel);
    }

    @Override
    public void todoListAdded(TodoList todoListAdded) {
        this.todoLists.add(todoListAdded);
        this.mainUI.addTab(todoListAdded.getName(), new TodoListUI(todoListAdded, this).jpListPanel);
    }
}
