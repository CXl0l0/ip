package sigma.exception;

/**
 * An exception that represents when user fails to specify the event time when creating an event task.
 */
public class NoEventTimeException extends SigmaException {
    public NoEventTimeException() {
        super("When's the event time yo? Add a time by using the /from and /to keyword after indicating the task name!");
    }
}
