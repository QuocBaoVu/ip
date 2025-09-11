package tuesday.parser;

import tuesday.command.AddCommand;
import tuesday.command.DeleteCommand;
import tuesday.command.StatusCommand;
import tuesday.command.Command;
import tuesday.command.EndCommand;
import tuesday.command.ListCommand;
import tuesday.command.FindCommand;
import tuesday.exception.TuesdayException;
import tuesday.command.CommandEnums.StatusAction;
import tuesday.task.TaskEnums.TaskType;


public class Parser {
    /**
     * Parses a user input string and returns a corresponding command object.
     * @param input
     * @return Command representing the parsed input
     * @throws TuesdayException If the input cannot be parsed into a valid command
     */
    public static Command parse(String input) throws TuesdayException {
        assert input != null : "Input cannot be null";
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
        case "bye":
            return new EndCommand();

        case "list":
            return new ListCommand();

        case "mark":
        case "unmark":
            return parseStatus(words, commandWord);

        case "delete":
            if (words.length < 2) {
                throw new TuesdayException("Missing task index to delete!");
            }
            return new DeleteCommand(words[1]);

        case "todo":
            return parseTodo(words);

        case "deadline":
            return parseDeadline(input);

        case "event":
            return parseEvent(input);

        case "find":
            return parseFind(input);

        default:
            throw new TuesdayException("Invalid command!");
    }
    }

    private static Command parseStatus(String[] words, String action) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException("Missing task index!");
        }
        StatusAction status = action.equals("mark") ? StatusAction.MARK : StatusAction.UNMARK;
        return new StatusCommand(status, words[1]);
    }

    private static Command parseTodo(String[] words) throws TuesdayException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new TuesdayException("Missing To-do task description!");
        }
        return new AddCommand(words[1].trim(), TaskType.TODO);
    }

    private static Command parseDeadline(String input) throws TuesdayException {
        if (!input.contains("/by")) {
            throw new TuesdayException("Missing deadline time. Use format: /by dd-MM-yyyy HHmm");
        }

        String deadlineContent = input.substring(9); // remove "deadline "
        String[] parts = deadlineContent.split(" /by ", 2);

        if (parts.length != 2) {
            throw new TuesdayException("Invalid deadline format. Use: deadline <desc> /by dd-MM-yyyy HHmm");
        }

        return new AddCommand(parts[0].trim(), TaskType.DEADLINE, parts[1].trim());
    }

    private static Command parseEvent(String input) throws TuesdayException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TuesdayException("Invalid event format. Use: event <desc> /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm");
        }

        String eventContent = input.substring(6); // remove "event "
        String regex = "/from | /to";
        String[] parts = eventContent.split(regex, 3);

        if (parts.length != 3) {
            throw new TuesdayException("Invalid event format. Use: event <desc> /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm");
        }

        return new AddCommand(parts[0].trim(), TaskType.EVENT, parts[1].trim(), parts[2].trim());
    }

    private static Command parseFind(String input) throws TuesdayException {
        if (input.trim().equals("find")) {
            throw new TuesdayException("Missing find keyword!");
        }

        String keyword = input.substring(5).trim();
        assert !keyword.isEmpty() : "Find command must have a keyword";

        return new FindCommand(keyword);
    }

    private static Command parseDelete(String[] words) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException("Missing task index!");
        }
        return new DeleteCommand(words[1].trim());
    }
}
