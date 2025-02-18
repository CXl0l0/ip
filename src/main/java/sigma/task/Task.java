package sigma.task;

//CHECKSTYLE.OFF: Regexp
/**
 * The parent class of ToDo, Deadline and Event tasks. Contains the basic
 * field of a task: name, type and completion state.
 */
public class Task {
    private String taskName;
    private String taskType;
    private boolean isDone;

    /**
     * Returns Task objects.
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
     * Returns a Task object by taking in an additional argument 
     * 'isDone' which indicates the completio state of the task. 
     * For internal use (eg: Creating task objects
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
     * Returns the field taskName.
     * 
     * @return The name of the task.
     */
    public String getTaskName() {
        assert taskName != "";
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        if (!taskName.equals("")) {
            this.taskName = taskName;
        }
    }

    /**
     * Returns the field taskType.
     * 
     * @return The type of the task.
     */
    public String getTaskType() {
        assert taskType != "";
        return this.taskType;
    }

    /**
     * Returns the field isDone.
     * 
     * @return The completion state of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the field isDone.
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
