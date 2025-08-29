package tuesday.command;

import java.time.format.DateTimeParseException;

import tuesday.storage.Storage;
import tuesday.task.TodoTask;
import tuesday.task.EventTask;
import tuesday.task.DeadlineTask;
import tuesday.task.TaskList;
import tuesday.task.Task;
import tuesday.ui.Ui;

public class AddCommand extends Command {
    private final String DESCRIPTION;
    private final String START_TIME;
    private final String END_TIME;
    private final String TASK_TYPE;

    // Constructor for todo
    public AddCommand(String description, String taskType) {
        this.DESCRIPTION = description;
        this.START_TIME = taskType;
        this.END_TIME = "";
        this.TASK_TYPE = "";
    }

    // Constructor for Deadline
    public AddCommand(String description, String taskType, String startTime) {
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = "";
        this.TASK_TYPE = taskType;

    }

    // Constructor for Event
    public AddCommand(String description, String taskType, String startTime, String endTime) {
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = endTime;
        this.TASK_TYPE = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        try {
            switch (TASK_TYPE) {
            case "todo":
                task = new TodoTask(this.DESCRIPTION);
                tasks.addTask(task);
                break;
            case "deadline":
                task = new DeadlineTask(this.DESCRIPTION, this.START_TIME);
                tasks.addTask(task);
                break;
            case "event":
                task = new EventTask(this.DESCRIPTION, this.START_TIME, this.END_TIME);
                tasks.addTask(task);
                break;
            }

            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (DateTimeParseException e) {
            ui.showError(e.getMessage() +
                    "Time format should be: dd-MM-yyyy HHmm");
        }

    }
    public boolean isExit() {
        return false;
    }
}
