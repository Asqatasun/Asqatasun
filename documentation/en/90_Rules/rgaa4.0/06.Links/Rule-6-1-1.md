# RGAA 4.0 — Rule 6.1.1

## Summary

This test consists in checking whether each text link is explicit enough to understand the purpose and the target.

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

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<a>` tags with a `"href"` attribute, without children (`a[href]:not(:has(img)):not(:has(svg)):not(:has(object)):not(:has(canvas))`) 
and all the tags with a `role` attribute equals to `link`, without children (`[role=link]:not(:has(img)):not(:has(svg)):not(:has(object)):not(:has(canvas))`)

#### Set2

All the elements of **Set1** with a not empty text or accessible name and without context (assuming [the definition of a link context in Rgaa4.0](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contexte-du-lien))

#### Set3

All the elements of **Set1** with a not empty text or accessible name and with a context (assuming [the definition of a link context in Rgaa4.0](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contexte-du-lien))

in other words :

size(**Set1**) = size(**Set2**) + size(**Set3**)

### Process

##### Test1

For each element of **Set2**, we check whether the computed accessible name is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test1**, raise a MessageA, raise a MessageB instead

##### Test2

For each element of **Set3**, we check whether the computed accessible name is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test2**, raise a MessageC, raise a MessageD instead

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, `"title"` attribute, `"aria-label"` attribute, computed accessible name, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, `"aria-label"` attribute, computed accessible name, snippet
-   present in source : yes

##### MessageC : Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, `"title"` attribute, `"aria-label"` attribute, computed accessible name, snippet
-   present in source : yes

##### MessageD : Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, `"aria-label"` attribute, computed link title, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no textual link (**Set1** is empty)

#### Failed

At least one textual link without context has a text content which is blacklisted or only composed of non-alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

We assume here that the links are only composed with a text. (`<a href="https://asqatasun.org/target.html">`my link`</a>`)

The accessible name is computed regarding the following rules :

 * Text associated through the usage of the WAI-ARIA `aria-labelledby` attribute
 * Otherwise, content of the WAI-ARIA `aria-label` attribute
 * Otherwise, content of the `a` tag
 * Otherwise, content de `title` attribute
 

All the links that have children different from `<img>`, `<canvas>`, `<object>` or `<svg>` are considered as combined links.

**Definition of not-pertinent link title :**

A link title is seen as not-pertinent in the following cases :

-   the link title is blacklisted (regarding the [LinkTextBlacklist nomenclature](https://doc.asqatasun.org/v5/en/business-rules/rgaa-v4/nomenclatures/linktextblacklist/))
-   the link only contains not alphanumerics characters


## Files

- [TestCases files for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule060101/)
- [Unit test file for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule060101Test.java)
- [Class file for rule 6.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule060101.java)


