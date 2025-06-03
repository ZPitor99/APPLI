package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConstanteVue {

    public static final List<String> LIST_NOM_SCENARIO = nomScenario();
    public static final List<Integer> LIST_NB_CHEMINS = nbChemins();

    private ConstanteVue() {}

    /**
     * Construit la liste des noms des scénarios à partir du dossier scenario
     *
     * @return la liste des noms des fichiers dans le dossier
     */
    private static List<String> nomScenario() {
        List<String> nomScenario = new ArrayList<>();
        for (File fichierScenario : Objects.requireNonNull(new File("scenario").listFiles())) {
            nomScenario.add(fichierScenario.getName());
        }
        return nomScenario;
    }

    /**
     * Créer une liste du nombre de chemins optimaux qui pourront être sélectionné
     * Permet de peupler la comboBox
     *
     * @return Une liste d'entier de 1 à 6
     */
    private static List<Integer> nbChemins() {
        List<Integer> nbChemins = new ArrayList<>();
        for (int i = 1; i < 6; i++){
            nbChemins.add(i);
        }
        return nbChemins;
    }


}
