package me.oskarkraemer.EventListeners;

import me.oskarkraemer.Events.TodoUpdatedEvent;

import java.util.EventListener;

public interface TodoUpdatedListener extends EventListener {
    void todoUpdated(TodoUpdatedEvent todoUpdatedEvent);
}
