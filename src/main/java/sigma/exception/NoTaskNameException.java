package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the task's name while creating a task.
 */
public class NoTaskNameException extends SigmaException {
    /**
     * Constructor for NoTaskNameException.
     */
    public NoTaskNameException() {
        super("What's the task name yo?");
    }
    
}
