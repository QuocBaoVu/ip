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

    private String type;
    private String index;

    /**
     * Construct a StatusCommand with the specified operation type and index
     * @param type: 'mark' or 'unmark'
     * @param index: index of the task that we want to alter
     */
    public StatusCommand(String type, String index) {
        this.type = type;
        this.index = index;
    }

    /**
     * Execute the status command by marking or unmarking the specified task
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(Integer.parseInt(index) - 1);
            switch (type) {
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
    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
