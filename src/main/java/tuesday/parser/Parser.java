package tuesday.parser;

import tuesday.command.*;
import tuesday.exception.TuesdayException;
import tuesday.command.CommandEnums.StatusAction;
import tuesday.task.TaskEnums.TaskType;


public class Parser {

    private static final String USE_FORMAT = "Use format: ";
    private static final String TODO_FORMAT = USE_FORMAT + "todo <desc>";
    private static final String DEADLINE_FORMAT = USE_FORMAT + "deadline <desc> /by dd-MM-yyyy HHmm";
    private static final String EVENT_FORMAT = USE_FORMAT + "event <desc> /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm";
    private static final String MISSING_INDEX = "Missing task index!";
    /**
     * Parse a user input string and returns a corresponding command object.
     * @param input: Input from user
     * @return Command representing the parsed input
     * @throws TuesdayException If the input cannot be parsed into a valid command
     */
    public static Command parse(String input) throws TuesdayException {
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
        case "sort-type":
            return parseSortByType(input);
        case "sort-time":
            return parseSortByTime(input);
        default:
            throw new TuesdayException("Invalid command!");
        }
    }

    /**
     * Parse a Status command
     * @param words: List of words from input
     * @param action: MARK or UNMARK
     * @return: Return StatusCommand
     * @throws TuesdayException
     */
    private static Command parseStatus(String[] words, String action) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException(MISSING_INDEX);
        }
        StatusAction status = action.equals("mark") ? StatusAction.MARK : StatusAction.UNMARK;
        return new StatusCommand(status, words[1]);
    }

    /**
     * Parse a AddCommand for To-do Task
     * @param words: Input list of words
     * @return: AddCommand with To-do constructors
     * @throws TuesdayException
     */
    private static Command parseTodo(String[] words) throws TuesdayException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new TuesdayException(TODO_FORMAT);
        }
        return new AddCommand(words[1].trim(), TaskType.TODO);
    }

    /**
     * Parse a command to add a Deadline Task
     * @param input: The untouch input from user
     * @return AddCommend with Deadline constructor
     * @throws TuesdayException
     */
    private static Command parseDeadline(String input) throws TuesdayException {
        if (!input.contains("/by")) {
            throw new TuesdayException("Missing deadline time. " + DEADLINE_FORMAT);
        }
            String deadlineContent = input.substring(9); // remove "deadline "
            String[] parts = deadlineContent.split(" /by ", 2);

            if (parts.length != 2) {
                throw new TuesdayException("Invalid deadline format. " + DEADLINE_FORMAT);
            }

            return new AddCommand(parts[0].trim(), TaskType.DEADLINE, parts[1].trim());
    }

    /**
     * Parse a command to add a Event task
     * @param input: The untouched input from user
     * @return AddCommend with Event constructor
     * @throws TuesdayException
     */
    private static Command parseEvent(String input) throws TuesdayException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TuesdayException("Invalid event format. " + EVENT_FORMAT);
        }

        String eventContent = input.substring(6); // remove "event "
        String regex = "/from | /to";
        String[] parts = eventContent.split(regex, 3);

        if (parts.length != 3) {
            throw new TuesdayException("Invalid event format. " + EVENT_FORMAT);
        }

        return new AddCommand(parts[0].trim(), TaskType.EVENT, parts[1].trim(), parts[2].trim());
    }

    /**
     * Parse Find command
     * @param input: untouched input from user
     * @return FindCommand
     * @throws TuesdayException
     */
    private static Command parseFind(String input) throws TuesdayException {
        if (input.trim().equals("find")) {
            throw new TuesdayException("Missing find keyword!");
        }

        String keyword = input.substring(5).trim();

        return new FindCommand(keyword);
    }

    /**
     * Parse Delete command
     * @param words: list of words from users
     * @return DeleteCommand
     * @throws TuesdayException
     */
    private static Command parseDelete(String[] words) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException(MISSING_INDEX);
        }
        return new DeleteCommand(words[1].trim());
    }


    private static Command parseSort(String input, CommandEnums.SortAction action) throws TuesdayException {
        // sort-type /by <type> OR sort-time /by <time>
        if (!input.contains("/by")) {
            throw new TuesdayException("Invalid sort by format.");
        }
        String[] parts = input.split(" /by ", 2);
        if (parts.length != 2) {
            throw new TuesdayException("Invalid sort by format.");
        }

        TaskType type = TaskType.valueOf(parts[1].trim().toUpperCase());
        return new SortCommand(action, type);
    }

    private static Command parseSortByType(String input) throws TuesdayException {
        return parseSort(input, CommandEnums.SortAction.TYPE);
    }

    private static Command parseSortByTime(String input) throws TuesdayException {
        return parseSort(input, CommandEnums.SortAction.TIME);
    }

}
