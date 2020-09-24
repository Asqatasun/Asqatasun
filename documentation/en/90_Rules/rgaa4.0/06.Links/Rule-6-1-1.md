# RGAA 4.0 — Rule 6.1.1

## Summary

No-check rule

## Business description

### Criterion

[6.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-6-1)

### Test

[6.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-6-1-1)

### Description

> Chaque [lien texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-texte) vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * L’[intitulé de lien](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-ou-nom-accessible-de-lien) seul permet d’en comprendre la fonction et la destination.
> * L’[intitulé de lien](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-ou-nom-accessible-de-lien) additionné au [contexte du lien](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contexte-du-lien) permet d’en comprendre la fonction et la destination.

#### Particular cases (criterion 6.1)

> Il existe une gestion de cas particulier pour les tests 6.1.1, 6.1.2, 6.1.3 et 6.1.4 lorsque le lien est ambigu pour tout le monde. Dans cette situation, où il n’est pas possible de rendre le lien explicite dans son contexte, le critère est non applicable.
> 
> Il existe une gestion de cas particulier pour le test 6.1.5 lorsque :
> 
> * La ponctuation et les lettres majuscules sont présentes dans le texte de l’[intitulé visible](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-visible) : elles peuvent être ignorées dans le nom accessible sans porter à conséquence.
> * Le texte de l’[intitulé visible](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-visible) sert de symbole : le texte ne doit pas être interprété littéralement au niveau du nom accessible. Le nom doit exprimer la fonction véhiculée par le symbole (par exemple, "B" au niveau d’un éditeur de texte aura pour nom accessible "Mettre en gras", le signe ">" en fonction du contexte signifiera "Suivant" ou "Lancer la vidéo"). Le cas des symboles mathématiques fait cependant exception (voir la note ci-dessous).
> 
> Note : si l’étiquette visible représente une expression mathématique, les symboles mathématiques peuvent être repris littéralement pour servir d’étiquette au nom accessible (ex. : "A>B"). Il est laissé à l’utilisateur le soin d’opérer la correspondance entre l’expression et ce qu’il doit épeler compte tenu de la connaissance qu’il a du fonctionnement de son logiciel de saisie vocale ("A plus grand que B" ou "A supérieur à B").

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

- [TestCases files for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule060101/)
- [Unit test file for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule060101Test.java)
- [Class file for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule060101.java)


