# RGAA 3.2016 - Rule 1.4.8

## Summary
No-check rule


## Business description

### Criterion
[1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-4)

### Test
[1.4.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-4-8)

### Description
Chaque image vectorielle (balise svg) utilisée comme CAPTCHA ou comme image-test, en l’absence d’alternative, vérifie-t-elle ces conditions ? La balise svg possède un role="img" ; La balise svg possède une propriété aria-label dont le contenu permet de comprendre la nature et la fonction de l’image et identique à l’attribut title s’il est présent ; La balise svg possède une balise desc dont le contenu permet de comprendre la nature et la fonction de l’image et identique à la propriété aria-label et à l’attribut title de la balise svg s’il est présent ; Un lien adjacent permet d’accéder à une alternative dont le contenu permet de comprendre la nature et la fonction de l’image et identique à la propriété aria-label et à l’attribut title de la balise svg s’il est présent.

### Level
**A**


## Technical description

### Scope
**Page**

### Decision level


## Algorithm

### Selection
None

### Process
None

### Analysis

#### No Tested
In all cases


##  TestCases

[TestCases files for rule 1.4.8](https://github.com/Asqatasun/Asqatasun/tree/RGAA_3.2016/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010408/)


