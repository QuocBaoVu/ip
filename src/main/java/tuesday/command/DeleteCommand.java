package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;
/**
 * Represent a command to delete a task from the task list
 * Take an index, finds the corresponding task in the list,
 * remove it, and display a confirmation message to the user.
 */
public class DeleteCommand extends Command {
    private final String INDEX;

    private String index;

    /**
     * Construct a DeleteCommand with the specified task index
     * @param index
     */
    public DeleteCommand(String index) {
        this.INDEX = index;
    }


    /**
     * Executes the delete command by removing the task
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(Integer.parseInt(INDEX) - 1);
            tasks.deleteTask(task);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");

        } catch (IndexOutOfBoundsException | NumberFormatException e ) {
            ui.showError(e.getMessage());
        }
    }

    /**
     *
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
            task = tasks.getTask(Integer.parseInt(INDEX) - 1);
            tasks.deleteTask(task);
            response = "Noted. I've removed this task:\n" + task.toString() + "\n" + "Now you have " + tasks.size() + " tasks in the list";
            System.out.println(response);
        } catch (IndexOutOfBoundsException | NumberFormatException e ) {
            ui.showError(e.getMessage());
            response = "Error: " + e.getMessage();
        }
        assert task != null: "Task is null";
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
