package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectureDistance {

    public List<String> villes = new ArrayList<>();
    public List<List<Integer>> distancesVilles = new ArrayList<>();

     public LectureDistance() throws FileNotFoundException {
         Scanner scanner = new Scanner(new File("distance" + File.separator + "distances.txt"));
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

    public List<List<Integer>> getDistancesVilles() {
        return distancesVilles;
    }

    public List<String> getVilles() {
        return villes;
    }

    @Override
    public String toString() {
        return "LectureDistance{" +
                "villes=" + villes +
                ",\ndistancesVilles=" + distancesVilles +
                '}';
    }

    public String getVille(int index) {
         return getVilles().get(index);
    }

    public List<Integer> getDistancesVille(int index) {
         return getDistancesVilles().get(index);
    }

    public Integer DistanceVille(int villeDepart, int villeArrive) {
        return getDistancesVilles().get(villeDepart).get(villeArrive);
    }

    public Integer DistanceVille(String villeDepart, String villeArrive) {
         int indexDepart = getVilles().indexOf(villeDepart);
         int indexArrive = getVilles().indexOf(villeArrive);

         return DistanceVille(indexDepart, indexArrive);
    }

    public void afficherMatrice() {
        System.out.println("Villes : " + villes);
        System.out.println("Matrice des distances :");
        for (int i = 0; i < villes.size(); i++) {
            System.out.println(villes.get(i) + " : " + distancesVilles.get(i));
        }
    }
}
