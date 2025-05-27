package modele;

import java.util.*;

public class GrapheOriente {
    TreeMap<String, Set<String>> voisinsSortant;

    public GrapheOriente(List<String> depart, List<String> arrive) {
        voisinsSortant = new TreeMap<>();
        if (! depart.isEmpty() && ! arrive.isEmpty()) {
            for (int i = 0; i < depart.size(); i++) {
                if (! voisinsSortant.containsKey(depart.get(i))) {
                    voisinsSortant.put(depart.get(i), new TreeSet<>());
                    voisinsSortant.get(depart.get(i)).add(arrive.get(i));
                } else {
                    voisinsSortant.get(depart.get(i)).add(arrive.get(i));
                }
                voisinsSortant.put(arrive.get(i), new TreeSet<>());
            }
        }
        System.out.println(voisinsSortant);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordre : ").append(this.ordre()).append("\n");
        sb.append("Taille : ").append(this.taille()).append("\n");
        sb.append("DegreMin : ").append(this.degreMinimal()).append("\n");
        sb.append("DegreMax : ").append(this.degreMaximal()).append("\n");
        for (String i : listeSommets()) {
            sb.append("sommet ").append(i).append(" degree=").append(degre(i)).append(" voisins sortant: ").append(voisinsSortant.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Donne la liste des sommets triée.
     *
     * @return Une arrayList des sommets du graphe
     */
    public List<String> listeSommets() {
        List<String> sommets = new ArrayList<>(voisinsSortant.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    /**
     * Donne le nombre de sommets du graphe
     *
     * @return Le nombre correspondant à l'ordre du graphe
     */
    public int ordre() {
        return this.listeSommets().size();
    }

    /**
     * Donne le nombre de voisins sortants du sommet
     *
     * @param sommet Le nom d'un sommet du gra^he
     * @return Le nombre correspondant au degré du sommet
     */
    public int degre(String sommet) {
        return voisinsSortant.get(sommet).size();
    }

    /**
     * Donne le nombre d'arêtes dans le graphe
     *
     * @return Le nombre correspondant à la taille du graphe
     */
    public int taille() {
        int taille = 0;
        for (String i : listeSommets()) {
            taille += this.degre(i);
        }
        return taille;
    }

    /**
     * Donne le degré minimal parmi les degrés des sommets du graphe
     *
     * @return Le degré minimal dans le graphe
     */
    public int degreMinimal() {
        int degreMin = 0;
        for (String i : listeSommets()) {
            if (degre(i) < degreMin)
                degreMin = degre(i);
        }
        return degreMin;
    }

    /**
     * Donne le degré maximal parmi les degrés des sommets du graphe
     *
     * @return Le degré maximal dans le graphe
     */
    public int degreMaximal() {
        int degreMax = 0;
        for (String i : listeSommets()) {
            if (degre(i) > degreMax)
                degreMax = degre(i);
        }
        return degreMax;
    }

    /**
     * Donne la liste de ville à parcourir pour effectuer le chemin topologique de manière ordonné
     * par nom de sommet alphabetic (algo du cours de graphe)
     *
     * @return une arraylist de ville constituant le chemin topologique
     */
    public ArrayList<String> trieTopologique() {
        // NUM
        ArrayList<String> num = new ArrayList<>();

        // VOISINS SORTANT
        // sommet+ (liste des sommets-)
        TreeMap<String, Set<String>> lvs = (TreeMap<String, Set<String>>) this.voisinsSortant.clone();

        // DEGRES ENTRANT
        // sommet+ et int
        TreeMap<String, Integer> e = new TreeMap<String, Integer>();
        e = this.getDegreEntrant();

        // SOMMETS SOURCES
        // sommet+
        TreeSet<String> s = new TreeSet<>();
        s = this.sommetsSources(e);

//        System.out.println( "------------ \n" + "e = " + e);
//        System.out.println("s = " + s);
//        System.out.println("lvs = " + lvs + "\n ------------");

        // PROGRAMME
        while (! s.isEmpty()) {
            String courant = s.pollFirst();
            for (String i : lvs.get(courant)) {

//                System.out.println("/" + courant);
//                System.out.println(i);
//                System.out.println("e="+e);
//                System.out.println("s="+s);
//                System.out.println(num);

                e.put(i, Integer.valueOf(e.get(i) - 1));
                if (e.get(i) == 0) {
                    s.add(i);
                }
            }
            num.add(courant);
        }
        System.out.println(num);
        return num;
    }

    private TreeMap<String, Integer> getDegreEntrant() {
        TreeMap<String, Integer> degreEntrant = new TreeMap<String, Integer>();
        for (String i : this.listeSommets()) {
            degreEntrant.put(i, Integer.valueOf(0));
            for (String j : this.listeSommets()) {
                if (this.voisinsSortant.get(j).contains(i)) {
                    degreEntrant.put(i, Integer.valueOf(degreEntrant.get(i) + 1));
                }
            }
        }
        return degreEntrant;
    }

    private TreeSet<String> sommetsSources(TreeMap<String, Integer> degreEntrant) {
        TreeSet<String> sommets = new TreeSet<>();
        for (String i : listeSommets()) {
            if (degreEntrant.get(i) == 0) {
                sommets.add(i);
            }
        }
        return sommets;
    }

    /**
     * Donne la liste de ville à parcourir pour effectuer le chemin topologique de manière gloutonne, c'est-à-dire
     * avec optimisation locale : prendre la ville la plus proche dans la liste des sources
     *
     * @return une arraylist de ville constituant le chemin topologique de manière gloutonne
     */
    public ArrayList<String> trieTopologiqueGlouton () {
        // NUM
        ArrayList<String> num = new ArrayList<>();

        // VOISINS SORTANT
        // sommet+ (liste des sommets-)
        TreeMap<String, Set<String>> lvs = (TreeMap<String, Set<String>>) this.voisinsSortant.clone();

        // DEGRES ENTRANT
        // sommet+ et int
        TreeMap<String, Integer> e = new TreeMap<String, Integer>();
        e = this.getDegreEntrant();

        // SOMMETS SOURCES
        // sommet+
        TreeSet<String> s = new TreeSet<>();
        s = this.sommetsSources(e);

//        System.out.println( "------------ \n" + "e = " + e);
//        System.out.println("s = " + s);
//        System.out.println("lvs = " + lvs + "\n ------------");

        // PROGRAMME
        while (! s.isEmpty()) {
            String courant = s.pollFirst();
            for (String i : lvs.get(courant)) {

//                System.out.println("/" + courant);
//                System.out.println(i);
//                System.out.println("e="+e);
//                System.out.println("s="+s);
//                System.out.println(num);

                e.put(i, Integer.valueOf(e.get(i) - 1));
                if (e.get(i) == 0) {
                    s.add(i);
                }
            }
            num.add(courant);
        }
        System.out.println(num);
        return num;
    }
}
