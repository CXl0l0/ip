public class ToDo extends Task {
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    public ToDo(String taskName) throws NoTaskNameException {
        super(taskName);
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.isDone() ? "X" : " ") + "] " + this.getTaskName();
    }
}
