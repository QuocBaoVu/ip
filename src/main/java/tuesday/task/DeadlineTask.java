package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *  Implementation of DeadlineTask Class
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadlineTime;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Construct Deadline task with a description, deadline
     * @param description
     * @param deadline
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadlineTime = LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * Return the String of type
     * @return 'D"
     */
    public String getType() {
        return "D";
    }

    /**
     * Return the detailed deadline time format for saving data to storage
     * @return @return format: dd-MM-yyyy HHmm
     */
    public String getTime() {
        return this.deadlineTime.format(inputFormatter);
    }

    /**
     * Return the decorated deadline time format for display in terminal
     * @return format: dd MMM yyyy - h:mma
     */
    public String getDeadlineTime() {
        return this.deadlineTime.format(outputFormatter);
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %s (BY %s)", this.isDone() ? "X" : " ", this.getDescription(),  this.getDeadlineTime());

    }
}
