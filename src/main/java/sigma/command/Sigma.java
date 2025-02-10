package sigma.command;

//CHECKSTYLE.OFF: Regexp
/**
 * Represents the chatbot object Sigma.
 * Provides task tracking management services.
 */
public class Sigma {
    private Ui ui;
    /**
     * Constructor of Sigma object.
     */
    public Sigma() {
        this.ui = new Ui();
    }

    /**
     * Function to start the chatbot.
     */
    private void launch() {
        ui.start();
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma();
        sigma.launch();
    }
}
