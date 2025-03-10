package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * An exception that represents when user fails to specify the date and time according
 * to the format while creating any tasks that requires specification of date or time.
 */
public class WrongDateTimeFormatException extends SigmaException {
    /**
     * Constructor of the WrongDateTimeFormatException.
     */
    public WrongDateTimeFormatException() {
        super("Invalid format yo, follow this format 'yyyy-MM-dd HHmm'");
    }
}
