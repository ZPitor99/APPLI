package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LectureMembre {

    public static Map<String, String> villeMembres;

    static {
        try {
            villeMembres = setVilleMembres();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LectureMembre(){}

    private static TreeMap<String, String> setVilleMembres() throws FileNotFoundException {
        TreeMap<String, String> mv = new TreeMap<String, String>();
        Scanner scanner = new Scanner(new File("donnees" + File.separator + "membres_APPLI.txt"));
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");

            String membre = scannerLine.next();
            String ville = scannerLine.next();

            mv.put(membre, ville);
            scannerLine.close();
        }
        scanner.close();
        return mv;
    }

    public Map<String, String> getVilleMembres() {
        return villeMembres;
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        for (Map.Entry<String, String> entry : villeMembres.entrySet()) {
            affichage.append(entry.getKey()).append('-').append(entry.getValue()).append('\n');
        }
        return affichage.toString();
    }
}
