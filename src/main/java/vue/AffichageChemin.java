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
        cheminTitre.setStyle("-fx-font-weight: bold");

        Label cheminSimple = new Label("Chemin Simple");
        TextFlow tfCheminSimple = new TextFlow();
        tfCheminSimple.setPrefWidth(200);
        tCheminSimple = new Text("Vélizy");
        tfCheminSimple.getChildren().add(tCheminSimple);

        Label cheminHeuristique = new Label("Chemin Heuristique");
        TextFlow tfCheminHeuristique = new TextFlow();
        tfCheminSimple.setPrefWidth(200);
        tCheminHeuristique = new Text("Vélizy");
        tfCheminHeuristique.getChildren().add(tCheminHeuristique);

        Label cheminOptimal = new Label("Chemin Optimal");
        TextFlow tfCheminOptimal = new TextFlow();
        tfCheminSimple.setPrefWidth(200);
        tCheminOptimal = new Text("Vélizy");
        tfCheminOptimal.getChildren().add(tCheminOptimal);

        this.getChildren().addAll(cheminTitre, cheminSimple, tfCheminSimple, cheminHeuristique, tfCheminHeuristique, cheminOptimal, tfCheminOptimal);

    }
}
