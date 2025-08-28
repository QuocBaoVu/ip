import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{
//    private String start;
//    private String end;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - h:mma");
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public EventTask(String description, String start, String end) {
        super(description);
//        this.start = start;
//        this.end = end;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            this.startTime = LocalDateTime.parse(start.trim(), inputFormatter);
            this.endTime = LocalDateTime.parse(end.trim(), inputFormatter);
    }
    public String getType() {
        return "E";
    }
    public String getTime() {
        return this.startTime.format(inputFormatter) + " to " + this.endTime.format(inputFormatter);
    }

    public String getStartTime() {
        return this.startTime.format(outputFormatter);
    }

    public String getEndTime() {
        return this.endTime.format(outputFormatter);
    }

    @Override
    public String toString() {
            return String.format("[E][%s] %s (FROM %s TO %s)", this.isDone() ? "X" : "", this.getDescription(), this.getStartTime(), this.getEndTime());
    }
}
