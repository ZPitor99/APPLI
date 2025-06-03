package vue;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import static modele.ConstanteVue.LIST_NB_CHEMINS;

public class AffichageOptiGestion extends VBox {

    public Text tCheminOptimal;
    public ComboBox<Integer> cbNbCheminOptimal;

    public AffichageOptiGestion() {
        super(10);

        Label cheminOptimal = new Label("Chemin Optimal");
        cheminOptimal.setId("titreChemin");

        cbNbCheminOptimal = peupleComboBox();
        cbNbCheminOptimal.getSelectionModel().selectFirst();
        cbNbCheminOptimal.setId("cbNbChemin");
        cbNbCheminOptimal.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && ! newValue.equals(oldValue)) {
                cbNbCheminOptimal.addEventHandler(ActionEvent.ACTION, HBoxRoot.getControleur());
            }
        });

        TextFlow tfCheminOptimal = new TextFlow();
        tfCheminOptimal.setPrefWidth(400);
        tfCheminOptimal.setMaxWidth(400);
        tfCheminOptimal.setMinWidth(400);
        tfCheminOptimal.setPrefHeight(200);
        tfCheminOptimal.setMaxHeight(200);
        tfCheminOptimal.setMinHeight(200);
        tCheminOptimal = new Text("Aucun scénario sélectionné");
        tCheminOptimal.setId("contenuChemin");
        tfCheminOptimal.getChildren().add(tCheminOptimal);

        getChildren().addAll(cheminOptimal, cbNbCheminOptimal, tfCheminOptimal);

    }

    public void majCheminOptimal(String cheminOptimal) {
        tCheminOptimal.setText(cheminOptimal);
    }

    /**
     * Permet de peupler la comboBox à partir d'une lite d'integer
     *
     * @return la comboBox peuplé grâce à l'argument
     */
    private ComboBox<Integer> peupleComboBox() {
        ComboBox<Integer> comboBox = new ComboBox<>();
        for (Integer s : LIST_NB_CHEMINS) {
            comboBox.getItems().add(s);
        }
        return comboBox;
    }
}
