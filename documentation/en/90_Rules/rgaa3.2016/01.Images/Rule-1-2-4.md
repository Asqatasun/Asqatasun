# RGAA 3.2016 - Rule 1.2.4

## Summary
No-check rule


## Business description

### Criterion
[1.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-2)

### Test
[1.2.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-2-4)

### Description
<div lang="fr">Chaque image vectorielle (balise <code lang="en">svg</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-de-dcoration">de d&#xE9;coration</a>, sans <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lgende-dimage">l&#xE9;gende</a>, v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>La balise svg poss&#xE8;de un attribut <code lang="en">aria-hidden="true"</code>&nbsp;;</li> <li>Les balises <code lang="en">title</code> et <code lang="en">desc</code> sont absentes ou vides&nbsp;;</li> <li>La balise <code lang="en">svg</code> ou l&#x2019;un de ses enfants est d&#xE9;pourvue d&#x2019;attribut <code lang="en">title</code>&nbsp;;</li> <li>La balise <code lang="en">svg</code> ou l&#x2019;un de ses enfants est d&#xE9;pourvue de r&#xF4;le, propri&#xE9;t&#xE9; ou &#xE9;tat ARIA visant &#xE0; labelliser l&#x2019;image vectorielle (<code lang="en">aria-label</code>, <code lang="en">aria-describedby</code>, <code lang="en">aria-labelledby</code> par exemple).</li> </ul></div>

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

[TestCases files for rule 1.2.4](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010204/)


