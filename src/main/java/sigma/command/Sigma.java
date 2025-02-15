package sigma.command;

//CHECKSTYLE.OFF: Regexp
/**
 * Represents the chatbot object Sigma.
 * Provides task tracking management services.
 */
public class Sigma {
    private Ui ui;
    private String commandType;

    /**
     * Constructor of Sigma object.
     */
    public Sigma() {
        this.ui = new Ui();
    }

    /**
     * Starts the chatbot.
     */
    private void launch() {
        ui.start();
    }

    /**
     * Generates a response for the user's chat message.
     * 
     * @return The string for the response from Sigma.
     */
    public String getResponse(String input) {
        this.commandType = ui.identifyCommandType(input);
        return ui.generateResponse(input);
    }

    public String getCommandType() {
        return this.commandType;
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma();
        sigma.launch();
    }
}
