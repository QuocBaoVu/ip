public class DeleteCommand extends Command {
    private String index;
    public DeleteCommand(String index) {
        this.index = index;
    }
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
