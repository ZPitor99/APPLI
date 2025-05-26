package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectureDistance {

    public static List<String> villes = new ArrayList<>();
    public static List<List<Integer>> distancesVilles = new ArrayList<>();

    public LectureDistance() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("donnees" + File.separator + "distances.txt"));
        Scanner scannerLine;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");

            villes.add(scannerLine.next());

            List<Integer> distance = new ArrayList<>();
            while (scannerLine.hasNext()) {
                distance.add(Integer.valueOf(scannerLine.next()));
            }
            distancesVilles.add(distance);
            scannerLine.close();
        }
        scanner.close();
    }

    /**
     * Donne la liste de liste des données des distances entre villes
     *
     * @return double arrayList des distances entre les villes
     */
    public List<List<Integer>> getDistancesVilles() {
        return distancesVilles;
    }

    /**
     * Donne la liste des villes
     *
     * @return une arrayList des villes
     */
    public List<String> getVilles() {
        return villes;
    }

    @Override
    /**
     * toString généré par IntelliJ
     */
    public String toString() {
        return "LectureDistance{" +
                "villes=" + villes +
                ",\ndistancesVilles=" + distancesVilles +
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
        System.out.println("Villes : " + villes);
        System.out.println("Matrice des distances :");
        for (int i = 0; i < villes.size(); i++) {
            System.out.println(villes.get(i) + " : " + distancesVilles.get(i));
        }
    }
}
