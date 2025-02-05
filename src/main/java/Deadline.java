public class Deadline extends Task {
    private String by;

    public Deadline(String taskName, String date) throws SigmaException {
        super(taskName, "D");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }

        if (date.equals("")) {
            throw new NoDeadlineException();
        }
        this.by = date;
    }

    public Deadline(String taskName, boolean done, String date) {
        super(taskName, done, "T");
        this.by = date;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() + " (By:" + by + ")";
    }
}
