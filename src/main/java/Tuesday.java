import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Tuesday {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Tuesday");
        System.out.println("What can I do for you?");

        List<Task> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("What the date today")) {
                LocalDate now = LocalDate.now();
                System.out.println(now);
            }
            else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                Task task= list.get(taskIndex - 1);
                task.markDone();
                System.out.println( "Nice! I've marked this task as done:");
                System.out.println(task.toString());
            }
            else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                Task task = list.get(taskIndex - 1);
                task.unmarkDone();
                System.out.println( "OK, I've marked this task as not done yet:");
                System.out.println(task.toString());
            }
            else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task task = new TodoTask(description);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
            else if (input.startsWith("deadline ")) {
                String content = input.substring(9);
                String[] splitContent = content.split(" /by ", 2);
                Task task = new DeadlineTask(splitContent[0],splitContent[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
            else if (input.startsWith("event ")) {
                String content = input.substring(6);
                String regex = "/from | /to";
                String[] splitContent = content.split(regex);
                Task task = new EventTask(splitContent[0],splitContent[1], splitContent[2]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            } else {
                Task newTask = new Task(input);
                System.out.println("added: " + input);
                list.add(newTask);
            }
        }
        sc.close();

    }
}
