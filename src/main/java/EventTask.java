public class EventTask extends Task{
    private String start;
    private String end;
    public EventTask(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
    }
    public String getType() {
        return "E";
    }
    public String getTime() {
        return this.start + " " + this.end;
    }
    @Override
    public String toString() {
        if (this.isDone()) {
            return String.format("[D][X] %s (from: %s to: %s)", this.getDescription(), this.start, this.end);
        }
        else {
            return String.format("[D][ ] %s (from: %s to: %s)", this.getDescription(), this.start, this.end);
        }
    }
}
