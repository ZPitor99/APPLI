package modele;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ScenarioTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCheminToString() {
        ArrayList<String> cheminString1 = new ArrayList<>();
        cheminString1.add("a*");
        cheminString1.add("bbbb/");
        cheminString1.add("cccc");

        ArrayList<String> cheminString2 = new ArrayList<>();
        cheminString2.add("bbbb/");
        cheminString2.add("bbbb/");

        ArrayList<String> cheminString3 = new ArrayList<>();
        cheminString3.add("cccc+");

        ArrayList<String> cheminString4 = new ArrayList<>();

        ArrayList<String> result1 = Scenario.getCheminToString(cheminString1);
        assertEquals(List.of("a", "bbbb", "ccc"), result1, "Erreur avec deux str différentes");

        ArrayList<String> result2 = Scenario.getCheminToString(cheminString2);
        assertEquals(List.of("bbbb"), result2, "Erreur avec deux str à fusionner");

        ArrayList<String> result3 = Scenario.getCheminToString(cheminString3);
        assertEquals(List.of("cccc"), result3, "Erreur avec une seule str");

        ArrayList<String> result4 = Scenario.getCheminToString(cheminString4);
        assertNull(result4, "Erreur avec une liste vide");
    }


    @Test
    void longeurChemin() {
        ArrayList<String> cheminString1 = new ArrayList<>();
        cheminString1.add("Amiens+");
        cheminString1.add("Amiens-");

        ArrayList<String> cheminString2 = new ArrayList<>();
        cheminString2.add("Bordeaux+");

        ArrayList<String> cheminString3 = new ArrayList<>();
        cheminString3.add("Reims+");
        cheminString3.add("Paris-");

        ArrayList<String> cheminString4 = new ArrayList<>();

        //Velizy → Amiens = 147 et Amiens → Amien = 0 => 147*2
        assertEquals(294, Scenario.longeurChemin(cheminString1), "Erreur pour deux villes confondues");
        //VeLizy → Bordeaux = 572 => 572*2
        assertEquals(1144, Scenario.longeurChemin(cheminString2), "Erreur pour une seule ville");
        //Somme des distances entre chaque ville
        assertEquals(354, Scenario.longeurChemin(cheminString3), "Erreur pour plusieurs villes");
        //Chemin vide → 0
        assertEquals(0, Scenario.longeurChemin(cheminString4), "Erreur pour aucune ville");
    }
}