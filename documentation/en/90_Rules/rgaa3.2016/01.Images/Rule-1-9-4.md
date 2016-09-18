# RGAA 3.2016 - Rule 1.9.4

## Summary
This test consists in detecting "text image" embed tags and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion
[1.9](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-9)

### Test
[1.9.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-9-4)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-texte">image texte</a> embarqu&#xE9;e (balise <code lang="en">embed</code> avec l&#x2019;attribut <code lang="en">type="image/…"</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-porteuse-dinformation">porteuse d&#x2019;information</a> doit si possible &#xEA;tre remplac&#xE9;e par du <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#texte-styl">texte styl&#xE9;</a>. Cette r&#xE8;gle est-elle respect&#xE9;e (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-1-9" title="Cas particuliers pour le crit&#xE8;re 1.9">cas particuliers</a>)&nbsp;?</div>

### Level
**AAA**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<embed>` tags with a `"type"` attribute that starts with "image/..."  not identified as captcha (see Notes about captcha detection)  (embed[type^=image])

### Process

For each element of **Set1**, raise a MessageA

##### MessageA 

-    code : **ManualCheckOnElements** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<embed>` tag with a "type" attribute that starts with "image/..." (**Set1** is empty)

#### Pre-qualified

The page has at least one `<embed>` tag with a "type" attribute that starts with "image/..." (**Set1** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases

[TestCases files for rule 1.9.4](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010904/)


