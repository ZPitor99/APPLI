package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AffichageChemin extends VBox {
    public Text tCheminSimple;
    public Text tCheminHeuristique;
    public Text tCheminOptimal;

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
        tfCheminSimple.setPrefHeight(160);
        tfCheminSimple.setMaxHeight(160);
        tfCheminSimple.setMinHeight(160);
        tCheminSimple = new Text("Vélizy");
        tCheminSimple.setId("contenuChemin");
        tfCheminSimple.getChildren().add(tCheminSimple);

        Label cheminHeuristique = new Label("Chemin Heuristique");
        cheminHeuristique.setId("titreChemin");
        TextFlow tfCheminHeuristique = new TextFlow();
        tfCheminHeuristique.setPrefWidth(400);
        tfCheminHeuristique.setMaxWidth(400);
        tfCheminHeuristique.setMinWidth(400);
        tfCheminHeuristique.setPrefHeight(160);
        tfCheminHeuristique.setMaxHeight(160);
        tfCheminHeuristique.setMinHeight(160);
        tCheminHeuristique = new Text("Vélizy");
        tCheminHeuristique.setId("contenuChemin");
        tfCheminHeuristique.getChildren().add(tCheminHeuristique);

        Label cheminOptimal = new Label("Chemin Optimal");
        cheminOptimal.setId("titreChemin");
        TextFlow tfCheminOptimal = new TextFlow();
        tfCheminOptimal.setPrefWidth(400);
        tfCheminOptimal.setMaxWidth(400);
        tfCheminOptimal.setMinWidth(400);
        tfCheminOptimal.setPrefHeight(160);
        tfCheminOptimal.setMaxHeight(160);
        tfCheminOptimal.setMinHeight(160);
        tCheminOptimal = new Text("Vélizy");
        tCheminOptimal.setId("contenuChemin");
        tfCheminOptimal.getChildren().add(tCheminOptimal);

        this.getChildren().addAll(cheminTitre, cheminSimple, tfCheminSimple, cheminHeuristique, tfCheminHeuristique, cheminOptimal, tfCheminOptimal);
    }

    public void majCheminSimple(String cheminSimple) {
        tCheminSimple.setText(cheminSimple);
    }

    public void majCheminHeuristique(String cheminHeuristique) {
        tCheminHeuristique.setText(cheminHeuristique);
    }

    public void majCheminOptimal(String cheminOptimal) {
        tCheminOptimal.setText(cheminOptimal);
    }
}
