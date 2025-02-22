package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the task's
 * new name when editing it.
 */
public class NoNewNameException extends SigmaException {
    /**
     * Constructor for NoNewNameException.
     */
    public NoNewNameException() {
        super("What's the new name yo? Edit the name by using " 
                + "the '/name' keyword and entering the name after the keyword!");
    }
}
