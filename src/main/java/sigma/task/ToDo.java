package sigma.task;

import sigma.exception.NoTaskNameException;

//CHECKSTYLE.OFF: Regexp
/**
 * Simplest subset of the class "Task" which represents basic to do tasks with no other
 * extra fields.
 */
public class ToDo extends Task {
    
    /**
     * Returns ToDo object.
     * 
     * @param taskName The name of the task.
     * @throws NoTaskNameException If there is missing input of task's name.
     */
    public ToDo(String taskName) throws NoTaskNameException {
        super(taskName, "T");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }
    }

    /**
     * Returns a ToDo object by taking in 1 extra argument 
     * which indicates the marked/unmarked state of the task. 
     * For internal use only (eg: Creating event objects
     * by reading data files).
     * 
     * @param taskName The name of the task.
     * @param done The completion state of the task.
     */
    public ToDo(String taskName, boolean done) {
        super(taskName, done, "T");
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName();
    }
}
