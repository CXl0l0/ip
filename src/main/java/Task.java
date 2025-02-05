public class Task {
    private String taskName;
    private String type;
    private boolean isDone;

    public Task(String taskName, String type) {
        this.taskName = taskName;
        this.isDone = false;
        this.type = type;
    }

    public Task(String taskName, boolean isDone, String type) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.type = type;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
}
