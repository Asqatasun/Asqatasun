# RGAA 3.2017 - Rule 1.3.8

## Summary
No-check rule


## Business description

### Criterion
[1.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-3)

### Test
[1.3.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-3-8)

### Description
<div lang="fr">Chaque image vectorielle (balise <code lang="en">svg</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-porteuse-dinformation">porteuse d'information</a>, en l'absence d'<a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#alternative-svg">alternative</a>, v&#xE9;rifie-t-elle ces conditions (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-1-3" title="Cas particuliers pour le crit&#xE8;re 1.3">cas particuliers</a>)&nbsp;? <ul><li>La balise <code lang="en">svg</code> poss&#xE8;de un <code lang="en">role="img"</code>.</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une propri&#xE9;t&#xE9; <code lang="en">aria-label</code> dont le contenu est pertinent et identique &#xE0; l'attribut <code lang="en">title</code> s'il est pr&#xE9;sent.</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une balise <code lang="en">&lt;title&gt;</code> dont le contenu est pertinent et contient un passage de texte identique &#xE0; la propri&#xE9;t&#xE9; <code lang="en">aria-label</code>.</li> </ul></div>

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

[TestCases files for rule 1.3.8](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2017/src/test/resources/testcases/rgaa32017/Rgaa32017Rule010308/)


