package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the task's
 * new info when editing an Event task.
 */
public class NoNewEventInfoException extends SigmaException {
    /**
     * Constructor for NoNewNameException.
     */
    public NoNewEventInfoException() {
        super("What are you trying to edit yo? Edit the name by using " 
                + "the '/name' keyword, the start date by using the '/from' keyword, "
                + "and the end date by using the '/to' keyword!");
    }
}
