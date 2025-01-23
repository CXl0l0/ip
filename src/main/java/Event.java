public class Event extends Task {
    String from;
    String to;

    public Event(String taskName, boolean done, String from, String to) {
        super(taskName, done);
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (this.isDone() ? "X" : " ") + "]" + this.getTaskName() + " (From:" + from + " -- To:" + to + ")";
    }
}
