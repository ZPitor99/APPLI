//package modele;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class ExempleLectureScenario {
//    public static Senario lectureScenario (File fichier) throws IOException {
//        Scenario scenario = new Scenario();
//
//        Scanner scanner = new Scanner(fichier);
//        Scanner scannerLine;
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            scannerLine = new Scanner(line).useDelimiter(" ");
//            String vendeur = scannerLine.next();
//            scannerLine.next();
//            String acheteur = scannerLine.next();
//            System.out.println(vendeur + " " + acheteur);
//            scannerLine.close();
//        }
//        scanner.close();
//        return scenario();
//    }
//
//}