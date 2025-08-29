package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadlineTime;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadlineTime = LocalDateTime.parse(deadline, inputFormatter);
    }

    // Return type of the Task
    public String getType() {
        return "D";
    }

    // Return the deadline time of Task in String form
    public String getTime() {
        return this.deadlineTime.format(inputFormatter);
    }

    // Return the deadline time of Task in LocalDateTime form
    public String getDeadlineTime() {
        return this.deadlineTime.format(outputFormatter);
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %s (BY %s)", this.isDone() ? "X" : " ", this.getDescription(),  this.getDeadlineTime());

    }
}
