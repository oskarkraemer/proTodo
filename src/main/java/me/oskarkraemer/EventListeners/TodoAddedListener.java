package me.oskarkraemer.EventListeners;

import me.oskarkraemer.Events.TodoAddedEvent;

import java.util.EventListener;

public interface TodoAddedListener extends EventListener {
    void todoAdded(TodoAddedEvent todoAddedEvent);
}
