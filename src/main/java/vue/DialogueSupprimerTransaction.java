package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Scenario;

import java.util.Optional;

public class DialogueSupprimerTransaction extends Dialog<Integer> {

    private ListView<String> listTransaction;

    public DialogueSupprimerTransaction(Stage parent, Scenario scenario) {

        initOwner(parent);
        // BLOQUE INTERACTION AVEC PARENT
        initModality(Modality.WINDOW_MODAL);
        setTitle("Ajouter Transaction");
        setHeaderText("Sélectionnez la transaction à supprimer");

        ButtonType buttonOK = new ButtonType("Supprimer", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(buttonOK, buttonCancel);

        listTransaction = new ListView<>();
        listTransaction.setPrefHeight(200);
        listTransaction.setPrefHeight(400);

        ObservableList<String> items = FXCollections.observableArrayList();
        if (scenario != null && ! scenario.getVendeurList().isEmpty()) {
            for (int i = 0; i < scenario.getVendeurList().size(); i++) {
                String transaction = (i + 1) + ". " + scenario.getVendeurList().get(i) +
                        " → " + scenario.getAcheteurList().get(i);
                items.add(transaction);
            }
        }
        listTransaction.setItems(items);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        if (items.isEmpty()) {
            Label labelVide = new Label("Aucune transaction disponible pour suppression");
            labelVide.setStyle("-fx-font-style: italic; -fx-text-fill: gray;");
            grid.add(labelVide, 0, 0);
        } else {
            Label labelInstruction = new Label("Transactions existantes :");
            grid.add(labelInstruction, 0, 0);
            grid.add(listTransaction, 0, 1);
        }

        getDialogPane().setContent(grid);

        Button okButton = (Button) getDialogPane().lookupButton(buttonOK);
        okButton.setDisable(items.isEmpty());

        if (! items.isEmpty()) {
            listTransaction.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldSelection, newSelection) -> {
                        okButton.setDisable(newSelection == null);
                    }
            );
            listTransaction.getSelectionModel().selectFirst();
        }

        setResultConverter(dialogButton -> {
            if (dialogButton == buttonOK && listTransaction.getSelectionModel().getSelectedIndex() != - 1) {
                return listTransaction.getSelectionModel().getSelectedIndex();
            }
            return null;
        });
    }

    /**
     * Affiche le dialogue et retourne le numéro de ligne à supprimer
     *
     * @return Le numéro de ligne à supprimer (basé sur 1) ou null si annulé
     */
    public Integer afficherDialogue() {
        Optional<Integer> result = showAndWait();
        return result.orElse(null);
    }

    /**
     * Vérifie si le scénario a des transactions à supprimer
     *
     * @param scenario Le scénario à vérifier
     * @return true si le scénario a des transactions, false sinon
     */
    public static boolean aDesTransactions(Scenario scenario) {
        return scenario != null && scenario.getVendeurList() != null && ! scenario.getVendeurList().isEmpty();
    }
}
