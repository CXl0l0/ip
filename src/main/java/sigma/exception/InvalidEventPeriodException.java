package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the deadline while creating a deadline task.
 */
public class InvalidEventPeriodException extends SigmaException {
    /**
     * Constructor for NoDeadlineException.
     */
    public InvalidEventPeriodException() {
        super("The event's start date can't be after the event's end date yo!");
    }
    
}
