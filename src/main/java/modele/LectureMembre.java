package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LectureMembre {

    public Map<String, List<String>> villeMembres = new TreeMap<String, List<String>>();

    public LectureMembre() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("donnees" + File.separator + "membres_APPLI.txt"));
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");

            String membre = scannerLine.next();
            String ville = scannerLine.next();

            if (! villeMembres.containsKey(ville)) {
                villeMembres.put(ville, new ArrayList<>());
            }
            villeMembres.get(ville).add(membre);
            scannerLine.close();
        }
        scanner.close();
    }

    public Map<String, List<String>> getVilleMembres() {
        return villeMembres;
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : villeMembres.entrySet()) {
            affichage.append(entry.getKey()).append('\n').append(entry.getValue()).append('\n');
        }
        return affichage.toString();
    }
}
