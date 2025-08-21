public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
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
}
