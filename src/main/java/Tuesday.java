import java.util.Scanner;
import java.time.LocalDate;

public class Tuesday {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Tuesday");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("What the date today")) {
                LocalDate now = LocalDate.now();
                System.out.println(now);
            } else {
                System.out.println(input);
            }
    }

    }
}
