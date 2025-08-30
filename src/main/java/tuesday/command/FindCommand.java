package tuesday.command;

import java.util.ArrayList;
import java.util.List;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

public class FindCommand extends Command{
    private final String INPUT;

    public FindCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingList = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(INPUT)) {
                matchingList.add(task);
            }
        }

        if (matchingList.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : matchingList) {
                System.out.println(task);
            }
        }


    }

    @Override
    public boolean isExit() {
        return false;
    }
}
