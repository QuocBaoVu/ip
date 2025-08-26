public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override public String toString(){
        if (this.isDone()) {
            return String.format("[T][X] %s", this.getDescription());
        }
        else {
            return String.format("[T][ ] %s", this.getDescription());
        }
    }
}
