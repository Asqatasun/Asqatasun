# RGAA 4.0 — Rule 1.9.2

## Summary

No-check rule

## Business description

### Criterion

[1.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-9)

### Test

[1.9.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-9-2)

### Description

> Chaque [image objet](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-objet) pourvue d’une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende) (balise `<object>` avec l’attribut type="image/…" associée à une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende) adjacente), vérifie-t-elle, si nécessaire, ces conditions ?
> 
> * L’[image objet](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-objet) (balise `<object>`) et sa [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende) adjacente sont contenues dans une balise `<figure>`.
> * La balise `<figure>` possède un attribut WAI-ARIA `role="figure"` ou `role="group"`.
> * La balise `<figure>` possède un attribut WAI-ARIA `aria-label` dont le contenu est identique au contenu de la [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende).
> * La [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende) est contenue dans une balise `<figcaption>`.

#### Technical notes (criterion 1.9)

> L’implémentation d’un attribut WAI-ARIA `role="group"` ou `role="figure"` sur l’élément parent `<figure>` est destiné à pallier le manque de support actuel des éléments `<figure>` par les technologies d’assistance. L’utilisation d’un élément `<figcaption>` pour associer une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende) à une image impose au minimum l’utilisation d’un attribut WAI-ARIA `aria-label` sur l’élément parent `<figure>` dont le contenu sera identique au contenu de l’élément `<figcaption>`. Pour s’assurer d’un support optimal, il peut également être fait une association explicite entre le contenu de l’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) de l’image et le contenu de l’élément `<figcaption>`, par exemple :
> 
> `<img src="image.png" alt="Photo : soleil couchant" /><figcaption>Photo : crédit xxx</figcaption>`
> 
> Les attributs WAI-ARIA `aria-labelledby` et `aria-describedby` ne peuvent pas être utilisés actuellement par manque de support par les technologies d’assistance.
> 
> Note : les images légendées doivent par ailleurs respecter le [critère 1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-1) et le [critère 1.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-3) relatifs aux images porteuses d’information.

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

- [TestCases files for rule 1.9.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010902/)
- [Unit test file for rule 1.9.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010902Test.java)
- [Class file for rule 1.9.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010902.java)


