package modele;

import java.util.*;

public class GrapheOriente {
    TreeMap<Integer, Set<Integer>> voisinsSortant;

    public GrapheOriente(int[][] graph) {
        voisinsSortant = new TreeMap<>();
        for (int i = 0; i < graph.length; i++) {
            voisinsSortant.put(i, new TreeSet<>());
            for (int j = 0; j < graph[i].length; j++) {
                voisinsSortant.get(i).add(graph[i][j]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordre : ").append(this.ordre()).append("\n");
        sb.append("Taille : ").append(this.taille()).append("\n");
        sb.append("DegreMin : ").append(this.degreMinimal()).append("\n");
        sb.append("DegreMax : ").append(this.degreMaximal()).append("\n");
        for (Integer i : listeSommets()) {
            sb.append("sommet ").append(i).append(" degre=").append(degre(i)).append(" voisins sortant: ").append(voisinsSortant.get(i)).append("\n");
        }
        return sb.toString();
    }

    public List<Integer> listeSommets() {
        List<Integer> sommets = new ArrayList<>(voisinsSortant.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    public int ordre() {
        return this.listeSommets().size();
    }

    public int degre(Integer sommet) {
        return voisinsSortant.get(sommet).size();
    }

    public int taille() {
        int taille = 0;
        for (Integer i : listeSommets()) {
            taille += this.degre(i);
        }
        return taille;
    }

    public int degreMinimal() {
        int degreMin = 0;
        for (Integer i : listeSommets()) {
            if (degre(i) < degreMin)
                degreMin = degre(i);
        }
        return degreMin;
    }

    public int degreMaximal() {
        int degreMax = 0;
        for (Integer i : listeSommets()) {
            if (degre(i) > degreMax)
                degreMax = degre(i);
        }
        return degreMax;
    }

    public List<Integer> trieTopologique() {
        // NUM
        ArrayList<Integer> num = new ArrayList<>();

        // VOISINS SORTANT
        TreeMap<Integer, Set<Integer>> lvs = (TreeMap<Integer, Set<Integer>>) this.voisinsSortant.clone();

        // DEGRES ENTRANT
        TreeMap<Integer, Integer> e = new TreeMap<Integer, Integer>();
        e = this.getDegreEntrant();

        // SOMMETS SOUCES
        TreeSet<Integer> s = new TreeSet<>();
        s = this.sommetsSources(e);

        System.out.println(e);
        System.out.println(s);
        System.out.println(lvs);

        // PROGRAMME
        while (! s.isEmpty()) {
            Integer courant = s.pollFirst();
            for (Integer i : lvs.get(courant)) {

                System.out.println("/" + courant);
                System.out.println(i);
                System.out.println("e=" + e);
                System.out.println("s=" + s);
                System.out.println(num);

                e.put(i, e.get(i) - 1);
                if (e.get(i) == 0) {
                    s.add(i);
                }
            }
            num.add(courant);
        }

        return num;
    }

    private TreeMap<Integer, Integer> getDegreEntrant() {
        TreeMap<Integer, Integer> degreEntrant = new TreeMap<Integer, Integer>();
        for (Integer i : this.listeSommets()) {
            degreEntrant.put(i, 0);
            for (Integer j : this.listeSommets()) {
                if (this.voisinsSortant.get(j).contains(i)) {
                    degreEntrant.put(i, degreEntrant.get(i) + 1);
                }
            }
        }
        return degreEntrant;
    }

    private TreeSet<Integer> sommetsSources(TreeMap<Integer, Integer> degreEntrant) {
        TreeSet<Integer> sommets = new TreeSet<>();
        for (Integer i : listeSommets()) {
            if (degreEntrant.get(i) == 0) {
                sommets.add(i);
            }
        }
        return sommets;
    }
}
