module com.example.sae_appli {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sae_appli to javafx.fxml;
    exports com.example.sae_appli;
    exports modele;
    exports vue;
    exports controleur;
}