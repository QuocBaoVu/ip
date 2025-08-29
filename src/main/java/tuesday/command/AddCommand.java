package tuesday.command;
import tuesday.storage.Storage;
import tuesday.task.*;
import tuesday.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represent a command to add a new task to the task list
 * Supports creation of three types of tasks:
 *  todo -> Description
 *  deadline -> Description + Deadline time
 *  event -> Description + Start time + End time
 */
public class AddCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;
    private String taskType;

    /**
     * Construct AddCommend for a todo task
     * @param description
     * @param taskType
     */
    public AddCommand(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.startTime = "";
        this.endTime = "";
    }

    /**
     * Construct AddCommend for deadline Task
     * @param description
     * @param taskType
     * @param startTime
     */
    public AddCommand(String description, String taskType, String startTime) {
        this.description = description;
        this.startTime = startTime;
        this.taskType = taskType;
        this.endTime = "";
    }

    /**
     * Construct AddCommend for event task
     * @param description
     * @param taskType
     * @param startTime
     * @param endTime
     */
    public AddCommand(String description, String taskType, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }

    /**
     * Execute the add command by creating the correct type of task
     * Add it to the task list
     * Display a confirmation message to the user and the updated number of task
     * @param tasks Task list where new task is added
     * @param ui User interface for displaying message
     * @param storage Storage used to store task to hardware
     */
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

    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    public boolean isExit() {
        return false;
    }
}
