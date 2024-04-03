package me.oskarkraemer.Events;

import me.oskarkraemer.Todo.Todo;
import me.oskarkraemer.TodoList.TodoList;

public class TodoListUpdatedEvent {
    private final TodoList originalTodoList;
    private final TodoList updatedTodoList;
    private final UPDATE_STATE todoListUpdateState;

    public TodoListUpdatedEvent(TodoList originalTodoList, TodoList updatedTodoList, UPDATE_STATE todoListUpdateState) {
        this.originalTodoList = originalTodoList;
        this.updatedTodoList = updatedTodoList;
        this.todoListUpdateState = todoListUpdateState;
    }

    public UPDATE_STATE getTodoListUpdateState() {
        return todoListUpdateState;
    }

    public TodoList getUpdatedTodoList() {
        return updatedTodoList;
    }
}
