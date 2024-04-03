package me.oskarkraemer.EventListeners;

import me.oskarkraemer.Events.TodoListUpdatedEvent;
import me.oskarkraemer.TodoList.TodoList;

import java.util.EventListener;

public interface TodoListUpdatedListener extends EventListener {
    void todoListUpdated(TodoListUpdatedEvent todoListUpdatedEvent);
}
