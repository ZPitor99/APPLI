package controleur;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        System.out.println("Controleur");
    }

    //Quitter APPLI
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application ?");
        alert.setHeaderText("Etes-vous certain de vouloir quitter ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
