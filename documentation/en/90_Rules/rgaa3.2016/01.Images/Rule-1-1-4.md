# RGAA 3.2016 - Rule 1.1.4

## Summary
This test consists in checking whether each `<area>` of a server image map is doubled by a link in the page.

## Business description

### Criterion
[1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-1)

### Test
[1.1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-1-4)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#zone-cliquable">zone cliquable</a> d&#x2019;une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#image-ractive">image r&#xE9;active</a> cot&#xE9; serveur est-t-elle doubl&#xE9;e d&#x2019;un lien dans la page&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags with an `"ismap"` attribute and all the `<input>` tags with a `"type"` attribute equals to "image" and an `"ismap"` attribute (css selector : `img[ismap],input[type=image][ismap]`)

### Process

#### Tests

##### Test1

For each element of Set1, produce a MessageA

#### Messages

##### MessageA : Check a link is associated with the server-sided image map

-    code : CheckALinkIsAssociatedWithTheServerSidedImageMap
-    status: Pre-qualified (NMI or warning)
-    case : For each element of Set1
-    parameter : `"alt"` attribute, `"src"` attribute, tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with a `"ismap"` attribute (**Set1** is empty)

#### Pre-qualified

**Set1** is not empty

## Notes

We only detect the elements of the **Set1** to determine whether the test is applicable



##  TestCases

[TestCases files for rule 1.1.4](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010104/)


