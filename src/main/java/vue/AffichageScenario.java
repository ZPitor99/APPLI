package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.ScenarioTableItem;

public class AffichageScenario extends VBox {
    public Label numScenario = new Label("_Scenario");
    public TableView<ScenarioTableItem> tableDuScenarios = new TableView<>();

    public AffichageScenario() {
        super(10);

        numScenario.setMnemonicParsing(true);
        this.getChildren().addAll(numScenario);

        TableColumn<ScenarioTableItem, String> scenarioVendeurs = new TableColumn<>("Vendeurs");
        TableColumn<ScenarioTableItem, String> scenarioAcheteurs = new TableColumn<>("Acheteurs");

        // Définir les largeurs des colonnes
        scenarioVendeurs.setPrefWidth(150);
        scenarioVendeurs.setMinWidth(150);
        scenarioVendeurs.setMaxWidth(150);

        scenarioAcheteurs.setPrefWidth(150);
        scenarioAcheteurs.setMinWidth(150);
        scenarioAcheteurs.setMaxWidth(150);

        scenarioVendeurs.setCellValueFactory(new PropertyValueFactory<>("vendeur"));
        scenarioVendeurs.setReorderable(Boolean.FALSE);
        scenarioVendeurs.setSortable(Boolean.FALSE);

        scenarioAcheteurs.setCellValueFactory(new PropertyValueFactory<>("acheteur"));
        scenarioAcheteurs.setReorderable(Boolean.FALSE);
        scenarioAcheteurs.setSortable(Boolean.FALSE);

        // Permettre à la table de s'agrandir avec la fenêtre
        VBox.setVgrow(tableDuScenarios, Priority.ALWAYS);

        // Ajout et affichage
        tableDuScenarios.getColumns().addAll(scenarioVendeurs, scenarioAcheteurs);
        tableDuScenarios.setPrefSize(300, 200);
        tableDuScenarios.setEditable(false);
        this.getChildren().addAll(tableDuScenarios);
    }
}
