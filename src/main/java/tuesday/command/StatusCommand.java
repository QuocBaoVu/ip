package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

public class StatusCommand extends Command {

    private final String TYPE;
    private final String INDEX;

    public StatusCommand(String type, String index) {
        this.TYPE = type;
        this.INDEX = index;
    }

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
    public boolean isExit() {
        return false;
    }
}
