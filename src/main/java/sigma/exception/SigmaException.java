package sigma.exception;

/**
 * Represents any exceptions related to the Sigma chatbot.
 */
public class SigmaException extends Exception {
    public SigmaException(String message) {
        super(message);
    }
}