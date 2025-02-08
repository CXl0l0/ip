package sigma.task;

/**
 * The parent class of ToDo, Deadline and Event tasks. Contains the basic
 * field of a task: name, type and completion state.
 */
public class Task {
    private String taskName;
    private String taskType;
    private boolean isDone;

    /**
     * Constructor of Task objects.
     * 
     * @param taskName
     * @param taskType
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Constructor of Task objects. Returns a Task object by taking
     * in an additional argument 'isDone' which indicates the completion
     * state of the task. For internal use (eg: Creating task objects
     * by reading the data files).
     * 
     * @param taskName
     * @param isDone
     * @param taskType
     */
    public Task(String taskName, boolean isDone, String taskType) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * A getting method for the field taskName.
     * 
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * A getting method for the field taskType.
     * 
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * A getting method for the field isDone.
     * 
     * @return The completion state of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * A setter method for the field isDone.
     * 
     * @param isDone The completion state of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
}
