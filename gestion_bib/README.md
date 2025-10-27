# Analyse des métriques CKJM

## Partie 2 – Analyse des résultats

### Exercice 1 : Lecture et interprétation

1. **Tableau des métriques CK**

| Métrique | Nom complet | Interprétation / Ce qu'elle mesure |
|----------|-------------|-----------------------------------|
| WMC | Weighted Methods per Class | Complexité interne de la classe |
| DIT | Depth of Inheritance Tree | Profondeur d'héritage |
| NOC | Number of Children | Nombre de sous-classes directes |
| CBO | Coupling Between Objects | Couplage avec d'autres classes |
| RFC | Response For a Class | Méthodes accessibles depuis l'extérieur |
| LCOM | Lack of Cohesion of Methods | Manque de cohésion interne |
| Ca | Afferent Coupling | Couplages entrants (stabilité) |
| Ce | Efferent Coupling | Couplages sortants (instabilité) |

2. **Analyse des métriques**

Voici les métriques extraites du fichier metrics.txt :

```
library.service.Customer 3 1 0 1 10 0 0 3
library.model.DigitalBook 2 0 0 1 3 0 1 2
library.model.Book 7 1 1 1 13 5 3 7
library.app.LibraryApp 2 1 0 4 10 1 0 2
library.model.Lendable 2 1 0 0 2 1 4 2
library.service.Library 5 1 0 2 18 0 1 5
```

**a. Forte complexité (WMC élevé)**
- `library.model.Book` (WMC = 7) : La classe la plus complexe de l'application
- `library.service.Library` (WMC = 5) : Deuxième classe la plus complexe

**b. Forte dépendance (CBO élevé)**
- `library.app.LibraryApp` (CBO = 4) : Dépend de 4 autres classes
- `library.service.Library` (CBO = 2) : Dépend de 2 autres classes

**c. Nombre élevé de réponses possibles (RFC élevé)**
- `library.service.Library` (RFC = 18) : Nombre élevé de méthodes accessibles
- `library.model.Book` (RFC = 13) : Nombre important de méthodes accessibles
- `library.service.Customer` et `library.app.LibraryApp` (RFC = 10) : Nombre modéré de méthodes accessibles

**d. Faible cohésion (LCOM élevé)**
- `library.model.Book` (LCOM = 5) : Cohésion faible, plusieurs responsabilités potentielles
- `library.model.Lendable` (LCOM = 4) : Cohésion modérée à faible

**Remarque** :
- Les classes avec un LCOM de 0 ou 1 ont une bonne cohésion.
- Les valeurs de DIT et NOC sont généralement faibles, ce qui est normal pour une application de cette taille.
- La classe `library.service.Library` semble être un point critique avec un RFC élevé (18), ce qui pourrait indiquer qu'elle a trop de responsabilités.

### Exercice 2 : Analyse critique

1. **Classe la plus complexe**
   - `library.service.Library` (WMC=5, RFC=18)
   - **Pourquoi ?** Cette classe a le plus grand nombre de méthodes pondérées (WMC) et le plus grand nombre de réponses possibles (RFC), ce qui en fait la classe la plus complexe de l'application. Son interface est conséquente, ce qui peut compliquer sa maintenance.

2. **Classe la plus cohérente**
   - `library.service.Customer` et `library.model.DigitalBook` (LCOM=0)
   - **Pourquoi ?** Un LCOM de 0 indique une excellente cohésion, ce qui signifie que les méthodes de ces classes sont fortement liées à leurs attributs. Ces classes sont bien conçues avec des responsabilités clairement définies.

3. **Corrélation WMC et RFC**
   - **Oui, corrélation positive** : Plus une classe a de méthodes (WMC élevé), plus elle a de réponses possibles (RFC élevé).
   - **Exemples** :
     - `Library` : WMC=5, RFC=18
     - `Book` : WMC=7, RFC=13
     - `DigitalBook` : WMC=2, RFC=3
   - **Interprétation** : Cette corrélation est logique car plus une classe contient de méthodes, plus elle peut potentiellement répondre à des appels différents.

4. **Indicateurs de faible réutilisabilité**
   - **CBO élevé** : Fort couplage avec d'autres classes, rendant la classe difficile à réutiliser indépendamment.
   - **LCOM élevé** : Faible cohésion, indiquant que la classe a probablement trop de responsabilités.
   - **RFC élevé** : Interface complexe, rendant la classe difficile à comprendre et à maintenir.
   - **Exemple critique** : `Book` (LCOM=5) montre des signes de faible cohésion, ce qui pourrait nuire à sa réutilisabilité.

5. **Comparaison des packages**
   - **library.model** :
     - `Book` : Ca=3, Ce=1
     - `DigitalBook` : Ca=1, Ce=0
     - `Lendable` : Ca=4, Ce=0
     - **Stabilité** : Moyenne (Ca moyen=2.67, Ce moyen=0.33)
   - **library.service** :
     - `Customer` : Ca=0, Ce=3
     - `Library` : Ca=1, Ce=5
     - **Stabilité** : Faible (Ca moyen=0.5, Ce moyen=4)
   - **Conclusion** : Le package `library.model` est plus stable que `library.service` car il a un Ca plus élevé et un Ce plus faible. Cela signifie qu'il est plus indépendant des autres parties du système et moins susceptible d'être affecté par des changements dans d'autres packages.
