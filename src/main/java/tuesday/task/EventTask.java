package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Implementation of EventTask Class
 */
public class EventTask extends Task{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Construct Event task with a description, start time and end time
     * @param description
     * @param start
     * @param end
     */
    public EventTask(String description, String start, String end) {
        super(description);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.startTime = LocalDateTime.parse(start.trim(), inputFormatter);
        this.endTime = LocalDateTime.parse(end.trim(), inputFormatter);
    }

    /**
     * Return the String of type
     * @return
     */
    public String getType() {
        return "E";
    }

    /**
     * Return the detailed event time format for saving data to storage
     * @return format: dd-MM-yyyy HHmm
     */
    public String getTime() {
        return this.startTime.format(inputFormatter) + " to " + this.endTime.format(inputFormatter);
    }

    /**
     * Return the decorated event time format for display in terminal
     * @return dd MMM yyyy - h:mma
     */
    public String getStartTime() {
        return this.startTime.format(outputFormatter);
    }

    /**
     * Return the decorated deadline time format for display in terminal
     * @return
     */
    public String getEndTime() {
        return this.endTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (FROM %s TO %s)", this.isDone() ? "X" : " ", this.getDescription(), this.getStartTime(), this.getEndTime());
    }
}
