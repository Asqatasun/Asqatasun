# RGAA 3.2017 - Rule 1.4.8

## Summary
No-check rule


## Business description

### Criterion
[1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-4)

### Test
[1.4.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-4-8)

### Description
<div lang="fr">Chaque image vectorielle (balise <code lang="en">svg</code>) utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#captcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-test">image-test</a>, en l'absence d'<a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#alternative-svg">alternative</a>, v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>La balise <code lang="en">svg</code> poss&#xE8;de un <code lang="en">role="img"</code>.</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une propri&#xE9;t&#xE9; <code lang="en">aria-label</code> dont le contenu permet de comprendre la nature et la fonction de l'image et identique &#xE0; l'attribut <code lang="en">title</code> s'il est pr&#xE9;sent.</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une balise <code lang="en">&lt;title&gt;</code> dont le contenu permet de comprendre la nature et la fonction de l'image et identique &#xE0; la propri&#xE9;t&#xE9; <code lang="en">aria-label</code>.</li> <li>Un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lien-adjacent">lien adjacent</a> permet d'acc&#xE9;der &#xE0; une alternative dont le contenu permet de comprendre la nature et la fonction de l'image et identique &#xE0; la propri&#xE9;t&#xE9; <code lang="en">aria-label</code>.</li> </ul></div>

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

[TestCases files for rule 1.4.8](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2017/src/test/resources/testcases/rgaa32017/Rgaa32017Rule010408/)


