package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoAddedListener;
import me.oskarkraemer.EventListeners.TodoListAddedListener;
import me.oskarkraemer.Events.TodoAddedEvent;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import java.util.List;

public class UIController implements TodoAddedListener, TodoListAddedListener, SelectedTodoListGetter {
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
    public void todoAdded(TodoAddedEvent todoAddedEvent) {
        Todo addedTodo = todoAddedEvent.getAddedTodo();
        TodoList todoListAddedTo = todoAddedEvent.getTodoListAddedTo();

        System.out.println("Added todo in list: " + todoListAddedTo.getName());
        System.out.println(addedTodo.getDescription());
        System.out.println(addedTodo.getDue());
        System.out.println(addedTodo.getTimeBudget());

        for (TodoList todoList : this.todoLists) {
            if (todoList.getName().equals(todoListAddedTo.getName())) {
                todoList.addTodo(addedTodo);
            }
        }

        this.mainUI.updateTab(todoListAddedTo.getName(), new TodoListUI(todoListAddedTo.getTodos()).jpListPanel);
    }

    @Override
    public void todoListAdded(TodoList todoListAdded) {
        this.todoLists.add(todoListAdded);
        this.mainUI.addTab(todoListAdded.getName(), new TodoListUI(todoListAdded.getTodos()).jpListPanel);
    }
}
