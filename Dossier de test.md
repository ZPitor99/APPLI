# Dossier de Test

## Présentation

L'objectif du test est de mettre en place les méthodes sur les graphes orientés et quelques unes sur les Scénarios. 
Les tests couvrent la plus grande partie des méthodes et vérifient les cas normaux, les cas limites et les bugs potentiels.

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

---

## Test de la class `Scénarios`

Test des méthodes qui ne dépende pas de la classe elle-même (static)

### Test `getCheminToString()`

Soit $e_1$ et $e^{\prime}_1$ deux éléments du chemin pris en paramètres et $e_2$, $e^{\prime}_2$ sont respectivement les deux éléments dont le dernier charactère à été supprimé.

| Classe | chemin                                            | Attendu                     | Observé                     | Valide       |
| ------ | ------------------------------------------------- | --------------------------- | --------------------------- | ------------ |
| P1     | $\forall e_1, e^{\prime}_1, e_1\neq e^{\prime}_1$ | [$e_2,e^{\prime}_2$]        | [$e_2,e^{\prime}_2$]        | $\checkmark$ |
| P2     | $\forall e_1, e^{\prime}_1, e_1 = e^{\prime}_1$   | [$e_2$] ou [$e^{\prime}_2$] | [$e_2$] ou [$e^{\prime}_2$] | $\checkmark$ |
| P3     | $\exists! e_1, e_1 \in$ chemin                    | [$e_2$]                     | [$e_2$]                     | $\checkmark$ |
| P4     | chemin $= \emptyset $                             | $\emptyset$                 | $\emptyset$                 | $\checkmark$ |

La méthode c'est comporté comme demandé 

### Test `longeurChemin()`

Soit $i$ le nombre d'élément du chemin, et $e$, $e^{\prime}$ des éléments du chemins présent dans la Constante $distanceVille$, $d(x,y)$ la distance entre $x$ et $y$ trouvé à partir de la Constante, et $V$ la ville de Vélizy:

| Classe | chemin                                    | Attendu                                     | Observé                                     | Valide       |
| ------ | ----------------------------------------- | ------------------------------------------- | ------------------------------------------- | ------------ |
| P1     | $\forall e, e^{\prime}, e = e^{\prime}$   | $d(V,e)\times2$                             | $d(V,e)\times2$                             | $\checkmark$ |
| P2     | $\exists! e, e \in$ chemin                | $d(V,e)\times2$                             | $d(V,e)\times2$                             | $\checkmark$ |
| P3     | $\forall e, e^{\prime}, e\neq e^{\prime}$ | $d(V,e) + d(e,e^{\prime}) + d(e{\prime},V)$ | $d(V,e) + d(e,e^{\prime}) + d(e{\prime},V)$ | $\checkmark$ |
| P4     | chemin $= \emptyset $                     | $0$                                         | $Erreur$                                    | $\times$     |

Le test de cette méthode nous a permit de mettre en évidence que nous n'avions pas pris en compte si le chemin était vide, nous avons donc ajouté le code suivant:

```java
// Longueur = 0 à l'exécution du if
if (chemin == null || chemin.isEmpty()) {
    return longueur;
}
```

$\implies$ **P4** est maintenant valide $\checkmark$
