import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadData() throws FileNotFoundException {
        File file = new File(this.filePath);
        List<Task> list = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            // Load data process
            System.out.println("Loading data...");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // Skip empty line
                }
                // Split by " | "
                String[] data = line.split(" \\| ");

                String type = data[0];
                boolean isDone = data[1].equals("1");
                String description = data[2];

                Task task = null;

                switch (type) {
                    case "T":
                        task = new TodoTask(description);
                        break;
                    case "D":
                        task = new DeadlineTask(description, data[3]);
                        break;
                    case "E":
                        String[] timeData = data[3].split("to");
                        task = new EventTask(description, timeData[0], timeData[1]);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid type");
                }
                if (isDone) {
                    task.markDone();
                }
                list.add(task);
            }
        }
        System.out.println("Loaded data successfully");
        return list;
    }

    public void saveData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            switch (task.getType()) {
                case "T":
                    fw.write(String.format("T | %s | %s\n", task.isDone() ? 1 : 0, task.getDescription()));
                    break;
                case "D":
                    fw.write(String.format("D | %s | %s | %s\n", task.isDone() ? 1 : 0, task.getDescription(), task.getTime()));
                    break;
                case "E":
                    fw.write(String.format("E | %s | %s | %s\n", task.isDone() ? 1 : 0, task.getDescription(), task.getTime()));
                    break;
            }
        }
        fw.close();

    }
}
