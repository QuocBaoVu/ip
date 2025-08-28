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
            return String.format("[T][%s] %s", this.isDone() ? "X" : "" ,this.getDescription());
    }
}
