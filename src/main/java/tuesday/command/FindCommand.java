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

    /**
     * Execute the "Find" Command
     * @param tasks
     * @param ui
     * @param storage
     */
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

    /**
     * Return response for Find command
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingList = new ArrayList<>();
        String response = "";
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(INPUT)) {
                matchingList.add(task);
            }
        }
        if (matchingList.isEmpty()) {
            response = "No matching tasks found.";
            System.out.println(response);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : matchingList) {
                response = task.getDescription();
                System.out.println(task);
            }
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
