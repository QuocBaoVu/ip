package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

public class ListCommand extends Command {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
