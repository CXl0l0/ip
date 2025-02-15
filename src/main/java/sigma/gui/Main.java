package sigma.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sigma.command.Sigma;

/**
 * A GUI for Sigma using FXML.
 */
public class Main extends Application {

    private Sigma sigma = new Sigma();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSigma(sigma);  // inject the Sigma instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

