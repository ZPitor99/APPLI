package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConstanteVue {

    public static final List<String> LIST_NOM_SCENARIO = nomScenario();
    public static final List<Integer> LIST_NB_CHEMINS = nbChemins();

    private ConstanteVue() {}

    private static List<String> nomScenario() {
        List<String> nomScenario = new ArrayList<>();
        for (File fichierScenario : Objects.requireNonNull(new File("scenario").listFiles())) {
            nomScenario.add(fichierScenario.getName());
        }
        return nomScenario;
    }

    private static List<Integer> nbChemins() {
        List<Integer> nbChemins = new ArrayList<>();
        for (int i = 1; i < 6; i++){
            nbChemins.add(i);
        }
        return nbChemins;
    }


}
