# RGAA 3.2016 - Rule 1.9.5

## Summary
This test consists in detecting `<canvas>` tags and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion
[1.9](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-9)

### Test
[1.9.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-9-5)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-texte">image texte</a> <span lang="en">bitmap</span> (balise <code lang="en">canvas</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-porteuse-dinformation">porteuse d&#x2019;information</a> doit si possible &#xEA;tre remplac&#xE9;e par du <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#texte-styl">texte styl&#xE9;</a>. Cette r&#xE8;gle est-elle respect&#xE9;e (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/cas-particuliers.html#cp-1-9" title="Cas particuliers pour le crit&#xE8;re 1.9">cas particuliers</a>)&nbsp;?</div>

### Level
**AAA**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

#### Set1

All the `<canvas>` tags of the page not identified as captcha (see Notes about captcha detection) (css selector : `canvas`)

### Process

#### Test1

For each element of **Set1**, raise a MessageA

##### MessageA 

-    code : **ManualCheckOnElements** 
-    status: Pre-Qualified
-    parameter : tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<canvas>` tags (**Set1** is empty)

#### Pre-qualified

The page has at least one `<canvas>` tag (**Set1** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases

[TestCases files for rule 1.9.5](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010905/)


