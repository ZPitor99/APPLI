package modele;

import java.util.*;

import static modele.LectureDistance.DISTANCES_VILLES;
import static modele.LectureDistance.VILLES;

public class GrapheOriente {
    TreeMap<String, Set<String>> voisinsSortant;

    public GrapheOriente(List<String> depart, List<String> arrive) {
        voisinsSortant = new TreeMap<>();
        if (! depart.isEmpty() && ! arrive.isEmpty()) {
            for (int i = 0; i < depart.size(); i++) {
                String deb = depart.get(i);
                String arr = arrive.get(i);

                // Ajouter l'arc de deb vers arr
                voisinsSortant.putIfAbsent(deb, new TreeSet<>());
                voisinsSortant.get(deb).add(arr);

                // S'assurer qu'arr est présent dans la map, mais ne pas écraser si déjà présent
                voisinsSortant.putIfAbsent(arr, new TreeSet<>());
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
        System.out.println("----------");
        System.out.println(voisinsSortant);
        int degreMin = Integer.MAX_VALUE;
        for (String i : listeSommets()) {
            System.out.println(degreMin);
            if (degre(i) < degreMin)
                degreMin = degre(i);
            System.out.println(degreMin);
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
        return num;
    }

    /**
     * À partir de la liste des voisins sortants (implementation du graphe), donne un dictionnaire des degrés entrant
     * de chaque sommet du graphe
     *
     * @return Un dictionnaire qui associe un sommet son degree entrant
     */
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

    /**
     * À partir de la liste des voisins sortants (implementation du graphe), donne les sommets sources du graphe
     * Rappel : Sources ↔ sommet avec un degré entrant = 0
     *
     * @param degreEntrant Un dictionnaire {ville : degré entrant} des sommets du graphe
     * @return la liste des villes étant source du graphe
     */
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
     * avec optimisation locale : prendre la ville la plus proche de la ville courante dans la liste des sources,
     * cela revient à partir de là où je suis, à aller dans la ville la plus proche parmi les villes où je dois aller possible.
     *
     * @return une arraylist de ville constituant le chemin topologique de manière gloutonne
     */
    public ArrayList<String> trieTopologiqueGlouton() {
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

        // Mettre courant à Velizy "départ"
        String courant = "Velizy+";

//        System.out.println( "------------ \n" + "e = " + e);
//        System.out.println("s = " + s);
//        System.out.println("lvs = " + lvs + "\n ------------");

        // PROGRAMME
        while (! s.isEmpty()) {
            // Optimisation locale
            courant = getPlusProche(s, courant);
            s.remove(courant);
            //---
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
        return num;
    }

    /**
     * Donne la ville la plus proche de la ville courante parmi les sources de sourcesAuxChoix
     * à partir des distances de la constante DISTANCES_VILLES
     *
     * @param sourcesAuChoix Liste des sources actuelles (villes où l'on peut aller)
     * @param courantActuel  La ville actuelle
     * @return Le nom de la ville la plus proche de la ville actuel das les sourcesAuChoix
     */
    private String getPlusProche(Set<String> sourcesAuChoix, String courantActuel) {
        if (sourcesAuChoix.isEmpty())
            return null;

        String plusProche = null;
        Integer minDist = Integer.MAX_VALUE;
        for (String candidate : sourcesAuChoix) {
            Integer dist = DISTANCES_VILLES.get(VILLES.get(courantActuel.substring(0, courantActuel.length() - 1))).get(VILLES.get(candidate.substring(0, candidate.length() - 1)));
            if (dist < minDist) {
                minDist = dist;
                plusProche = candidate;
            }
        }
        return plusProche;
    }

    /**
     * Génère tous les chemins topologiques possibles du graphe
     * Garde les k meilleurs et évite les doublons dus au + et -
     * mais met en doublon pour respecter k chemins
     *
     * @param k nombre maximum de chemins à retourner
     * @return liste des k plus courts chemins topologique
     */
    public List<List<String>> trieTopologiqueOptimal(int k) {
        TreeMap<String, Integer> degreEntrant = this.getDegreEntrant();
        TreeSet<String> sources = this.sommetsSources(degreEntrant);

        List<List<String>> tousLesChemins = new ArrayList<>();
        List<String> cheminActuel = new ArrayList<>();

        retourTopologique(degreEntrant, sources, cheminActuel, tousLesChemins);

        Map<List<String>, List<String>> cheminsUniques = new LinkedHashMap<>();
        //trier en fonction de la longeur du chemin
        tousLesChemins.sort((chemin1, chemin2) ->
                Integer.compare(longeurChemin(chemin1), longeurChemin(chemin2)));

        for (List<String> l : tousLesChemins) {
            List<String> cheminTransforme = Scenario.getCheminToString(l);

            if (! cheminsUniques.containsKey(cheminTransforme)) {
                cheminsUniques.put(cheminTransforme, l);

                if (cheminsUniques.size() >= k) {
                    break;
                }
            }
        }

        ArrayList<List<String>> resultats = new ArrayList<>(cheminsUniques.values());
        while (resultats.size() < k) {
            resultats.add(resultats.getLast());
        }
        return resultats;
    }

    /**
     * Méthode récursive pour générer tous les chemins topologiques
     *
     * @param degreEntrant   degrés entrants actuels de chaque sommet
     * @param sources        sommets actuellement disponibles (degré entrant = 0)
     * @param cheminActuel   chemin en cours de construction
     * @param tousLesChemins liste pour stocker tous les chemins complets trouvés
     */
    private void retourTopologique(TreeMap<String, Integer> degreEntrant,
                                   TreeSet<String> sources,
                                   List<String> cheminActuel,
                                   List<List<String>> tousLesChemins) {

        // Condition d'arrêt : plus de sources disponibles
        if (sources.isEmpty()) {
            // Vérifier si tous les sommets ont été visités
            if (cheminActuel.size() == this.ordre()) {
                tousLesChemins.add(new ArrayList<>(cheminActuel));
            }
            return;
        }

        for (String sommetCourant : new TreeSet<>(sources)) {


            //COPIES pour éviter ConcurrentModificationException
            TreeMap<String, Integer> nouveauDegreEntrant = new TreeMap<>(degreEntrant);
            TreeSet<String> nouvellesSources = new TreeSet<>(sources);
            List<String> nouveauChemin = new ArrayList<>(cheminActuel);

            nouvellesSources.remove(sommetCourant);
            nouveauChemin.add(sommetCourant);

            for (String voisin : voisinsSortant.get(sommetCourant)) {
                int nouveauDegre = nouveauDegreEntrant.get(voisin) - 1;
                nouveauDegreEntrant.put(voisin, nouveauDegre);

                if (nouveauDegre == 0) {
                    nouvellesSources.add(voisin);
                }
            }

            retourTopologique(nouveauDegreEntrant, nouvellesSources, nouveauChemin, tousLesChemins);
        }
    }

    private int longeurChemin(List<String> chemin) {
        return Scenario.longeurChemin(chemin);
    }
}
