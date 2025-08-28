public class EndCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showDataSaving();
        try {
            storage.saveData(tasks.getTasks());
            ui.showDataSaved();
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }


       ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
