package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public EventTask(String description, String start, String end) {
        super(description);
            this.startTime = LocalDateTime.parse(start.trim(), INPUT_FORMATTER);
            this.endTime = LocalDateTime.parse(end.trim(), INPUT_FORMATTER);
    }

    public String getType() {
        return "E";
    }
    public String getTime() {
        return this.startTime.format(INPUT_FORMATTER)
                + " to "
                + this.endTime.format(INPUT_FORMATTER);
    }

    public String getStartTime() {
        return this.startTime.format(OUTPUT_FORMATTER);
    }

    public String getEndTime() {
        return this.endTime.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
            return String.format("[E][%s] %s (FROM %s TO %s)",
                    this.isDone() ? "X" : " ",
                    this.getDescription(),
                    this.getStartTime(),
                    this.getEndTime());
    }
}
