package modele;

import java.io.*;
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

    public List<List<String>> trieTopologiqueOptimal;
    public List<Integer> trieTopologiqueOptimalLongueur;

    public File fichierScenario;

    public Scenario(File fichier) throws FileNotFoundException {
        this.fichierScenario = fichier;
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
        if (trieTopologiqueSimple == null) {
            trieTopologiqueSimple = new ArrayList<>();
        }
        return getCheminToString(trieTopologiqueSimple);
    }

    public Integer getTrieTopologiqueSimpleLongueur() {
        return trieTopologiqueSimpleLongueur;
    }

    public ArrayList<String> getTrieTopologiqueGlouton() {
        if (trieTopologiqueGlouton == null) {
            trieTopologiqueGlouton = new ArrayList<>();
        }
        return getCheminToString(trieTopologiqueGlouton);
    }

    public Integer getTrieTopologiqueGloutonLongueur() {
        return trieTopologiqueGloutonLongueur;
    }

    public List<List<String>> getTrieTopologiqueOptimal() {
        ArrayList<List<String>> trieTopologiqueOptimalVille = new ArrayList<>();
        for (List<String> list : trieTopologiqueOptimal) {
            if (!list.isEmpty())
                trieTopologiqueOptimalVille.add(getCheminToString(list));
        }
        return trieTopologiqueOptimalVille;
    }

    public List<Integer> getTrieTopologiqueOptimalLongueur() {
        return trieTopologiqueOptimalLongueur;
    }

    /**
     * Transforme un chemin de string avec un caractère supplémentaire en un chemin de ville
     * sans caractère supplémentaire et en fusionnant en une seule, deux strings consécutive avec caractère supplémentaire
     *
     * @param chemin Une suite de string dont chaque string possède à la fin d'elle un caractère à supprimer
     * @return la liste des chaines de characters transformé
     */
    public static ArrayList<String> getCheminToString(List<String> chemin) {
        if (chemin == null || chemin.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<String> cheminVilles = new ArrayList<>();
        cheminVilles.add(chemin.getFirst().substring(0, chemin.getFirst().length() - 1));
        if (chemin.size() > 1) {
            for (int i = 1; i < chemin.size(); i++) {
                if (! cheminVilles.getLast().equals(chemin.get(i).substring(0, chemin.get(i).length() - 1))) {
                    cheminVilles.add(chemin.get(i).substring(0, chemin.get(i).length() - 1));
                }
            }
        }
        return cheminVilles;
    }

    /**
     * Donne à partir d'une liste de la liste des villes dans lesquelles se trouvent chacun des membres de la liste
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
     * Affecte aux champs correspondant les chemins topologiques et la longueur de ces chemins
     * (Champ trieTopologiqueOptimal et trieTopologiqueOptimal)
     *
     * @param chemins la liste de liste des k meilleurs chemins
     */
    public void setTrieTopologiqueOptimal(List<List<String>> chemins) {
        trieTopologiqueOptimal = chemins;
        ArrayList<Integer> cheminslongeur = new ArrayList<>();
        for (List<String> chemin : chemins) {
            cheminslongeur.add(longeurChemin(chemin));
        }
        trieTopologiqueOptimalLongueur = cheminslongeur;
    }

    /**
     * Donne la longueur du chemin à partir des constantes DISTANCESVILLES et VILLES
     *
     * @param chemin Une liste de villes
     * @return la somme des longueurs entre chaque ville de la liste et ajoute la distance entre Vélizy et la ville de départ et d'arrivé
     */
    public static Integer longeurChemin(List<String> chemin) {
        Integer longueur = 0;
        if (chemin == null || chemin.isEmpty()) {
            return longueur;
        }
        longueur += DISTANCES_VILLES.get(VILLES.get("Velizy")).get(VILLES.get(chemin.getFirst().substring(0, chemin.getFirst().length() - 1)));
        for (int i = 0; i < chemin.size() - 1; i++) {
            longueur += DISTANCES_VILLES.get(VILLES.get(chemin.get(i).substring(0, chemin.get(i).length() - 1))).get(VILLES.get(chemin.get(i + 1).substring(0, chemin.get(i + 1).length() - 1)));
        }
        longueur += DISTANCES_VILLES.get(VILLES.get(chemin.getLast().substring(0, chemin.getLast().length() - 1))).get(VILLES.get("Velizy"));
        return longueur;
    }

    /**
     * Ajoute une nouvelle vente au scénario courant et sauvegarde dans le fichier
     *
     * @param vendeur  Le nom du Pokémon vendeur
     * @param acheteur Le nom du Pokémon acheteur
     * @throws IOException si une erreur survient lors de l'écriture dans le fichier
     */
    public void ajouterVente(String vendeur, String acheteur) throws IOException {
        vendeurList.add(vendeur);
        acheteurList.add(acheteur);
        sauvegarderScenario();
    }

    /**
     * Supprime une vente à un numéro de ligne spécifique et sauvegarde dans le fichier
     *
     * @param numeroLigne Le numéro de la ligne à supprimer (commence à 1)
     * @throws IndexOutOfBoundsException si le numéro de ligne est invalide
     * @throws IOException               si une erreur survient lors de l'écriture dans le fichier
     */
    public void supprimerVente(int numeroLigne) throws IOException {
        if (numeroLigne < 1 || numeroLigne >= vendeurList.size()) {
            throw new IndexOutOfBoundsException("Le numéro de ligne " + numeroLigne + " est invalide");
        }
        vendeurList.remove(numeroLigne - 1);
        acheteurList.remove(numeroLigne - 1);
        sauvegarderScenario();
    }

    /**
     * Sauvegarde le scénario courant dans le fichier
     *
     * @throws IOException si une erreur survient lors de l'écriture dans le fichier
     */
    private void sauvegarderScenario() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierScenario))) {
            for (int i = 0; i < vendeurList.size(); i++) {
                writer.write(vendeurList.get(i) + " -> " + acheteurList.get(i));
                writer.newLine();
            }
        }
    }

    public static String creerScenario() throws IOException {
        String dossier = "scenario";
        File dossierFile = new File(dossier);

        int maxNum = trouverNumScenarioMax(dossierFile);
        maxNum++;

        String nomNouveauFichier = "scenario_" + maxNum + ".txt";
        File nouveauFile = new File(dossier + File.separator + nomNouveauFichier);
        if (nouveauFile.createNewFile()) {
            return nomNouveauFichier;
        } else {
            throw new IOException("Le nouveau fichier " + nomNouveauFichier + " est invalide");
        }
    }

    private static int trouverNumScenarioMax(File dossier) {
        int max = 0;

        File[] files = dossier.listFiles();
        if (files != null) {
            for (File f : files) {
                String nomFichier = f.getName();

                Scanner scanner = new Scanner(nomFichier);
                scanner.useDelimiter("_|\\.");

                try {
                    //Ignorer "scénario"
                    if (scanner.hasNext()) {
                        scanner.next();
                    }
                    if (scanner.hasNextInt()) {
                        int n = scanner.nextInt();
                        max = Math.max(max, n);
                    }

                } catch (Exception e) {
                    System.err.println(e.getMessage() + "\nErreur pour le fichier " + nomFichier);
                } finally {
                    scanner.close();
                }
            }
        }
        return max;
    }
}
