package tuesday.command;
import tuesday.storage.Storage;
import tuesday.task.*;
import tuesday.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;
    private String taskType;

    // Constructor for todo
    public AddCommand(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.startTime = "";
        this.endTime = "";
    }

    // Constructor for Deadline
    public AddCommand(String description, String taskType, String startTime) {
        this.description = description;
        this.startTime = startTime;
        this.taskType = taskType;
        this.endTime = "";
    }

    // Constructor for Event
    public AddCommand(String description, String taskType, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        try {
            switch (taskType) {
                case "todo":
                    task = new TodoTask(this.description);
                    tasks.addTask(task);
                    break;
                case "deadline":
                    task = new DeadlineTask(this.description, this.startTime);
                    tasks.addTask(task);
                    break;
                case "event":
                    task = new EventTask(this.description, this.startTime, this.endTime);
                    tasks.addTask(task);
                    break;
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (DateTimeParseException e) {
            ui.showError(e.getMessage() + "Time format should be: dd-MM-yyyy HHmm");
        }

    }
    public boolean isExit() {
        return false;
    }
}
