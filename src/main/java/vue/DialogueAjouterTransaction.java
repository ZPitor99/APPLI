package vue;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.LectureMembre;

import java.util.ArrayList;
import java.util.List;

public class DialogueAjouterTransaction extends Dialog<String[]> {

    private ComboBox<String> comboBoxVendeur;
    private ComboBox<String> comboBoxAcheteur;

    public DialogueAjouterTransaction(Stage parent) {
        initOwner(parent);
        // BLOQUE INTERACTION AVEC PARENT
        initModality(Modality.WINDOW_MODAL);
        setTitle("Ajouter Transaction");
        setHeaderText("Selectionner le vendeur et l'acheteur");

        ButtonType buttonOK = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(buttonOK, buttonCancel);

        List<String> listMembre = new ArrayList<String>();
        listMembre.addAll(LectureMembre.VILLES_MEMBRES.keySet());

        comboBoxVendeur = new ComboBox<>(FXCollections.observableArrayList(listMembre));
        comboBoxAcheteur = new ComboBox<>(FXCollections.observableArrayList(listMembre));

        comboBoxAcheteur.setPromptText("Ajouter le acheteur");
        comboBoxVendeur.setPromptText("Ajouter le vendeur");

        Button okButton = (Button) getDialogPane().lookupButton(buttonOK);
        okButton.setDisable(true);

        // Listener pour activer/désactiver le bouton OK
        // Code pris en ligne
        //-----
        Runnable updateOkButton = () -> {
            okButton.setDisable(comboBoxVendeur.getValue() == null || comboBoxAcheteur.getValue() == null);
        };

        comboBoxVendeur.valueProperty().addListener((obs, oldVal, newVal) -> updateOkButton.run());
        comboBoxAcheteur.valueProperty().addListener((obs, oldVal, newVal) -> updateOkButton.run());
        //-----


        // Conversion résultat
        setResultConverter(dialogButton -> {
            if (dialogButton == buttonOK) {
                return new String[]{comboBoxVendeur.getValue(), comboBoxAcheteur.getValue()};
            }
            return null;
        });
    }
}
