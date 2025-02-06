import java.io.IOException;

public class Sigma {
    public Sigma() throws IOException {
        this.ui = new Ui();
    }
    
    private Ui ui;

    private void launch() throws SigmaException, IOException {
        ui.start();
    }

    public static void main(String[] args) throws SigmaException, IOException {
        Sigma sigma = new Sigma();
        sigma.launch();
    }
}
