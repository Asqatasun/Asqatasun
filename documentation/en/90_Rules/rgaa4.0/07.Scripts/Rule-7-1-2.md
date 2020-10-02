# RGAA 4.0 — Rule 7.1.2

## Summary

No-check rule

## Business description

### Criterion

[7.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-7-1)

### Test

[7.1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-1-2)

### Description

> Chaque [script](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#script) qui génère ou contrôle un [composant d’interface](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#composant-d-interface) respecte-t-il une de ces conditions ?
> 
> * Le [composant d’interface](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#composant-d-interface) est [correctement restitué](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#correctement-restitue-par-les-technologies-d-assistance) par les technologies d’assistance.
> * Une [alternative](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-a-script) accessible permet d’accéder aux mêmes fonctionnalités.

#### Particular cases (criterion 7.1)

> Il existe une gestion de cas particulier pour le test 7.1.3 lorsque :
> 
> * La ponctuation et les lettres majuscules sont présentes dans le texte de l’[intitulé visible](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-visible) : elles peuvent être ignorées dans le nom accessible sans porter à conséquence.
> * Le texte de l’[intitulé visible](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-visible) sert de symbole : le texte ne doit pas être interprété littéralement au niveau du nom accessible. Le nom doit exprimer la fonction véhiculée par le symbole (par exemple, "B" au niveau d’un éditeur de texte aura pour nom accessible "Mettre en gras", le signe ">" en fonction du contexte signifiera "Suivant" ou "Lancer la vidéo"). Le cas des symboles mathématiques fait cependant exception (voir la note ci-dessous).
> 
> Note : si l’étiquette visible représente une expression mathématique, les symboles mathématiques peuvent être repris littéralement pour servir d’étiquette au nom accessible (ex. : "A>B"). Il est laissé à l’utilisateur le soin d’opérer la correspondance entre l’expression et ce qu’il doit épeler compte tenu de la connaissance qu’il a du fonctionnement de son logiciel de saisie vocale ("A plus grand que B" ou "A supérieur à B").

#### Technical notes (criterion 7.1)

> Le critère 7.1 implémente la notion de « compatible avec les technologies d’assistance » tel que définie par les WCAG, ainsi que le recours à WAI-ARIA pour rendre un composant ou une fonctionnalité accessible. Le bon usage de WAI-ARIA est vérifié via les tests 7.1.1, 7.1.2, 7.1.3.
> 
> Note importante : dans un environnement HTML5, beaucoup de composants peuvent nécessiter JavaScript pour fonctionner ; en conséquence la fourniture d’une alternative à un composant JavaScript qui ne pourrait pas être rendu accessible devra bénéficier d’une méthode spécifique au composant en cause, permettant de le remplacer par une alternative accessible (et de le réactiver). Cela signifie que la désactivation de JavaScript pour l’ensemble de la page ne sera pas acceptée comme une méthode valable, à moins qu’elle ne remette pas en cause l’utilisation des autres composants.

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

@@@TODO


## Algorithm

### Selection

None

### Process

None

### Analysis

#### Not Tested

In all cases


## Files

- [TestCases files for rule 7.1.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule070102/)
- [Unit test file for rule 7.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070102Test.java)
- [Class file for rule 7.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070102.java)


