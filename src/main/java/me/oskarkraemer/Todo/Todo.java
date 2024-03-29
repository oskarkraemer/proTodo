package me.oskarkraemer.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Todo {
    private boolean completed;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime due;
    private int timeBudget;

    public Todo(TodoBuilder builder) {
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.due = builder.due;
        this.timeBudget = builder.timeBudget;
        this.completed = builder.completed;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public int getTimeBudget() {
        return timeBudget;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String createdAtString = this.createdAt != null ? this.createdAt.format(formatter) : "";
        String dueString = this.due != null ? this.due.format(formatter) : "";

        String str = String.format("- [%s] %s", this.completed ? "x": " ", this.description);

        if(timeBudget > 0) {
            str += String.format(" | %dm", this.timeBudget);
        }

        if(!dueString.isEmpty()) {
            str += String.format(" | Due by: %s", dueString);
        }

        if(!createdAtString.isEmpty()) {
            str += String.format(" | Created at: %s", createdAtString);
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same object
        if (!(obj instanceof Todo todo2)) return false; // Not a to-do object

        return this.isCompleted() == todo2.isCompleted() &&
                Objects.equals(this.getDue(), todo2.getDue()) &&
                this.getDescription().equals(todo2.getDescription()) &&
                this.getTimeBudget() == todo2.getTimeBudget();
    }


    public static class TodoBuilder {
        private boolean completed = false;
        private final String description;
        private LocalDateTime createdAt;
        private LocalDateTime due;
        private int timeBudget;

        public TodoBuilder(String description) {
            this.description = description;
        }
        public TodoBuilder due(LocalDateTime due) {
            this.due = due;
            return this;
        }
        public TodoBuilder timeBudget(int timeBudget) {
            this.timeBudget = timeBudget;
            return this;
        }
        public TodoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public TodoBuilder completed(boolean completed) {
            this.completed = completed;
            return this;
        }
        public Todo build() {
            Todo todo = new Todo(this);
            validate(todo);
            return todo;
        }
        private void validate(Todo todo) {
            if(todo.description == null || todo.description.isEmpty()) throw new IllegalArgumentException("Description must not be empty or null.");
            if(todo.due != null && todo.createdAt != null && !todo.due.isAfter(todo.createdAt)) throw new IllegalArgumentException("Due must lie after createdAt.");
            if(todo.timeBudget < 0) throw new IllegalArgumentException("TimeBudget must not be negative.");
        }
    }
}

