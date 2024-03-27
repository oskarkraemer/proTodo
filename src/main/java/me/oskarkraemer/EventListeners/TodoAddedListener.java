package me.oskarkraemer.EventListeners;

import me.oskarkraemer.Todo.Todo;

import java.util.EventListener;

public interface TodoAddedListener extends EventListener {
    void todoAdded(Todo addedTodo);
}
