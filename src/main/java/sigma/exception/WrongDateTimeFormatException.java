package sigma.exception;

public class WrongDateTimeFormatException extends SigmaException {
    public WrongDateTimeFormatException() {
        super("Invalid format yo, follow this format 'yyyy-MM-dd HHmm'");
    }
}
