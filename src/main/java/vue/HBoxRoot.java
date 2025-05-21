package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import static modele.ConstanteVue.LIST_NOM_SCENARIO;

public class HBoxRoot extends HBox {

    public HBoxRoot() {
        super();
        MenuBar menuBar = new MenuBar();
        Menu menuScenario = new Menu("Menu");
        ToggleGroup groupeScenario = new ToggleGroup();
        for (String item : LIST_NOM_SCENARIO) {
            RadioMenuItem menuItem = new RadioMenuItem(item);
            menuItem.setUserData(item);
            menuScenario.getItems().add(menuItem);
            menuItem.setToggleGroup(groupeScenario);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(menuItem.getUserData());
                }
            });
        }
        ((RadioMenuItem)menuScenario.getItems().getFirst()).setSelected(true);
        menuBar.getMenus().add(menuScenario);
        this.getChildren().add(menuBar);
    }
}
