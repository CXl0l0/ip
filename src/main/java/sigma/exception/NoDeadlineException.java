package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the deadline while creating a deadline task.
 */
public class NoDeadlineException extends SigmaException {
    /**
     * Constructor for NoDeadlineException.
     */
    public NoDeadlineException() {
        super("When's the deadline yo? " 
                + "Add a deadline by using the " 
                + "/by keyword after indicating the task name!");
    }
    
}
