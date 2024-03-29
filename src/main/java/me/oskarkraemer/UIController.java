package me.oskarkraemer;

import me.oskarkraemer.EventListeners.TodoAddedListener;
import me.oskarkraemer.Events.TodoAddedEvent;
import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

import java.util.List;

public class UIController implements TodoAddedListener, SelectedTodoListGetter {
    private final MainUI mainUI;
    private final List<TodoList> todoLists;

    public UIController(List<TodoList> todoLists, MainUI mainUI) {
        this.todoLists = todoLists;
        this.mainUI = mainUI;

        this.mainUI.initAddTodoModal(this, todoLists, this);

        this.mainUI.addTab("ProjectX", new TodoListUI(this.todoLists.get(0).getTodos()).jpListPanel);

        TodoList tl = this.todoLists.get(0);
        tl.addTodo(new Todo.TodoBuilder("Hello").build());
        this.todoLists.set(0, tl);

        this.mainUI.updateTab("ProjectX", new TodoListUI(this.todoLists.get(0).getTodos()).jpListPanel);
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

        for(int i = 0; i < this.todoLists.size(); i++) {
            TodoList todoList = this.todoLists.get(i);

            if(todoList.getName().equals(todoListAddedTo.getName())) {
                todoList.addTodo(addedTodo);
            }
        }

        this.mainUI.updateTab(todoListAddedTo.getName(), new TodoListUI(todoListAddedTo.getTodos()).jpListPanel);
    }
}
