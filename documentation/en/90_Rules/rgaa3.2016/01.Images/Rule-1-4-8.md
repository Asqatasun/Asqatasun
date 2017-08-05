# RGAA 3.2016 - Rule 1.4.8

## Summary
No-check rule


## Business description

### Criterion
[1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-4)

### Test
[1.4.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-4-8)

### Description
<div lang="fr">Chaque image vectorielle (balise <code lang="en">svg</code>) utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#captcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-test">image-test</a>, en l&#x2019;absence d&#x2019;<a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#alternative-svg">alternative</a>, v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>La balise <code lang="en">svg</code> poss&#xE8;de un <code lang="en">role="img"</code>&nbsp;;</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une propri&#xE9;t&#xE9; <code lang="en">aria-label</code> dont le contenu permet de comprendre la nature et la fonction de l&#x2019;image et identique &#xE0; l&#x2019;attribut <code lang="en">title</code> s&#x2019;il est pr&#xE9;sent&nbsp;;</li> <li>La balise <code lang="en">svg</code> poss&#xE8;de une balise <code lang="en">desc</code> dont le contenu permet de comprendre la nature et la fonction de l&#x2019;image et identique &#xE0; la propri&#xE9;t&#xE9; <code lang="en">aria-label</code> et &#xE0; l&#x2019;attribut <code lang="en">title</code> de la balise <code lang="en">svg</code> s&#x2019;il est pr&#xE9;sent&nbsp;;</li> <li>Un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#lien-adjacent">lien adjacent</a> permet d&#x2019;acc&#xE9;der &#xE0; une alternative dont le contenu permet de comprendre la nature et la fonction de l&#x2019;image et identique &#xE0; la propri&#xE9;t&#xE9; <code lang="en">aria-label</code> et &#xE0; l&#x2019;attribut <code lang="en">title</code> de la balise <code lang="en">svg</code> s&#x2019;il est pr&#xE9;sent.</li> </ul></div>

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

[TestCases files for rule 1.4.8](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010408/)


