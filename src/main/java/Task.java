import java.util.HashMap;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s.", description);
        }
        else {
            return String.format("[ ] %s.", description);
        }
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    abstract String getType();

    abstract String getTime();

    public String getDescription() {
        return description;
    }
}
