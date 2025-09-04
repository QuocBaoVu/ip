package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

/**
 * Represent a command to update the completion status of a task
 * Support mark/unmark actions
 */
public class StatusCommand extends Command {

    private final String TYPE;
    private final String INDEX;

    /**
     * Construct a StatusCommand with the specified operation type and index
     * @param type: 'mark' or 'unmark'
     * @param index: index of the task that we want to alter
     */
    public StatusCommand(String type, String index) {
        this.TYPE = type;
        this.INDEX = index;
    }

    /**
     * Execute the status command by marking or unmarking the specified task
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(Integer.parseInt(INDEX) - 1);
            switch (TYPE) {
                case "mark":
                    task.markDone();
                    break;
                case "unmark":
                    task.unmarkDone();
                    break;
            }

            ListCommand ls = new ListCommand();
            ls.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response;
        try {
            Task task = tasks.getTask(Integer.parseInt(INDEX) - 1);
            switch (TYPE) {
                case "mark":
                    task.markDone();
                    break;
                case "unmark":
                    task.unmarkDone();
                    break;
            }

            ListCommand ls = new ListCommand();
            response = ls.getResponse(tasks, ui, storage);
            ls.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            response = "Error: " + e.getMessage();
            ui.showError(e.getMessage());
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
