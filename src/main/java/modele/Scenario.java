package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static modele.LectureDistance.distancesVilles;
import static modele.LectureDistance.villes;
import static modele.LectureMembre.villeMembres;

public class Scenario {
    public List<String> vendeurList = new ArrayList<>();
    public List<String> acheteurList = new ArrayList<>();

    public List<String> vendeurListDouble;
    public List<String> acheteurListDouble;

    public List<String> trieTopologique;

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

    public void setListDouble() {
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
        } else
            ajout = "-";

        for (String s : membres) {
            System.out.println(s);
            villes.add(villeMembres.get(s) + ajout);
        }
        return villes;
    }

    public List<String> getVendeurListDouble() {
        return vendeurListDouble;
    }

    public List<String> getAcheteurListDouble() {
        return acheteurListDouble;
    }

    public void setTrieTopologique(List<String> chemin) {
        trieTopologique = chemin;
        longeurChemin(trieTopologique);

    }

    private Integer longeurChemin(List<String> chemin) {
        Integer longueur = (Integer) 0;
        longueur += distancesVilles.get(villes.indexOf("Velizy")).get(villes.indexOf(chemin.getFirst().substring(0, chemin.getFirst().length() - 1)));
        for (int i = 0; i < chemin.size() - 1; i++) {
            longueur += distancesVilles.get(villes.indexOf(chemin.get(i).substring(0, chemin.get(i).length() - 1))).get(villes.indexOf(chemin.get(i + 1).substring(0, chemin.get(i + 1).length() - 1)));
        }
        longueur += distancesVilles.get(villes.indexOf(chemin.getLast().substring(0, chemin.getLast().length() - 1))).get(villes.indexOf("Velizy"));
        return longueur;
    }
}
