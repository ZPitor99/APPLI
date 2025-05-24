package modele;

import java.util.*;

public class GrapheOriente {
    TreeMap<String, Set<String>> voisinsSortant;

    public GrapheOriente(List<String> depart, List<String> arrive) {
        voisinsSortant = new TreeMap<>();
        if (!depart.isEmpty() || !arrive.isEmpty()) {
            for (int i = 0; i < depart.size(); i++) {
                if (!voisinsSortant.containsKey(depart.get(i))) {
                    voisinsSortant.put(depart.get(i), new TreeSet<>());
                }
                else {
                    voisinsSortant.get(depart.get(i)).add(arrive.get(i));
                }
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
        for (String i : listeSommets()) {
            sb.append("sommet ").append(i).append(" degree=").append(degre(i)).append(" voisins sortant: ").append(voisinsSortant.get(i)).append("\n");
        }
        return sb.toString();
    }

    public List<String> listeSommets() {
        List<String> sommets = new ArrayList<>(voisinsSortant.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    public int ordre() {
        return this.listeSommets().size();
    }

    public int degre(String sommet) {
        return voisinsSortant.get(sommet).size();
    }

    public int taille() {
        int taille = 0;
        for (String i : listeSommets()) {
            taille += this.degre(i);
        }
        return taille;
    }

    public int degreMinimal() {
        int degreMin = 0;
        for (String i : listeSommets()) {
            if (degre(i) < degreMin)
                degreMin = degre(i);
        }
        return degreMin;
    }

    public int degreMaximal() {
        int degreMax = 0;
        for (String i : listeSommets()) {
            if (degre(i) > degreMax)
                degreMax = degre(i);
        }
        return degreMax;
    }

    public List<String> trieTopologique() {
        // NUM
        ArrayList<String> num = new ArrayList<>();

        // VOISINS SORTANT
        TreeMap<String, Set<String>> lvs = (TreeMap<String, Set<String>>) this.voisinsSortant.clone();

        // DEGRES ENTRANT
        TreeMap<String, Integer> e = new TreeMap<String, Integer>();
        e = this.getDegreEntrant();

        // SOMMETS SOUCES
        TreeSet<String> s = new TreeSet<>();
        s = this.sommetsSources(e);

        System.out.println(e);
        System.out.println(s);
        System.out.println(lvs);

        // PROGRAMME
        while (!s.isEmpty()) {
            String courant = s.pollFirst();
            for (String i : lvs.get(courant)) {

                System.out.println("/" + courant);
                System.out.println(i);
                System.out.println("e="+e);
                System.out.println("s="+s);
                System.out.println(num);

                e.put(i, e.get(i)-1);
                if (e.get(i) == 0) {
                    s.add(i);
                }
            }
            num.add(courant);
        }

        return num;
    }

    private TreeMap<String, Integer> getDegreEntrant() {
        TreeMap<String, Integer> degreEntrant = new TreeMap<String, Integer>();
        for (String i : this.listeSommets()) {
            degreEntrant.put(i, 0);
            for (String j : this.listeSommets()) {
                if (this.voisinsSortant.get(j).contains(i)) {
                    degreEntrant.put(i, degreEntrant.get(i) + 1);
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
}
