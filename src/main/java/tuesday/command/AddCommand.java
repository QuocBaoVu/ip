package tuesday.command;

import java.time.format.DateTimeParseException;

import tuesday.storage.Storage;
import tuesday.task.TodoTask;
import tuesday.task.EventTask;
import tuesday.task.DeadlineTask;
import tuesday.task.TaskList;
import tuesday.task.Task;
import tuesday.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final String DESCRIPTION;
    private final String START_TIME;
    private final String END_TIME;
    private final String TASK_TYPE;

    /**
     * Construct AddCommend for a todo task
     * @param description
     * @param taskType
     */
    public AddCommand(String description, String taskType) {
        this.DESCRIPTION = description;
        this.START_TIME = taskType;
        this.END_TIME = "";
        this.TASK_TYPE = "";
    }

    /**
     * Construct AddCommend for deadline Task
     * @param description
     * @param taskType
     * @param startTime
     */
    public AddCommand(String description, String taskType, String startTime) {
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = "";
        this.TASK_TYPE = taskType;

    }

    /**
     * Construct AddCommend for event task
     * @param description
     * @param taskType
     * @param startTime
     * @param endTime
     */
    public AddCommand(String description, String taskType, String startTime, String endTime) {
        this.DESCRIPTION = description;
        this.START_TIME = startTime;
        this.END_TIME = endTime;
        this.TASK_TYPE = taskType;
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

    /**
     * Execute the add response
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        String response = "";
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
            if (task == null) {
                throw new NullPointerException("task is null");
            }
            response = "Got it. I've added this task:\n" + task.toString() +
                    "\n" + "Now you have " + tasks.size() + " tasks in the list";
            System.out.println(response);
        } catch (DateTimeParseException e) {
            response = e.getMessage() +
                    "Time format should be: dd-MM-yyyy HHmm";
            ui.showError(response);
        } catch (NullPointerException e) {
            response = e.getMessage();
            ui.showError(response);
        }
        return response;

    }

    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    public boolean isExit() {
        return false;
    }
}
