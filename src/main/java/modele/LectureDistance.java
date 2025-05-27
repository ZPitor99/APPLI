package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectureDistance {

    public static List<String> VILLES;

    static {
        try {
            VILLES = setVilles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List<Integer>> DISTANCES_VILLES;

    static {
        try {
            DISTANCES_VILLES = setDistances();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LectureDistance() {
    }

    /**
     * Donne la liste des villes lue dans le fichier /donnees/distances.txt
     * @return une liste de ville
     * @throws FileNotFoundException
     */
    private static ArrayList<String> setVilles() throws FileNotFoundException {
        ArrayList<String> villeList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("donnees" + File.separator + "distances.txt"));
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            villeList.add(scannerLine.next());
        }
        scanner.close();
        return villeList;
    }

    /**
     * Donne une liste de liste (matrice) qui correspond aux distances entre chaque ville
     * depuis le fichier /donnees/distances.txt
     *
     * @return Une liste de liste des distances entre villes
     * @throws FileNotFoundException
     */
    private static List<List<Integer>> setDistances() throws FileNotFoundException {
        List<List<Integer>> distancesList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("donnees" + File.separator + "distances.txt"));
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            scannerLine.next();

            List<Integer> distance = new ArrayList<>();
            while (scannerLine.hasNext()) {
                distance.add(Integer.valueOf(scannerLine.next()));
            }
            distancesList.add(distance);
            scannerLine.close();
        }
        scanner.close();
        return distancesList;
    }

    /**
     * Donne la liste de liste des données des distances entre villes
     *
     * @return double arrayList des distances entre les villes
     */
    public List<List<Integer>> getDistancesVilles() {
        return DISTANCES_VILLES;
    }

    /**
     * Donne la liste des villes
     *
     * @return une arrayList des villes
     */
    public List<String> getVilles() {
        return VILLES;
    }

    @Override
    /**
     * toString généré par IntelliJ
     */
    public String toString() {
        return "LectureDistance{" +
                "villes=" + VILLES +
                ",\ndistancesVilles=" + DISTANCES_VILLES +
                '}';
    }

    /**
     * Donne le nom de la ville à l'indice donné
     *
     * @param index l'indice de la ville dans la liste des villes
     * @return Le Nom de la ville à cet indice
     */
    public String getVille(int index) {
        return getVilles().get(index);
    }

    /**
     * Donne la liste de la distance d'une ville grâce à son indice
     *
     * @param index l'indice de la ville dans la liste des villes
     * @return une arrayList des distances de la ville correspondante
     */
    public List<Integer> getDistancesVille(int index) {
        return getDistancesVilles().get(index);
    }

    /**
     * Donne la distance entre deux villes
     *
     * @param villeDepart indice de la ville de départ dans la liste des villes
     * @param villeArrive indice de la ville de départ dans la liste des villes
     * @return la distance entre ces deux villes
     */
    public Integer DistanceVille(int villeDepart, int villeArrive) {
        return getDistancesVilles().get(villeDepart).get(villeArrive);
    }

    /**
     * @param villeDepart nom de la ville de départ dans la liste des villes
     * @param villeArrive nom de la ville de départ dans la liste des villes
     * @return la distance entre ces deux villes
     */
    public Integer DistanceVille(String villeDepart, String villeArrive) {
        int indexDepart = getVilles().indexOf(villeDepart);
        int indexArrive = getVilles().indexOf(villeArrive);

        return DistanceVille(indexDepart, indexArrive);
    }

    /**
     * Affiche dans le terminal sous forme de matrice les distance entre les villes
     */
    public void afficherMatrice() {
        System.out.println("Villes : " + VILLES);
        System.out.println("Matrice des distances :");
        for (int i = 0; i < VILLES.size(); i++) {
            System.out.println(VILLES.get(i) + " : " + DISTANCES_VILLES.get(i));
        }
    }
}
