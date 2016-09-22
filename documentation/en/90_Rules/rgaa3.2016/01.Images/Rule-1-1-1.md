# RGAA 3.2016 - Rule 1.1.1

## Summary
This test consists in checking whether each `<img>` tag is defined with an `"alt"` attribute.

## Business description

### Criterion
[1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-1-1)

### Test
[1.1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-1-1)

### Description
<div lang="fr">Chaque image (balise <code lang="en">img</code>) a-t-elle un attribut <code lang="en">alt</code>&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags (css selector : `img`)

### Process

#### Test1

For each element of **Set1**, test the presence of the `"alt"` attribute.

For each occurrence of false-result of **Test1**, raise a MessageA

##### MessageA : Missing Alt attribute

-    code : **AltMissing** 
-    status: Failed
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Passed

All the `<img>` tags of the page have an `"alt"` attribute (**Test1** returns true for all the elements of **Set1**)

#### Failed

At least one `<img>` tag has no `"alt"` attribute (**Test1** returns failed for at least one element)

#### Not Applicable

The page has no `<img>` tag (**Set1** is empty)



##  TestCases

[TestCases files for rule 1.1.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010101/)


