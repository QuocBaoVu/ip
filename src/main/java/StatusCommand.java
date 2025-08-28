public class StatusCommand extends Command {

    private String type;
    private String index;

    public StatusCommand(String type, String index) {
        this.type = type;
        this.index = index;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
