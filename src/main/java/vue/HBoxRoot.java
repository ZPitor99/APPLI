package vue;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import static modele.ConstanteVue.LIST_NOM_SCENARIO;

public class HBoxRoot extends HBox {

    private static Controleur controleur = new Controleur();
    private static AffichageScenario affichageScenario = new AffichageScenario();

    public HBoxRoot() {
        super();
        this.setSpacing(10);

        //Bar de Menu
        MenuBar menuBar = new MenuBar();
        Menu menuScenario = new Menu("_Scenario");
        menuScenario.setMnemonicParsing(true);
        Platform.runLater(menuBar::requestFocus);
        ToggleGroup groupeScenario = new ToggleGroup();
        for (String item : LIST_NOM_SCENARIO) {
            RadioMenuItem menuItem = new RadioMenuItem("_" + item.replace(".txt", "").replace("_", " "));
            menuItem.setMnemonicParsing(true);
            menuItem.setUserData(item);
            menuScenario.getItems().add(menuItem);
            menuItem.setToggleGroup(groupeScenario);
            menuItem.addEventHandler(ActionEvent.ACTION, controleur);
        }
        ((RadioMenuItem) menuScenario.getItems().get(1)).setSelected(true);
        menuBar.getMenus().add(menuScenario);
        this.getChildren().add(menuBar);

        //Vues
        this.getChildren().addAll(affichageScenario);
    }

    public static Controleur getControleur() {
        return controleur;
    }

    public static AffichageScenario getAffichageScenario() {
        return affichageScenario;
    }
}
