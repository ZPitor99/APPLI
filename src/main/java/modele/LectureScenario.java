package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectureScenario {

    public static Scenario lectureScenario(File fichier) throws IOException {
        List<String> vendeurs = new ArrayList<>();
        List<String> acheteurs = new ArrayList<>();
        Scanner scanner = new Scanner(fichier);
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            vendeurs.add(scannerLine.next());
            scannerLine.next();
            acheteurs.add(scannerLine.next());
            scannerLine.close();
        }
        scanner.close();
        return new Scenario(vendeurs, acheteurs);
    }
}