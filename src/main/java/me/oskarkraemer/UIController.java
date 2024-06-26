package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoUpdatedListener;
import me.oskarkraemer.EventListeners.TodoListUpdatedListener;
import me.oskarkraemer.Events.TodoListUpdatedEvent;
import me.oskarkraemer.Events.TodoUpdatedEvent;
import me.oskarkraemer.Events.UPDATE_STATE;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class UIController implements TodoUpdatedListener, TodoListUpdatedListener, SelectedTodoListGetter {
    private final MainUI mainUI;
    private final List<TodoList> todoLists;

    public UIController(List<TodoList> todoLists, MainUI mainUI) {
        this.todoLists = todoLists;
        this.mainUI = mainUI;

        this.mainUI.initUICallbacks(this, this, todoLists, this);
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

        if(todoUpdatedEvent.getTodoUpdateState() == UPDATE_STATE.CREATED) {
            todoListUpdatedTo.addTodo(updatedTodo);
        }

        trySaveTodoList(todoListUpdatedTo);

        this.mainUI.updateTab(todoListUpdatedTo.getName(), new TodoListUI(todoListUpdatedTo, this).getTodoListPanel());
    }

    @Override
    public void todoListUpdated(TodoListUpdatedEvent todoListUpdatedEvent) {
        switch (todoListUpdatedEvent.getTodoListUpdateState()) {
            case CREATED -> {
                TodoList addedTodoList = todoListUpdatedEvent.getUpdatedTodoList();

                if (!addedTodoList.getMarkdownFile().isEmpty()) {
                    if (!trySaveTodoList(addedTodoList)) throw new RuntimeException("ToDo list could not be created");
                }

                this.todoLists.add(addedTodoList);
                this.mainUI.addTab(addedTodoList.getName(), new TodoListUI(addedTodoList, this).getTodoListPanel(), this, this);
            }

            case DELETED -> {
                this.todoLists.remove(todoListUpdatedEvent.getOriginalTodoList());
            }
        }
    }

    private boolean trySaveTodoList(TodoList todoList) {
        try {
            todoList.saveToFile();
            return true;
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this.mainUI, "Error saving changes:\n" + exception.getMessage(),
                    "An error occurred!", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }
}
