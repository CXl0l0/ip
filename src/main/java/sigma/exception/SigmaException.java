package sigma.exception;

//CHECKSTYLE.OFF: Regexp
/**
 * Represents any exceptions related to the Sigma chatbot.
 */
public class SigmaException extends Exception {
    /**
     * Constructor for SigmaException.
     * @param message The message of the exception.
     */
    public SigmaException(String message) {
        super(message);
        assert message != "";
    }
}
