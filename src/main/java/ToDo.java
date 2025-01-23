public class ToDo extends Task {
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.isDone() ? "X" : " ") + "] " + this.getTaskName();
    }
}
