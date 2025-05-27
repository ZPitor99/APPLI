package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.WindowEvent;
import modele.Scenario;
import modele.ScenarioTableItem;
import vue.HBoxRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;


public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent && event.getSource() instanceof RadioMenuItem) {
            RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
            String nomFichier = (String) menuItem.getUserData();
            chargerScenario(nomFichier);
        }
    }

    /**
     * Charge et affiche le scénario sélectionné dans la table
     *
     * @param nomFichier Le nom du fichier de scénario à charger
     */
    private void chargerScenario(String nomFichier) {
        try {
            File fichierScenario = new File("scenario" + File.separator + nomFichier);
            Scenario scenario = new Scenario(fichierScenario);

            String nomScenario = nomFichier.replace(".txt", "").replace("_", " ");
            HBoxRoot.getAffichageScenario().numScenario.setText("Scénario : " + nomScenario);

            // Créer une liste observable pour la table = fait les actualisations auto
            ObservableList<ScenarioTableItem> items = FXCollections.observableArrayList();

            for (int i = 0; i < scenario.getVendeurList().size(); i++) {
                String vendeur = scenario.getVendeurList().get(i);
                String acheteur = scenario.getAcheteurList().get(i);
                items.add(new ScenarioTableItem(vendeur, acheteur));
            }
            HBoxRoot.getAffichageScenario().tableDuScenarios.setItems(items);

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de chargement");
            alert.setHeaderText("Impossible de charger le scénario");
            alert.setContentText("Le fichier " + nomFichier + " n'a pas pu être trouvé ou lu.");
            alert.showAndWait();
        }
    }

    /**
     * Affiche une fenetre de confirmation avant de quitter l'application
     *
     * @param event
     */
    public void handleWindowClose(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application");
        alert.setHeaderText("Êtes-vous certain de vouloir quitter ?");
        alert.setContentText("Toutes les données non sauvegardées seront perdues.");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() == ButtonType.NO) {
            event.consume();
        }
    }
}
