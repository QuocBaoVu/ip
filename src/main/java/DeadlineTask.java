

public class DeadlineTask extends Task {
    private String deadline;
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return String.format("[D][X] %s (by: %s)", this.getDescription(), this.deadline);
        }
        else {
            return String.format("[D][ ] %s (by: %s)", this.getDescription(),  this.deadline);
        }
    }
}
