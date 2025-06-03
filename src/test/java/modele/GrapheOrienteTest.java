package modele;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrapheOrienteTest {
    //Voile dossier de test

    private GrapheOriente grapheVide;
    private GrapheOriente grapheSimple;
    private GrapheOriente grapheComplexe;

    @BeforeEach
    void setUp() {
        grapheVide = new GrapheOriente(new ArrayList<>(), new ArrayList<>());

        List<String> departSimple = Arrays.asList("A", "B");
        List<String> arriveSimple = Arrays.asList("C", "D");
        grapheSimple = new GrapheOriente(departSimple, arriveSimple);

        List<String> departComplexe = Arrays.asList("A", "A", "B", "C", "D");
        List<String> arriveComplexe = Arrays.asList("B", "C", "D", "D", "E");
        grapheComplexe = new GrapheOriente(departComplexe, arriveComplexe);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(3)
    void ordre() {
        assertEquals(0, grapheVide.ordre(), "ordre doit être de 0 pour graph vide");
        assertEquals(4, grapheSimple.ordre(), "ordre doit être de 4");
        assertEquals(5, grapheComplexe.ordre(), "ordre doit être de 5");
    }

    @Test
    @Order(4)
    void degre() {
        assertEquals(1, grapheSimple.degre("A"), "Le sommet à devrait avoir un degré de 1");
        assertEquals(1, grapheSimple.degre("B"), "Le sommet à devrait avoir un degré de 1");
        assertEquals(0, grapheSimple.degre("C"), "Le sommet à devrait avoir un degré de 0");

        assertEquals(2, grapheComplexe.degre("A"), "Le sommet à devrait avoir un degré de 2");
        assertEquals(1, grapheComplexe.degre("B"), "Le sommet à devrait avoir un degré de 1");
        assertEquals(1, grapheComplexe.degre("C"), "Le sommet à devrait avoir un degré de 1");
        assertEquals(1, grapheComplexe.degre("D"), "Le sommet à devrait avoir un degré de 1");
        assertEquals(0, grapheComplexe.degre("E"), "Le sommet à devrait avoir un degré de 0");
    }

    @Test
    @Order(5)
    void taille() {
        assertEquals(0, grapheVide.taille(), "taille doit être de 0");
        assertEquals(2, grapheSimple.taille(), "taille doit être de 2");
        assertEquals(5, grapheComplexe.taille(), "taille doit être de 5");
    }

    @Test
    @Order(6)
    void degreMinimal() {
        assertEquals(0, grapheSimple.degreMinimal(), "degreMinimal doit être de 0");
        assertEquals(0, grapheComplexe.degreMinimal(), "degreMinimal doit être de 0");

        // Test avec un g avec une degrée min d'au moins 1
        List<String> depart0 = Arrays.asList("AB", "B+", "C");
        List<String> arrive0 = Arrays.asList("B+", "C", "AB");
        GrapheOriente graphe = new GrapheOriente(depart0, arrive0);
        assertEquals(1, graphe.degreMinimal(), "degreMinimal doit être de 1");
    }

    @Test
    @Order(7)
    void degreMaximal() {
        assertEquals(1, grapheSimple.degreMaximal(), "degreMaximal doit être de 1");
        assertEquals(2, grapheComplexe.degreMaximal(), "degreMaximal doit être de 2");

        //Test avec un g avec un degré max élevé
        List<String> depart = Arrays.asList("A", "A", "A", "A");
        List<String> arrive = Arrays.asList("B", "C", "D", "E");
        GrapheOriente etoile = new GrapheOriente(depart, arrive);
        assertEquals(4, etoile.degreMaximal(), "degreMaximal doit être de 4");
    }

    @Test
    @Order(2)
    void listSommets() {
        assertTrue(grapheVide.listeSommets().isEmpty(), "Liste des sommets doit être vide");

        List<String> sommetsSimple = grapheSimple.listeSommets();
        assertEquals(Arrays.asList("A", "B", "C", "D"), sommetsSimple, "Les sommets devraient être triés aphabétiquement");

        List<String> sommetsComplexe = grapheComplexe.listeSommets();
        assertEquals(Arrays.asList("A", "B", "C", "D", "E"), sommetsComplexe, "Les sommets devraient être triés aphabétiquement");
    }

    @Test
    @Order(1)
    void testConstructeurListeVide() {
        GrapheOriente graphe = new GrapheOriente(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, graphe.ordre(), "Un tel graphe doit avoir une ordre de 0");
        assertEquals(0, graphe.taille(), "Un tel graphe doit avoir une taille de 0");

    }

    @Test
    @Order(9)
    void toStringTest() {
        assertDoesNotThrow(() -> grapheSimple.toString(), "toString() ne devrait pas lancer d'exception");

        String str = grapheSimple.toString();
        assertNotNull(str, "toString() ne devrait pas retourner null");
    }
}