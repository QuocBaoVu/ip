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
                Task t = list.get(taskIndex - 1);
                t.markDone();
                System.out.println( "Nice! I've marked this task as done:");
                System.out.println(t.toString());
            }
            else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                Task t = list.get(taskIndex - 1);
                t.unmarkDone();
                System.out.println( "OK, I've marked this task as not done yet:");
                System.out.println(t.toString());
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
