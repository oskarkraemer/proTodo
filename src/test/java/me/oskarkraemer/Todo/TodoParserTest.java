package me.oskarkraemer.Todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TodoParserTest {
    @Test
    @DisplayName("Parse a ToDo markdown string.")
    void parseMarkdown() {
        // - [ ] Read book | 1200s | Due by: 2025-03-25T20:05:30
        Todo targetTodo1 = new Todo.TodoBuilder("Read book").timeBudget(1200).due(LocalDateTime.parse("2025-03-25T20:05:30")).build();
        Todo parsedTodo1 = TodoParser.parseMarkdown("- [ ] Read book | 1200s | Due by: 2025-03-25T20:05:30");

        assertEquals(targetTodo1, parsedTodo1);

        // - [ ] Read book
        Todo targetTodo2 = new Todo.TodoBuilder("Read book").build();
        Todo parsedTodo2 = TodoParser.parseMarkdown("- [ ] Read book");

        assertEquals(targetTodo2, parsedTodo2);

        // - [x] Read book
        Todo targetTodo3 = new Todo.TodoBuilder("Read book").completed(true).build();
        Todo parsedTodo3 = TodoParser.parseMarkdown("- [x] Read book");

        assertEquals(targetTodo3, parsedTodo3);

        // - [ ] Read book | Created at: 2024-03-25T20:05:30 | 1200s | Due by: 2025-03-25T20:05:30
        Todo targetTodo4 = new Todo.TodoBuilder("Read book").timeBudget(1200).due(LocalDateTime.parse("2025-03-25T20:05:30")).createdAt(LocalDateTime.parse("2024-03-25T20:05:30")).build();
        Todo parsedTodo4 = TodoParser.parseMarkdown("- [ ] Read book | Created at: 2024-03-25T20:05:30 | 1200s | Due by: 2025-03-25T20:05:30");

        assertEquals(targetTodo4, parsedTodo4);
    }
}