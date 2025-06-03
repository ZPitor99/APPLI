package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.GrapheOriente;
import modele.Scenario;
import modele.ScenarioTableItem;
import vue.DialogueAjouterTransaction;
import vue.DialogueSupprimerTransaction;
import vue.HBoxRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent && event.getSource() instanceof RadioMenuItem menuItem) {
            String nomFichier = (String) menuItem.getUserData();
            chargerScenario(nomFichier);

            String nomScenario = menuItem.getText();

            File scFile = new File("scenario" + File.separator + toNomFichier(nomScenario));
            majChemins(scFile, nomScenario);
        }
    }

    /**
     * Fait en sorte de mettre à jour les chemins sur la vue en ordant au modèle de les calculer pour les
     * envoyer à la vue une fois fait.
     *
     * @param scFile      Le fichier du scénario
     * @param nomScenario Le nom du scénario
     */
    private static void majChemins(File scFile, String nomScenario) {
        try {
            HBoxRoot.setScenarioActuel(new Scenario(scFile));
            Scenario sc = HBoxRoot.getScenarioActuel();
            sc.setListDouble();
            GrapheOriente g = new GrapheOriente(sc.getVendeurListDouble(), sc.getAcheteurListDouble());
            sc.setTrieTopologiqueSimple(g.trieTopologique());
            sc.setTrieTopologiqueGlouton(g.trieTopologiqueGlouton());
            int numsc = Integer.parseInt(nomScenario.substring(nomScenario.indexOf(" ") + 1));
            if (numsc < 4 || numsc > 8) {
                sc.setTrieTopologiqueOptimal(g.trieTopologiqueOptimal(6));
                Integer nbChemin = HBoxRoot.getAffichageOptiGestion().cbNbCheminOptimal.getValue();
                if (sc.getTrieTopologiqueOptimal().isEmpty())
                    HBoxRoot.getAffichageOptiGestion().majCheminOptimal("Pas de chemins");
                else {
                    StringBuilder cheminsOptimaux = new StringBuilder();
                    for (int i = 0; i < nbChemin; i++) {
                        List<String> cheminAffiche = sc.getTrieTopologiqueOptimal().get(i);
                        cheminsOptimaux.append("Vélizy, ");
                        cheminsOptimaux.append(cheminAffiche.toString().substring(1, cheminAffiche.toString().length() - 1));
                        cheminsOptimaux.append(", Vélizy");
                        cheminsOptimaux.append("\n");
                        int ibis = i + 1;
                        cheminsOptimaux.append("Longeur du chemin ").append(ibis).append(" en kilomètre: ")
                                .append(sc.getTrieTopologiqueOptimalLongueur().get(i)).append("\n").append("\n");
                    }
                    HBoxRoot.getAffichageOptiGestion().majCheminOptimal(cheminsOptimaux.toString());
                }
            } else {
                HBoxRoot.getAffichageOptiGestion().majCheminOptimal("Données non renseignées");
            }
            if (! sc.getTrieTopologiqueSimple().isEmpty()) {
                HBoxRoot.getAffichageChemin().majCheminSimple("Vélizy, " +
                        sc.getTrieTopologiqueSimple().toString().substring(1, sc.getTrieTopologiqueSimple().toString().length() - 1)
                        + ", Vélizy" + "\n\n" + "Distance de parcours en kilomètre: " +
                        sc.getTrieTopologiqueSimpleLongueur().toString());
            } else
                HBoxRoot.getAffichageChemin().majCheminSimple("Pas de chemins");
            if (! sc.getTrieTopologiqueGlouton().isEmpty()) {
                HBoxRoot.getAffichageChemin().majCheminHeuristique("Vélizy, " +
                        sc.getTrieTopologiqueGlouton().toString().substring(1, sc.getTrieTopologiqueGlouton().toString().length() - 1)
                        + ", Vélizy" + "\n\n" + "Distance de parcours en kilomètre: " +
                        sc.getTrieTopologiqueGloutonLongueur().toString());
            } else
                HBoxRoot.getAffichageChemin().majCheminHeuristique("Pas de chemins");
        } catch (FileNotFoundException e) {
            System.err.println("Erreur lors du calcul des chemins : " + e.getMessage());
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

            appliquerCSS(alert);

            alert.showAndWait();
        }
    }

    /**
     * Applique le css a l'alerte en paramètre
     *
     * @param alert alerte dont on veut appliquer le CSS
     */
    private static void appliquerCSS(Alert alert) {
        alert.getDialogPane().setId("alert-dialog");
        alert.getDialogPane().lookup(".header-panel").setId("alert-title");
        alert.getDialogPane().lookup(".content.label").setId("alert-content");
        File css = new File("css" + File.separator + "style.css");
        alert.getDialogPane().getStylesheets().add(css.toURI().toString());
    }

    /**
     * Affiche une fenêtre de confirmation avant de quitter l'application
     *
     * @param event événement
     */
    public void handleWindowClose(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application");
        alert.setHeaderText("Êtes-vous certain de vouloir quitter ?");
        alert.setContentText("Toutes les données non sauvegardées seront perdues.");

        appliquerCSS(alert);

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
        if (nomScenario.endsWith(".txt")) {
            return nomScenario.replace(" ", "_");
        }
        return nomScenario.replace(" ", "_") + ".txt";
    }

    /**
     * Fait le contraire de toNomFichier
     *
     * @param nomScenario Une chaine de character avec des espaces
     * @return Une chaine de character du nom du scénario
     */
    private static String toNotNomFichier(String nomScenario) {
        if (nomScenario.endsWith(".txt")) {
            return nomScenario.substring(0, nomScenario.length() - 4).replace("_", " ");
        }
        return nomScenario.replace("_", " ");
    }

    /**
     * Crée un nouveau scénario et affiche un message de confirmation
     *
     * @param event événement qui déclenche la méthode
     */
    public void creerNouveauScenario(ActionEvent event) {
        try {
            String nomNouveauFichier = Scenario.creerScenario();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nouveau scénario créé");
            alert.setHeaderText("Scénario créé avec succès !");
            alert.setContentText("Le nouveau scénario a été créé sous le nom : " + nomNouveauFichier);

            appliquerCSS(alert);

            HBoxRoot.setMenuScenario(nomNouveauFichier, HBoxRoot.menuScenario);

            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de création");
            alert.setHeaderText("Impossible de créer le nouveau scénario");
            alert.setContentText("Erreur : " + e.getMessage());

            appliquerCSS(alert);

            alert.showAndWait();
        }
    }

    /**
     * Ouvre une fenêtre de dialogue pour ajouter une transaction et l'ajoute au scénario actuel
     * Met à jour les chemins une fois l'ajout fait
     *
     * @param event événement qui déclenche la méthode
     */
    public void ajouterTransaction(ActionEvent event) {
        if (HBoxRoot.getScenarioActuel() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun scénario sélectionné");
            alert.setHeaderText("Impossible d'ajouter une transaction");
            alert.setContentText("Veuillez d'abord sélectionner un scénario.");

            appliquerCSS(alert);

            alert.showAndWait();
            return;
        }

        Stage parentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        DialogueAjouterTransaction dialogue = new DialogueAjouterTransaction(parentStage);
        String[] resultat = dialogue.afficherDialogue();

        if (resultat != null) {
            try {
                String vendeur = resultat[0];
                String acheteur = resultat[1];

                HBoxRoot.getScenarioActuel().ajouterVente(vendeur, acheteur);
                Scenario scenario = HBoxRoot.getScenarioActuel();
                String nomFichier = scenario.fichierScenario.getName();
                chargerScenario(nomFichier);
                File scFile = new File("scenario" + File.separator + toNomFichier(nomFichier));
                majChemins(scFile, toNotNomFichier(nomFichier));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible d'ajouter la transaction");
                alert.setContentText("Erreur : " + e.getMessage());

                appliquerCSS(alert);

                alert.showAndWait();
            }
        }
    }

    /**
     * Ouvre une fenêtre de dialogue pour supprimer une transaction du scénario actuel
     * Mettre à jour les chemins à jour une fois la suppression faite
     *
     * @param event événement qui déclenche la méthode
     */
    public void supprimerTransaction(ActionEvent event) {
        if (HBoxRoot.getScenarioActuel() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun scénario sélectionné");
            alert.setHeaderText("Impossible de supprimer une transaction");
            alert.setContentText("Veuillez d'abord sélectionner un scénario.");

            appliquerCSS(alert);

            alert.showAndWait();
            return;
        }

        Scenario scenario = HBoxRoot.getScenarioActuel();
        if (! DialogueSupprimerTransaction.aDesTransactions(scenario)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucune transaction");
            alert.setHeaderText("Aucune transaction à supprimer");
            alert.setContentText("Le scénario actuel ne contient aucune transaction.");

            appliquerCSS(alert);

            alert.showAndWait();
        } else {
            Stage parentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            // Créer et afficher le dialogue
            DialogueSupprimerTransaction dialogue = new DialogueSupprimerTransaction(parentStage, scenario);
            Integer numeroLigne = dialogue.afficherDialogue();

            if (numeroLigne != null) {
                try {
                    scenario.supprimerVente(numeroLigne);

                    String nomFichier = scenario.fichierScenario.getName();
                    chargerScenario(nomFichier);
                    File scFile = new File("scenario" + File.separator + nomFichier);
                    majChemins(scFile, toNotNomFichier(nomFichier));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
