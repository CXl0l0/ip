package sigma.task;

public class Task {
    private String taskName;
    private String taskType;
    private boolean isDone;

    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String taskName, boolean isDone, String taskType) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskType() {
        return this.taskType;
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
