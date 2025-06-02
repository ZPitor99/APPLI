package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AffichageChemin extends VBox {
    public Text tCheminSimple;
    public Text tCheminHeuristique;

    public AffichageChemin() {
        super(10);

        Label cheminTitre = new Label("Chemins");
        cheminTitre.setId("titrePartie");

        Label cheminSimple = new Label("Chemin Simple");
        cheminSimple.setId("titreChemin");
        TextFlow tfCheminSimple = new TextFlow();
        tfCheminSimple.setPrefWidth(400);
        tfCheminSimple.setMaxWidth(400);
        tfCheminSimple.setMinWidth(400);
        tfCheminSimple.setPrefHeight(200);
        tfCheminSimple.setMaxHeight(200);
        tfCheminSimple.setMinHeight(200);
        tCheminSimple = new Text("Aucun scénario sélectionné");
        tCheminSimple.setId("contenuChemin");
        tfCheminSimple.getChildren().add(tCheminSimple);

        Label cheminHeuristique = new Label("Chemin Heuristique");
        cheminHeuristique.setId("titreChemin");
        TextFlow tfCheminHeuristique = new TextFlow();
        tfCheminHeuristique.setPrefWidth(400);
        tfCheminHeuristique.setMaxWidth(400);
        tfCheminHeuristique.setMinWidth(400);
        tfCheminHeuristique.setPrefHeight(200);
        tfCheminHeuristique.setMaxHeight(200);
        tfCheminHeuristique.setMinHeight(200);
        tCheminHeuristique = new Text("Aucun scénario sélectionné");
        tCheminHeuristique.setId("contenuChemin");
        tfCheminHeuristique.getChildren().add(tCheminHeuristique);

        this.getChildren().addAll(cheminTitre, cheminSimple, tfCheminSimple, cheminHeuristique, tfCheminHeuristique);
    }

    public void majCheminSimple(String cheminSimple) {
        tCheminSimple.setText(cheminSimple);
    }

    public void majCheminHeuristique(String cheminHeuristique) {
        tCheminHeuristique.setText(cheminHeuristique);
    }
}
