public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public String getTime() {
        return "";
    };
    @Override public String toString(){
        if (this.isDone()) {
            return String.format("[T][X] %s", this.getDescription());
        }
        else {
            return String.format("[T][ ] %s", this.getDescription());
        }
    }
}
