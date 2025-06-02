package vue;

import controleur.Controleur;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new HBoxRoot();
        root.setPadding(new Insets(0, 10, 0, 10));
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("APPLI");
        stage.setResizable(true);
        File css = new File("css" + File.separator + "style.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pokemon.png"))));
        Controleur controleur = HBoxRoot.getControleur();
        stage.setOnCloseRequest(controleur::handleWindowClose);

        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}