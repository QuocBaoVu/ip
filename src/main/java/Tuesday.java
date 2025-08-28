import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tuesday {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initiate a list
        List<Task> list = new ArrayList<>();

        // Load data from data/tuesday.txt to list
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath+"/data/tuesday.txt";
        File file = new File(filePath);

        try (Scanner fileScanner = new Scanner(file))  {
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
                Boolean isDone = data[1].equals("1");
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println("Data loaded successfully!");

        System.out.println("Hello! I'm Tuesday");
        System.out.println("What can I do for you?");
        System.out.println("Start with 'todo', 'deadline' or 'event' + 'task_description' + 'time' to create respective task");
        System.out.println("Start with 'mark' or 'unmark' + 'task_index' to change status of the task ");
        System.out.println("Time format should be: dd-MM-yyyy HHmm" );

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    try {
                        System.out.println("Saving data...");
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
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    System.out.println("Data saved successfully!");
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("What the date today")) {
                    LocalDate now = LocalDate.now();
                    System.out.println(now);
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task task = list.get(taskIndex - 1);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.toString());
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task task = list.get(taskIndex - 1);
                    task.unmarkDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.toString());
                } else if (input.startsWith("delete ")){
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task task = list.get(taskIndex - 1);
                    list.remove(task);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
                else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new TuesdayException("ERROR: Missing todo task description!");
                    }
                    String description = input.substring(5);
                    Task task = new TodoTask(description);
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else if (input.startsWith("deadline")) {
                    if (input.trim().equals("deadline")) {
                        throw new TuesdayException("ERROR: Missing deadline task content!");
                    }
                    if (!input.contains("/by")) {
                        throw new TuesdayException("ERROR: Missing deadline time for task content!");
                    }

                    String content = input.substring(9);
                    String[] splitContent = content.split(" /by ", 2);
                    try {
                        Task task = new DeadlineTask(splitContent[0], splitContent[1]);
                        list.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Correct deadline date format: /by dd-MM-yyyy HHmm");

                    }
                } else if (input.startsWith("event")) {
                    if (input.trim().equals("event")) {
                        throw new TuesdayException("ERROR: Missing deadline task content!");
                    }
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new TuesdayException("ERROR: Missing event start or end time for task content!");
                    }
                    String content = input.substring(6);
                    String regex = "/from | /to";
                    String[] splitContent = content.split(regex);
                    try {
                        Task task = new EventTask(splitContent[0], splitContent[1], splitContent[2].trim());
                        list.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Correct event date format: /from dd-MM-yyyy HHmm /to dd-MM-yyyy");
                    }

                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                } else {
                    throw new TuesdayException("ERROR: Invalid input");
                }
            } catch (TuesdayException e) {
            System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR: The index of task is out of bounds");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number index for task");
            }
        }
        sc.close();
    }
}
