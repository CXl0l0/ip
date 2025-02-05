public class Event extends Task {
    private String from;
    private String to;

    public Event(String taskName, boolean done, String from, String to) {
        super(taskName, done, "E");
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, String from, String to) throws SigmaException {
        super(taskName, "E");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }

        if (from.equals("") || to.equals("")) {
            throw new NoEventTimeException();
        }

        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() + " (From:" + from + " -- To:" + to + ")";
    }
}
