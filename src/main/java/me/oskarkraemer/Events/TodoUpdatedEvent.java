package me.oskarkraemer.Events;

import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

public class TodoUpdatedEvent {
    private Todo originalTodo;
    private Todo updatedTodo;
    private TodoList todoListUpdatedTo;
    private UPDATE_STATE todoUpdateState;

    public TodoUpdatedEvent(Todo originalTodo, Todo updatedTodo, TodoList todoListUpdatedTo, UPDATE_STATE todoUpdateState) {
        this.originalTodo = originalTodo;
        this.updatedTodo = updatedTodo;
        this.todoListUpdatedTo = todoListUpdatedTo;
        this.todoUpdateState = todoUpdateState;
    }


    public Todo getOriginalTodo() {
        return this.originalTodo;
    }
    public Todo getUpdatedTodo() {
        return updatedTodo;
    }

    public void setAddedTodo(Todo updatedTodo) {
        this.updatedTodo = updatedTodo;
    }

    public TodoList getTodoListUpdatedTo() {
        return todoListUpdatedTo;
    }

    public UPDATE_STATE getTodoUpdateState() {
        return this.todoUpdateState;
    }

    public void setTodoListAddedTo(TodoList todoListUpdatedTo) {
        this.todoListUpdatedTo = todoListUpdatedTo;
    }
}