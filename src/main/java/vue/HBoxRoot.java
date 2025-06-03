package vue;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Scenario;

import static modele.ConstanteVue.LIST_NOM_SCENARIO;

public class HBoxRoot extends VBox {

    private static Controleur controleur = new Controleur();
    private static AffichageScenario affichageScenario = new AffichageScenario();
    private static AffichageChemin affichageChemin = new AffichageChemin();
    private static AffichageOptiGestion affichageOptiGestion = new AffichageOptiGestion();

    private static Scenario scenarioActuel = null;
    public static ToggleGroup groupeScenario = new ToggleGroup();
    public static Menu menuScenario = new Menu("_Scenario");

    public HBoxRoot() {
        super();
        this.setSpacing(40);

        //Bar de Menu
        MenuBar menuBar = new MenuBar();
        Menu menuFichier = new Menu("_Fichier");
        Menu menuGestion = new Menu("_Gestion");
        menuScenario.setMnemonicParsing(true);
        menuFichier.setMnemonicParsing(true);
        menuGestion.setMnemonicParsing(true);
        Platform.runLater(menuBar::requestFocus);

        for (String item : LIST_NOM_SCENARIO) {
            setMenuScenario(item, menuScenario);
        }
        ((RadioMenuItem) menuScenario.getItems().get(1)).setSelected(true);
        menuBar.getMenus().add(menuScenario);

        MenuItem newScenario = new MenuItem("_Nouveau Scenario");
        newScenario.setMnemonicParsing(true);
        newScenario.setOnAction(controleur::creerNouveauScenario);
        menuFichier.getItems().addAll(newScenario);
        menuBar.getMenus().add(menuFichier);

        MenuItem ajouterTransaction = new MenuItem("_Ajouter Transaction");
        MenuItem annulerTransaction = new MenuItem("_Annuler Transaction");
        ajouterTransaction.setMnemonicParsing(true);
        annulerTransaction.setMnemonicParsing(true);
        ajouterTransaction.setOnAction(controleur::ajouterTransaction);
        annulerTransaction.setOnAction(controleur::supprimerTransaction);
        menuGestion.getItems().addAll(ajouterTransaction, annulerTransaction);
        menuBar.getMenus().add(menuGestion);

        this.getChildren().add(menuBar);

        //Vues
        HBox main = new HBox(20);
        main.setSpacing(40);
        main.setPrefSize(500, 600);
        main.getChildren().addAll(affichageScenario, affichageChemin, affichageOptiGestion);
        this.getChildren().add(main);
    }

    public static void setMenuScenario(String item, Menu menuScenario) {
        RadioMenuItem menuItem = new RadioMenuItem(item.replace(".txt", "").replace("_", " "));
        menuItem.setUserData(item);
        menuScenario.getItems().add(menuItem);
        menuItem.setToggleGroup(groupeScenario);
        menuItem.addEventHandler(ActionEvent.ACTION, controleur);
    }

    public static Controleur getControleur() {
        return controleur;
    }

    public static AffichageScenario getAffichageScenario() {
        return affichageScenario;
    }

    public static AffichageChemin getAffichageChemin() {
        return affichageChemin;
    }

    public static AffichageOptiGestion getAffichageOptiGestion() {
        return affichageOptiGestion;
    }

    public static Scenario getScenarioActuel() {
        return scenarioActuel;
    }

    public static void setScenarioActuel(Scenario scenarioActuel) {
        HBoxRoot.scenarioActuel = scenarioActuel;
    }
}
