package me.oskarkraemer.Events;

import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

public class TodoAddedEvent {
    private Todo addedTodo;
    private TodoList todoListAddedTo;

    public TodoAddedEvent(Todo addedTodo, TodoList todoListAddedTo) {
        this.addedTodo = addedTodo;
        this.todoListAddedTo = todoListAddedTo;
    }


    public Todo getAddedTodo() {
        return addedTodo;
    }

    public void setAddedTodo(Todo addedTodo) {
        this.addedTodo = addedTodo;
    }

    public TodoList getTodoListAddedTo() {
        return todoListAddedTo;
    }

    public void setTodoListAddedTo(TodoList todoListAddedTo) {
        this.todoListAddedTo = todoListAddedTo;
    }
}
