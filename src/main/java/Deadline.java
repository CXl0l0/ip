public class Deadline extends Task {
    String by;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.by = date;
    }

    public Deadline(String taskName, boolean done, String date) {
        super(taskName, done);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (this.isDone() ? "X" : " ") + "]" + this.getTaskName() + " (By:" + by + ")";
    }
}
