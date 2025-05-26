package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static modele.LectureMembre.villeMembres;

public class Scenario {
    public List<String> vendeurList = new ArrayList<>();
    public List<String> acheteurList = new ArrayList<>();
    public  List<String> vendeurListDouble;
    public  List<String> acheteurListDouble;

    public Scenario(File fichier) throws FileNotFoundException {
        Scanner scanner = new Scanner(fichier);
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            vendeurList.add(scannerLine.next());
            scannerLine.next();
            acheteurList.add(scannerLine.next());
            scannerLine.close();
        }
        scanner.close();
    }

    public Scenario(List<String> vendeurList, List<String> acheteurList) {
        this.vendeurList = vendeurList;
        this.acheteurList = acheteurList;
    }

    public String toString() {
        return vendeurList.toString() + "\n" + acheteurList.toString();
    }

    public List<String> getVendeurList() {
        return vendeurList;
    }

    public List<String> getAcheteurList() {
        return acheteurList;
    }

    public void setListDouble(){
        vendeurListDouble = new ArrayList<>();
        acheteurListDouble = new ArrayList<>();

        vendeurListDouble = transformationVille(vendeurList, true);
        acheteurListDouble = transformationVille(acheteurList, false);
        System.out.println(vendeurListDouble);
        System.out.println(acheteurListDouble);
    }

    private List<String> transformationVille(List<String> membres, boolean mettrePlus) {
        List<String> villes = new ArrayList<>();
        String ajout = "";
        if (mettrePlus) {
            ajout = "+";
        }
        else
            ajout = "-";

        for (String s : membres) {
            System.out.println(s);
            villes.add(villeMembres.get(s)+ajout);
        }
        return villes;
    }

    public List<String> getVendeurListDouble() {
        return vendeurListDouble;
    }

    public List<String> getAcheteurListDouble() {
        return acheteurListDouble;
    }
}
