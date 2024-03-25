package me.oskarkraemer.Todo;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoParser {
    private static final Pattern CHECKBOX_PATTERN = Pattern.compile("^- \\[([x ])] ");
    private static final Pattern SECONDS_PATTERN = Pattern.compile("(\\d+)s");
    public static Todo parseMarkdown(String markdownTodo) {
        markdownTodo = markdownTodo.trim();

        Matcher checkboxMatcher = CHECKBOX_PATTERN.matcher(markdownTodo);
        if(!checkboxMatcher.find()) throw new IllegalArgumentException("Invalid todo format: Incorrect checkbox pattern.");

        boolean completed = checkboxMatcher.group(1).equals("x");

        String[] splitString = markdownTodo.split(" \\| ");

        String description = splitString[0].substring(6);
        if(description.isEmpty()) throw new IllegalArgumentException("Invalid todo format: Description must not be empty.");

        if(splitString.length == 1) return new Todo.TodoBuilder(description).completed(completed).build();

        //Loop through optional arguments and match them respectively
        LocalDateTime createdAt = null;
        LocalDateTime due = null;
        int timeBudget = 0;

        for(int i = 1; i < splitString.length; i++) {
            //Set timeBudget if available
            Matcher secondsMatcher = SECONDS_PATTERN.matcher(splitString[i]);
            if(secondsMatcher.matches()) {
                String numberGroup = secondsMatcher.group(1);
                try {
                    timeBudget = Integer.parseInt(numberGroup);
                } catch (NumberFormatException e) {
                    System.out.println("[MDP]: Could not parse seconds - " + e);
                }
            }

            //Set Due by if available
            else if (splitString[i].startsWith("Due by: ")) {
                due = LocalDateTime.parse(splitString[i].split("Due by: ")[1]);
            }

            //Set Created at if available
            else if (splitString[i].startsWith("Created at: ")) {
                createdAt = LocalDateTime.parse(splitString[i].split("Created at: ")[1]);
            }
        }


        return new Todo.TodoBuilder(description)
                .createdAt(createdAt)
                .due(due)
                .timeBudget(timeBudget)
                .completed(completed)
                .build();
    }
}
