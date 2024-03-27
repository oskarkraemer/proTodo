package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoAddedListener;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import java.util.List;

public class UIController implements TodoAddedListener {
    private final MainUI mainUI;
    private final List<TodoList> todoLists;

    public UIController(List<TodoList> todoLists, MainUI mainUI) {
        this.todoLists = todoLists;
        this.mainUI = mainUI;

        this.mainUI.setTodoAddedListener(this);

        this.mainUI.addTab("TestList", new TodoListUI(this.todoLists.get(0).getTodos()).jpListPanel);

        TodoList tl = this.todoLists.get(0);
        tl.addTodo(new Todo.TodoBuilder("Hello").build());
        this.todoLists.set(0, tl);

        this.mainUI.updateTab("TestList", new TodoListUI(this.todoLists.get(0).getTodos()).jpListPanel);
    }

    @Override
    public void todoAdded(Todo addedTodo) {
        System.out.println(addedTodo.getDescription());
        System.out.println(addedTodo.getDue());
        System.out.println(addedTodo.getTimeBudget());
    }
}
