package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.WindowEvent;
import modele.GrapheOriente;
import modele.Scenario;
import modele.ScenarioTableItem;
import vue.HBoxRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent && event.getSource() instanceof RadioMenuItem){
            RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
            String nomFichier = (String) menuItem.getUserData();
            chargerScenario(nomFichier);

            String nomScenario = menuItem.getText();

            File scFile = new File("scenario" + File.separator + toNomFichier(nomScenario));
            try {
                Scenario sc = new Scenario(scFile);
                sc.setListDouble();
                GrapheOriente g = new GrapheOriente(sc.getVendeurListDouble(), sc.getAcheteurListDouble());
                sc.setTrieTopologiqueSimple(g.trieTopologique());
                sc.setTrieTopologiqueGlouton(g.trieTopologiqueGlouton());
                if (Integer.valueOf(nomScenario.substring(nomScenario.length() - 1)) < 4) {
                    sc.setTrieTopologiqueOptimal(g.trieTopologiqueOptimal(10));
                    Integer nbChemin = HBoxRoot.getAffichageOptiGestion().cbNbCheminOptimal.getValue();

                    StringBuilder cheminsOptimaux = new StringBuilder();
                    for (int i = 0; i < nbChemin; i++) {
                        List<String> cheminAffiche = sc.getTrieTopologiqueOptimal().get(i);
                        cheminsOptimaux.append("Vélizy, ");
                        cheminsOptimaux.append(cheminAffiche.toString().substring(1, cheminAffiche.toString().length() - 1));
                        cheminsOptimaux.append(", Vélizy");
                        cheminsOptimaux.append("\n");
                        int ibis = i+1;
                        cheminsOptimaux.append("Longeur du chemin ").append(ibis).append(" en kilomètre: ")
                                .append(sc.getTrieTopologiqueOptimalLongueur().get(i)).append("\n").append("\n");
                    }
                    HBoxRoot.getAffichageOptiGestion().majCheminOptimal(cheminsOptimaux.toString());

                } else {
                    HBoxRoot.getAffichageOptiGestion().majCheminOptimal("Données non renseignées");
                }
                HBoxRoot.getAffichageChemin().majCheminSimple("Vélizy, " +
                        sc.getTrieTopologiqueSimple().toString().substring(1, sc.getTrieTopologiqueSimple().toString().length() - 1)
                        + ", Vélizy" + "\n\n" + "Distance de parcours en kilomètre: " +
                        sc.getTrieTopologiqueSimpleLongueur().toString());
                HBoxRoot.getAffichageChemin().majCheminHeuristique("Vélizy, " +
                        sc.getTrieTopologiqueGlouton().toString().substring(1, sc.getTrieTopologiqueGlouton().toString().length() - 1)
                        + ", Vélizy" + "\n\n" + "Distance de parcours en kilomètre: " +
                        sc.getTrieTopologiqueGloutonLongueur().toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
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
     * @param event événement
     */
    public void handleWindowClose(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application");
        alert.setHeaderText("Êtes-vous certain de vouloir quitter ?");
        alert.setContentText("Toutes les données non sauvegardées seront perdues.");

        //CSS et ID
        alert.getDialogPane().setId("alert-dialog");
        alert.getDialogPane().lookup(".header-panel").setId("alert-title");
        alert.getDialogPane().lookup(".content.label").setId("alert-content");
        File css = new File("css" + File.separator + "style.css");
        alert.getDialogPane().getStylesheets().add(css.toURI().toString());

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() == ButtonType.NO) {
            event.consume();
        }
    }

    /**
     * Traite la string pour changer les espaces en _ et ajouté .txt en fin de string
     *
     * @param nomScenario Une chaine de character avec des espaces
     * @return Une chaine de character pour être un nom de fichier.txt
     */
    private String toNomFichier(String nomScenario) {
        return nomScenario.substring(1).replace(" ", "_") +
                ".txt";
    }
}
