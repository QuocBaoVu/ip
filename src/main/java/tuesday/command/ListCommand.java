package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

/**
 * Represent a command to list all tasks currently in the task list
 * Print each task with its index to the console
 */
public class ListCommand extends Command {
    /**
     * Execute the list command by printing all tasks in the task list
     * along with their indices
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            System.out.println("No tasks found");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Return the response for the List command
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        if (tasks.getTasks().isEmpty()) {
            response = "No tasks found";
            System.out.println(response);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                response += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
            }
        }
        return response;
    }
    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
