# RGAA 3.2016 - Rule 1.6.5

## Summary
No-check rule


## Business description

### Criterion
[1.6](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-6)

### Test
[1.6.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-6-5)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#bouton-formulaire">bouton</a> de type image (balise <code lang="en">input</code> avec l&#x2019;attribut <code lang="en">type="image"</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-porteuse-dinformation">porteur d&#x2019;information</a>, qui impl&#xE9;mente une r&#xE9;f&#xE9;rence &#xE0; une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#description-dtaille-image">description d&#xE9;taill&#xE9;e</a> adjacente <i>via</i> une propri&#xE9;t&#xE9; <code lang="en">aria-describedby</code>, v&#xE9;rifie-t-il ces conditions&nbsp;? <ul><li>Le passage de texte est identifi&#xE9; <i>via</i> un attribut <code lang="en">id</code>&nbsp;;</li> <li>La valeur de l&#x2019;attribut <code lang="en">id</code> est unique&nbsp;;</li> <li>La valeur de la propri&#xE9;t&#xE9; ARIA <code lang="en">aria-describedby</code> est &#xE9;gale &#xE0; la valeur de l&#x2019;attribut <code lang="en">id</code>.</li> </ul></div>

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

[TestCases files for rule 1.6.5](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010605/)


