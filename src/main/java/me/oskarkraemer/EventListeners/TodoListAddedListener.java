package me.oskarkraemer.EventListeners;

import me.oskarkraemer.TodoList.TodoList;

import java.util.EventListener;

public interface TodoListAddedListener extends EventListener {
    void todoListAdded(TodoList todoListAdded);
}
