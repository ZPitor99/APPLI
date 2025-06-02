# Dossier de Test

## Présentation

L'objectif du test est de mettre en place les méthodes sur les graphes orientés. Les tests couvrent la plus grande
partie des méthodes publiques et vérifient les cas normaux, les cas limites et les bugs potentiels.

## Test de la class `GrapheOriente`

### Configuration des Tests (@BeforeEach)

#### Conception
Trois graphes différents sont créés pour couvrir différents scénarios :

1. **grapheVide** : Graphe sans sommets ni arêtes
2. **grapheSimple** : Graphe linéaire $A → B → C$ ($3$ sommets, $2$ arêtes)
3. **grapheComplexe** : Graphe avec embranchements $A → \left\{ B;C \right\}, \left\{ B;C \right\} → D, D → E $ ($5$ sommets, $5$ arêtes)

#### Validité
Cette approche permet de tester :
- Les cas limites (graphe vide)
- Les cas simples (chaîne linéaire)
- Les cas plus complexes (embranchements multiples)
- Autres cas spécifiques en fonction de la méthode testée

---

### Test `Ordre()`
Vérifie que la méthode `ordre()` retourne le bon nombre de sommets pour chaque type de graphe.

**Principe testé** : La méthode compte correctement les clés du TreeMap `voisinsSortant`.

---
### Test `Taille()`
Vérifie que la méthode `taille()` compte correctement le nombre total d'arêtes.

**Principe testé** : La somme de tous les degrés sortants égale le nombre d'arêtes.

---
### Test `DegreMinimal()`
Teste la méthode `degreMinimal()` sur différents types de graphes.

**Principe testé** : La méthode parcourt tous les sommets et trouve le minimim correct.

---
### Test `DegreMaximal()`
Vérifie que `degreMaximal()` trouve correctement le degré le plus élevé.

**Principe testé** : La méthode parcourt tous les sommets et trouve le maximum correct.

---
### Test `ListeSommets()`
Vérifie que la liste des sommets est correctement triée alphabétiquement.

**Principe testé** : La méthode utilise `Collections.sort()` correctement sur les clés du TreeMap.

---
### Test `testConstructeurListeVide()`
Test de robustesse pour vérifier le comportement avec des listes vides.

**Principe testé** : Gestion correcte des cas limites dans le constructeur.

---
### Test `toStringTest()`
Test de sanité pour s'assurer que `toString()` fonctionne sans exception.

**Principe testé** : Fonctionnement basique de la méthode d'affichage.

---

### Logique de cet ordre :

- D'abord les tests du constructeur (base de tout)
- Puis les tests des propriétés structurelles (sommets, ordre)
- Ensuite les calculs simples (degré, taille)
- Puis les calculs complexes (min/max)
- Les tests de cas spécifiques
- Enfin les tests d'affichage (moins critiques)

Cet ordre suit une progression logique du plus fondamental au plus spécialisé. C'est-à-dire de manière ascendante.

Les méthodes de tri topologique ne sont pas testées, car elles dépendent de classes externes (`LectureScenario`, `Scenario`)
non disponible dans environment de test.