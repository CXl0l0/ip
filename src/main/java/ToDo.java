public class ToDo extends Task {
    public ToDo(String taskName, boolean done) {
        super(taskName, done, "T");
    }

    public ToDo(String taskName) throws NoTaskNameException {
        super(taskName, "T");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.getIsDone() ? "X" : " ") + "] " + this.getTaskName();
    }
}
