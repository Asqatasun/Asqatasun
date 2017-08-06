# RGAA 3.2017 - Rule 1.4.2

## Summary
No-check rule


## Business description

### Criterion
[1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-4)

### Test
[1.4.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-4-2)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#zone-dune-image-ractive">zone</a> (balise <code lang="en">area</code>) d'une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-ractive">image r&#xE9;active</a> utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#captcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-test">image-test</a>, ayant un attribut <code lang="en">alt</code>, v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>Le contenu de l'attribut <code lang="en">alt</code> permet de comprendre la nature et la fonction de la zone.</li> <li>S'il est pr&#xE9;sent, le contenu de l'attribut <code lang="en">title</code> est identique au contenu de l'attribut <code lang="en">alt</code>.</li> <li>S'il est pr&#xE9;sent, le contenu de la propri&#xE9;t&#xE9; <code lang="en">aria-label</code> est identique au contenu de l'attribut <code lang="en">alt</code>.</li> <li>S'il est pr&#xE9;sent, le contenu du <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#passage-texte-aria">passage de texte li&#xE9; via la propri&#xE9;t&#xE9; <code lang="en">aria-labelledby</code></a> est identique au contenu de l'attribut <code lang="en">alt</code>.</li> </ul></div>

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

#### No Tested
In all cases


##  TestCases

[TestCases files for rule 1.4.2](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2017/src/test/resources/testcases/rgaa32017/Rgaa32017Rule010402/)


