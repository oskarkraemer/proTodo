package me.oskarkraemer.TodoList;

import me.oskarkraemer.Todo.TodoParser;

public class TodoListParser {
    public static TodoList parseMarkdown(String markdownTodoList) {
        //Remove empty lines
        String markdownWoEmpty = markdownTodoList.replaceAll("(?m)^[ \\t]*\\r?\\n", "");

        String[] markdownLines = markdownWoEmpty.split("\n");

        if(!markdownLines[0].startsWith("# ToDo: ")) throw new IllegalArgumentException("Invalid todoList format: todoList must start with '# ToDo: '.");

        TodoList todoList = new TodoList(markdownLines[0].split("# ToDo: ")[1]);

        for(int i = 1; i < markdownLines.length; i++) {
            if (markdownLines[i].startsWith("## Metadata")) {
                i++;

                for(; i < markdownLines.length; i++) {
                    String metaLine = markdownLines[i];

                    if(metaLine.startsWith("Author: ")) {
                        //TODO: Implement TodoList author
                    }

                    else if(metaLine.startsWith("Interval: ")) {
                        //TODO: Implement TodoList intervals
                    } else {
                        i--;
                        break;
                    }
                }
            }

            else if(markdownLines[i].startsWith("## List")) {
                i++;

                for(; i < markdownLines.length; i++) {
                    System.out.println("Parse ToDo: " + markdownLines[i]);
                    todoList.addTodo(TodoParser.parseMarkdown(markdownLines[i]));
                }
            }
        }

        return todoList;
    }
}
