package me.oskarkraemer.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime due;
    private int timeBudget;

    public Todo(TodoBuilder builder) {
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.due = builder.due;
        this.timeBudget = builder.timeBudget;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String createdAtString = this.createdAt.format(formatter);
        String dueString = this.due.format(formatter);

        return String.format("%s [%ds] Due by: %s Created at: %s", this.description, this.timeBudget, dueString, createdAtString);
    }

    public static class TodoBuilder {
        private final String description;
        private final LocalDateTime createdAt;
        private LocalDateTime due;
        private int timeBudget;

        public TodoBuilder(String description) {
            this.description = description;
            this.createdAt = LocalDateTime.now();
        }
        public TodoBuilder due(LocalDateTime due) {
            this.due = due;
            return this;
        }
        public TodoBuilder timeBudget(int timeBudget) {
            this.timeBudget = timeBudget;
            return this;
        }
        public Todo build() {
            Todo todo = new Todo(this);
            validate(todo);
            return todo;
        }
        private void validate(Todo todo) {
            if(this.description == null || this.description.isEmpty()) throw new IllegalArgumentException("Description must not be empty or null.");
            if(this.due != null && !due.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("Due must lie in the future.");
            if(this.timeBudget < 0) throw new IllegalArgumentException("TimeBudget must not be negative.");
        }
    }
}

