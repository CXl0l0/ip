package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the task's
 * new info when editing a deadline task.
 */
public class NoNewDeadlineInfoException extends SigmaException {
    /**
     * Constructor for NoNewNameException.
     */
    public NoNewDeadlineInfoException() {
        super("What are you trying to edit yo? Edit the name by using " 
                + "the '/name' keyword and edit the deadline by using the '/by' keyword!");
    }
}
