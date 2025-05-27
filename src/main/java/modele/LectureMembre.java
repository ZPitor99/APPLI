package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LectureMembre {

    public static Map<String, String> VILLES_MEMBRES;

    static {
        try {
            VILLES_MEMBRES = setVilleMembres();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LectureMembre() {
    }

    /**
     * Donne un dictionnaire qui à chaque membre associe sa ville de résidence
     * depuis le fichier /donnees/membres_APPLI.txt
     *
     * @return un dictionnaire nom du membre (key) et ville (value)
     * @throws FileNotFoundException
     */
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

    /**
     * Accesseur du champ villeMembres : le dictionnaire VilleMembres
     * @return un dictionnaire des membres et de leur ville de résidence
     */
    public Map<String, String> getVilleMembres() {
        return VILLES_MEMBRES;
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        for (Map.Entry<String, String> entry : VILLES_MEMBRES.entrySet()) {
            affichage.append(entry.getKey()).append('-').append(entry.getValue()).append('\n');
        }
        return affichage.toString();
    }
}
