package tuesday.parser;

import tuesday.exception.TuesdayException;
import tuesday.command.*;

public class Parser {

    public static Command parse(String input) throws TuesdayException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new EndCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (words.length < 2) {
                    throw new TuesdayException("Missing task index!");
                }
                return new StatusCommand("mark", words[1]);
            case "unmark":
                if (words.length < 2) {
                    throw new TuesdayException("Missing task index!");
                }
                return new StatusCommand("unmark", words[1]);
            case "delete":
                return new DeleteCommand(words[1]);
            case "todo":
                if (input.trim().equals("todo")) {
                    throw new TuesdayException("Missing To-do task description!");
                }
                String description = words[1];
                return new AddCommand(description, "todo");
            case "deadline":
                if (input.trim().equals("deadline")) {
                    throw new TuesdayException("Missing deadline task content!");
                }
                if (!input.contains("/by")) {
                    throw new TuesdayException("Missing deadline time for task content. Time format should be: /by dd-MM-yyyy HHmm");
                }
                String deadlineContent = input.substring(9);
                String[] deadlineSplitContent = deadlineContent.split(" /by ", 2);
                if (deadlineSplitContent.length != 2) {
                    throw new TuesdayException("Missing deadline time for task content. Time format should be: /by dd-MM-yyyy HHmm");
                }
                return new AddCommand(deadlineSplitContent[0].trim(), "deadline" ,deadlineSplitContent[1].trim());
            case "event":
                if (input.trim().equals("event")) {
                    throw new TuesdayException("Missing event task content!");
                }
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new TuesdayException("Wrong time format for event task. Time format should be: /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm");
                }
                String eventContent = input.substring(6);
                String regex = "/from | /to";
                String[] eventSplitContent = eventContent.split(regex);
                if (eventSplitContent.length != 3) {
                    throw new TuesdayException("Wrong time format for event task. Time format should be: /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm");
                }
                return new AddCommand(eventSplitContent[0].trim(), "event", eventSplitContent[1].trim(), eventSplitContent[2].trim());
            default:
                throw new TuesdayException("Invalid command!");
        }
    }
}
