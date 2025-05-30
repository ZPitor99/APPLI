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

        numScenario.setId("titrePartie");
        numScenario.setMnemonicParsing(true);
        this.getChildren().addAll(numScenario);

        TableColumn<ScenarioTableItem, String> scenarioVendeurs = new TableColumn<>("Vendeurs");
        TableColumn<ScenarioTableItem, String> scenarioAcheteurs = new TableColumn<>("Acheteurs");

        // Définir les largeurs des colonnes
        scenarioVendeurs.setPrefWidth(160);
        scenarioVendeurs.setMinWidth(160);
        scenarioVendeurs.setMaxWidth(160);

        scenarioAcheteurs.setPrefWidth(160);
        scenarioAcheteurs.setMinWidth(160);
        scenarioAcheteurs.setMaxWidth(160);

        //Place des colonnes fixe, trie possible → praticité
        scenarioVendeurs.setCellValueFactory(new PropertyValueFactory<>("vendeur"));
        scenarioVendeurs.setReorderable(Boolean.FALSE);
        scenarioVendeurs.setSortable(Boolean.TRUE);

        scenarioAcheteurs.setCellValueFactory(new PropertyValueFactory<>("acheteur"));
        scenarioAcheteurs.setReorderable(Boolean.FALSE);
        scenarioAcheteurs.setSortable(Boolean.TRUE);

        // Permettre à la table de s'agrandir avec la fenêtre
        VBox.setVgrow(tableDuScenarios, Priority.ALWAYS);

        // Ajout et affichage
        tableDuScenarios.getColumns().addAll(scenarioVendeurs, scenarioAcheteurs);
        tableDuScenarios.setPrefSize(320, 200);
        tableDuScenarios.setEditable(false);
        this.getChildren().addAll(tableDuScenarios);
    }
}
