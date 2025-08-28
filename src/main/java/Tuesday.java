import java.io.FileNotFoundException;

public class Tuesday {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        // Load data from data/tuesday.txt to list
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath+"/data/tuesday.txt";
        new Tuesday(filePath).run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TuesdayException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
