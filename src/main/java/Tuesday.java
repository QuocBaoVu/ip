import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Tuesday {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Tuesday");
        System.out.println("What can I do for you?");

        List<String> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("What the date today")) {
                LocalDate now = LocalDate.now();
                System.out.println(now);
            }
            else if (input.equals("list")) {
                Integer counter = 1;
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                System.out.println("added: " + input);
                list.add(input);
            }
        }

    }
}
