package tuesday.command;

import java.util.ArrayList;
import java.util.List;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

public class FindCommand extends Command{
    private final String KEYWORD;
    private static final String NO_TASK_FOUND = "No matching tasks found";
    private static final String TASKS_FOUND = "Here are the matching tasks in your list:";

    public FindCommand(String input) {
        this.KEYWORD = input;
    }

    private List<Task> findMatching(TaskList tasks, String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    private String formatMatches(List<Task> matches) {
        if (matches.isEmpty()) {
            return NO_TASK_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder(TASKS_FOUND).append("\n");
        for (Task task : matches) {
            stringBuilder.append(task.getDescription()).append("\n");
        }
        return stringBuilder.toString().trim();
    }


    /**
     * Execute the "Find" Command
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = formatMatches(findMatching(tasks, KEYWORD));
        System.out.println(output);

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
        String response = formatMatches(findMatching(tasks, KEYWORD));
        assert response != null && !response.isEmpty() : "Response should not be empty";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
