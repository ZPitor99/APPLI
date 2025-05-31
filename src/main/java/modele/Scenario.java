package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static modele.LectureDistance.DISTANCES_VILLES;
import static modele.LectureDistance.VILLES;
import static modele.LectureMembre.VILLES_MEMBRES;

public class Scenario {
    public List<String> vendeurList = new ArrayList<>();
    public List<String> acheteurList = new ArrayList<>();

    public List<String> vendeurListDouble;
    public List<String> acheteurListDouble;

    public List<String> trieTopologiqueSimple;
    public Integer trieTopologiqueSimpleLongueur;

    public List<String> trieTopologiqueGlouton;
    public Integer trieTopologiqueGloutonLongueur;

    public List<String> trieTopologiqueOptimal;
    public Integer trieTopologiqueOptimalLongueur;

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

    /**
     * Accesseur de VendeurList
     *
     * @return la liste des vendeurs du scénario
     */
    public List<String> getVendeurList() {
        return vendeurList;
    }

    /**
     * Accesseur de AcheteurList
     *
     * @return la liste des acheteurs du scénario
     */
    public List<String> getAcheteurList() {
        return acheteurList;
    }

    /**
     * Double les sommets du graphe pour éviter les cycles
     * Remplie les champs %Double de la classe
     */
    public void setListDouble() {
        vendeurListDouble = new ArrayList<>();
        acheteurListDouble = new ArrayList<>();
        vendeurListDouble = transformationVille(vendeurList, true);
        acheteurListDouble = transformationVille(acheteurList, false);
    }

    public ArrayList<String> getTrieTopologiqueSimple() {
        return getCheminToString(trieTopologiqueSimple);
    }

    public Integer getTrieTopologiqueSimpleLongueur() {
        return trieTopologiqueSimpleLongueur;
    }

    public ArrayList<String> getTrieTopologiqueGlouton() {
        return getCheminToString(trieTopologiqueGlouton);
    }

    public Integer getTrieTopologiqueGloutonLongueur() {
        return trieTopologiqueGloutonLongueur;
    }

    /**
     * Transforme un chemin de string avec un caractère supplémentaire en un chemin de ville
     * sans caractère supplémentaire et en fusionnant en un deux villes consécutives avec caractère supplémentaire
     *
     * @param chemin Un chemin de ville avec des villes+ et villes-
     * @return la liste des villes transformées
     */
    private ArrayList<String> getCheminToString(List<String> chemin) {
        ArrayList<String> cheminVilles = new ArrayList<>();
        cheminVilles.add(chemin.getFirst().substring(0, chemin.getFirst().length() - 1));
        for (int i = 1; i < chemin.size(); i++) {
            if (!cheminVilles.getLast().equals(chemin.get(i).substring(0, chemin.get(i).length() - 1))) {
                cheminVilles.add(chemin.get(i).substring(0, chemin.get(i).length() - 1));
            }
        }
        return cheminVilles;
    }

    /**
     * Donne à partir d'une liste de la liste des villes dans les quel se trouvent chacun des membres de la liste
     * Exemple : Transforme [Bulbizarre, Herbizarre, Florizarre, Salamèche]
     * En : [Brest+, Perpignan+, Brest+, Lille+] si mettrePlus est à true
     * Sinon en : [Brest-, Perpignan-, Brest-, Lille-]
     *
     * @param membres    Liste des membres à transformer
     * @param mettrePlus Boolean qui indique si le signe est "+" ou "-"
     * @return La Liste des membres transformés en ville avec le symbole adéquate
     */
    private List<String> transformationVille(List<String> membres, boolean mettrePlus) {
        List<String> villes = new ArrayList<>();
        String ajout;
        if (mettrePlus) {
            ajout = "+";
        } else
            ajout = "-";

        for (String s : membres) {
            villes.add(VILLES_MEMBRES.get(s) + ajout);
        }
        return villes;
    }

    /**
     * Accesseur de VendeurListDouble
     *
     * @return une liste de villes doublées
     */
    public List<String> getVendeurListDouble() {
        return vendeurListDouble;
    }

    /**
     * Accesseur de AcheteurListDouble
     *
     * @return une liste de villes doublées
     */
    public List<String> getAcheteurListDouble() {
        return acheteurListDouble;
    }

    /**
     * Affecte aux champs correspondant le chemin topologique et la longueur de ce chemin
     * (Champ trieTopologiqueSimple et trieTopologiqueSimpleLongueur)
     *
     * @param chemin Le chemin topologique calculé de manière simple
     */
    public void setTrieTopologiqueSimple(List<String> chemin) {
        trieTopologiqueSimple = chemin;
        trieTopologiqueSimpleLongueur = longeurChemin(trieTopologiqueSimple);
    }

    /**
     * Affecte aux champs correspondant le chemin topologique et la longueur de ce chemin
     * (Champ trieTopologiqueGlouton et trieTopologiqueGloutonLongueur)
     *
     * @param chemin Le chemin topologique calculé de manière gloutonne
     */
    public void setTrieTopologiqueGlouton(List<String> chemin) {
        trieTopologiqueGlouton = chemin;
        trieTopologiqueGloutonLongueur = longeurChemin(trieTopologiqueGlouton);
    }

    /**
     * Donne la longueur du chemin à partir des constantes DISTANCESVILLES et VILLES
     *
     * @param chemin Une liste de villes
     * @return la somme des longueurs entre chaque ville de la liste et ajoute la distance entre Vélizy et la ville de départ et d'arrivé
     */
    private Integer longeurChemin(List<String> chemin) {
        Integer longueur = 0;
        longueur += DISTANCES_VILLES.get(VILLES.get("Velizy")).get(VILLES.get(chemin.getFirst().substring(0, chemin.getFirst().length() - 1)));
        for (int i = 0; i < chemin.size() - 1; i++) {
            longueur += DISTANCES_VILLES.get(VILLES.get(chemin.get(i).substring(0, chemin.get(i).length() - 1))).get(VILLES.get(chemin.get(i + 1).substring(0, chemin.get(i + 1).length() - 1)));
        }
        longueur += DISTANCES_VILLES.get(VILLES.get(chemin.getLast().substring(0, chemin.getLast().length() - 1))).get(VILLES.get("Velizy"));
        System.out.println(longueur);
        return longueur;
    }
}
