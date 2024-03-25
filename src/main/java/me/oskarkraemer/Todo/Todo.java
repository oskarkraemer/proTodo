package me.oskarkraemer.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {
    private boolean completed = false;
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String createdAtString = this.createdAt != null ? this.createdAt.format(formatter) : "";
        String dueString = this.due != null ? this.due.format(formatter) : "";

        String str = String.format("- [%s] %s", this.completed ? "x": " ", this.description);

        if(timeBudget > 0) {
            str += String.format(" | %ds", this.timeBudget);
        }

        if(!dueString.isEmpty()) {
            str += String.format(" | Due by: %s", dueString);
        }

        if(!createdAtString.isEmpty()) {
            str += String.format(" | Created at: %s", createdAtString);
        }

        return str;
    }

    public static class TodoBuilder {
        private boolean completed = false;
        private final String description;
        private LocalDateTime createdAt;
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
        public TodoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt != null ? createdAt : this.createdAt;
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
            if(this.description == null || this.description.isEmpty()) throw new IllegalArgumentException("Description must not be empty or null.");
            if(this.due != null && !this.due.isAfter(this.createdAt)) throw new IllegalArgumentException("Due must lie after createdAt.");
            if(this.timeBudget < 0) throw new IllegalArgumentException("TimeBudget must not be negative.");
        }
    }
}

