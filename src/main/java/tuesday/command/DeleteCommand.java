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
    private String index;

    /**
     * Construct a DeleteCommand with the specified task index
     * @param index
     */
    public DeleteCommand(String index) {
        this.index = index;
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
            Task task = tasks.getTask(Integer.parseInt(index) - 1);
            tasks.deleteTask(task);

            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException | NumberFormatException e ) {
            ui.showError(e.getMessage());
        }


    }

    public boolean isExit() {
        return false;
    }
}
