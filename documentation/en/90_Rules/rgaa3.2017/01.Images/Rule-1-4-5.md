# RGAA 3.2017 - Rule 1.4.5

## Summary
No-check rule


## Business description

### Criterion
[1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-4)

### Test
[1.4.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-4-5)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-objet">image objet</a> (balise <code lang="en">object</code> avec l'attribut <code lang="en">type="image/…"</code>) utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#captcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-test">image-test</a>, qui utilise une propri&#xE9;t&#xE9; <code lang="en">aria-label</code>, <code lang="en">aria-labelledby</code> ou un attribut <code lang="en">title</code>, v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>S'il est pr&#xE9;sent, le contenu de l'attribut <code lang="en">title</code> est identique au contenu de l'attribut <code lang="en">aria-label</code>.</li> <li>S'il est pr&#xE9;sent, le contenu de l'attribut <code lang="en">title</code> est identique au <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#passage-texte-aria">passage de texte li&#xE9; par la propri&#xE9;t&#xE9; <code lang="en">aria-labelledby</code></a>.</li> </ul></div>

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

[TestCases files for rule 1.4.5](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2017/src/test/resources/testcases/rgaa32017/Rgaa32017Rule010405/)


