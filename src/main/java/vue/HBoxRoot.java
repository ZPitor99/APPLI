package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import static modele.ConstanteVue.LIST_NOM_SCENARIO;

public class HBoxRoot extends HBox {

    private static Controleur controleur = new Controleur();

    public HBoxRoot() {
        super();

        //Bar de Menu
        MenuBar menuBar = new MenuBar();
        Menu menuScenario = new Menu("Scenario");
        ToggleGroup groupeScenario = new ToggleGroup();
        for (String item : LIST_NOM_SCENARIO) {
            RadioMenuItem menuItem = new RadioMenuItem(item.replace(".txt", "").replace("_", " "));
            menuItem.setUserData(item);
            menuScenario.getItems().add(menuItem);
            menuItem.setToggleGroup(groupeScenario);
            menuItem.addEventHandler(ActionEvent.ACTION, controleur);
        }
        ((RadioMenuItem) menuScenario.getItems().get(1)).setSelected(true);
        menuBar.getMenus().add(menuScenario);
        this.getChildren().add(menuBar);

        //Vues

    }

    public static Controleur getControleur() {
        return controleur;
    }
}
