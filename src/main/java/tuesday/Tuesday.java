package tuesday;

import java.io.File;
import java.io.FileNotFoundException;

import tuesday.command.Command;
import tuesday.exception.TuesdayException;
import tuesday.parser.Parser;
import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main entry point for Tuesday chatbot
 * Initializes UI, storage, and task list, and runs the program loop.
 */

public class Tuesday {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a Tuesday application using the given filepath
     * Load the tasks from the filepath storage file available
     * @param filePath
     */
    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showError("Failed to load data: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Entry point of application
     * Create the required data directory if required
     * @param args
     */
    public static void main(String[] args) {
        // Load data from data/tuesday.txt to list
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/data/tuesday.txt";

        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            System.out.println("Failed to create directory: " + parent.getAbsolutePath());
        }

        System.out.println("Loading from: " + filePath);
        new Tuesday(filePath).run();
    }

    /**
     * Run the chatbot with welcome message
     * Parse user command until exit command
     */
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
