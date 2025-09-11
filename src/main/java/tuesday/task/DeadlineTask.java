package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *  Implementation of DeadlineTask Class
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadlineTime;

    private final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");


    /**
     * Construct Deadline task with a description, deadline
     * @param description
     * @param deadline
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        assert deadline != null: "deadline cannot be null";
        this.deadlineTime = LocalDateTime.parse(deadline, INPUT_FORMATTER);
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
        return this.deadlineTime.format(INPUT_FORMATTER);
    }

    /**
     * Return the decorated deadline time format for display in terminal
     * @return format: dd MMM yyyy - h:mma
     */
    public String getDeadlineTime() {
        return this.deadlineTime.format(OUTPUT_FORMATTER);
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %s (BY %s)",
                this.isDone() ? "X" : " ",
                this.getDescription(),
                this.getDeadlineTime());

    }
}
