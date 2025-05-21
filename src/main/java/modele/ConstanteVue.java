package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConstanteVue {

    public static final List<String> LIST_NOM_SCENARIO = nomScenario();

    private static List<String> nomScenario() {
        List<String> nomScenario = new ArrayList<>();
        for (File fichierScenario : Objects.requireNonNull(new File("scenario").listFiles())) {
            nomScenario.add(fichierScenario.getName().replace(".txt", "").replace("_", " "));
        }
        return nomScenario;
    }


}
