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
        tfCheminSimple.setPrefWidth(200);
        tCheminSimple = new Text("Vélizy");
        tCheminSimple.setId("contenuChemin");
        tfCheminSimple.getChildren().add(tCheminSimple);

        Label cheminHeuristique = new Label("Chemin Heuristique");
        cheminHeuristique.setId("titreChemin");
        TextFlow tfCheminHeuristique = new TextFlow();
        tfCheminSimple.setPrefWidth(200);
        tCheminHeuristique = new Text("Vélizy");
        tCheminHeuristique.setId("contenuChemin");
        tfCheminHeuristique.getChildren().add(tCheminHeuristique);

        Label cheminOptimal = new Label("Chemin Optimal");
        cheminOptimal.setId("titreChemin");
        TextFlow tfCheminOptimal = new TextFlow();
        tfCheminSimple.setPrefWidth(200);
        tCheminOptimal = new Text("Vélizy");
        tCheminOptimal.setId("contenuChemin");
        tfCheminOptimal.getChildren().add(tCheminOptimal);

        this.getChildren().addAll(cheminTitre, cheminSimple, tfCheminSimple, cheminHeuristique, tfCheminHeuristique, cheminOptimal, tfCheminOptimal);

    }
}
