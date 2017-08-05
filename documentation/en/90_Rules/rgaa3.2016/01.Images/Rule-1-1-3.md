# RGAA 3.2016 - Rule 1.1.3

## Summary
This test consists in checking whether each form button is defined with an `"alt"` attribute

## Business description

### Criterion
[1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-1)

### Test
[1.1.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-1-3)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#bouton-formulaire">bouton</a> de formulaire (balise <code lang="en">input</code> avec l&#x2019;attribut <code lang="en">type="image"</code>) a-t-il un attribut <code lang="en">alt</code>&nbsp;?</div>

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

All the `<input>` tags with a `"type"` attribute equals to "image" (css selector : `input[type=image]`)

### Process

#### Test1

For each element of **Set1**, test the presence of an `"alt"` attribute.

For each occurrence of false-result of **Test1**, raise a MessageA

##### MessageA 

-    code : **AltMissing** 
-    status: Failed
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Passed

All the `<input>` tags with a `"type"` attribute equals to "image" of the page have an `"alt"` attribute (**Test1** returns true for all the elements of **Set1**)

#### Failed

At least one `<input>` tag with a `"type"` attribute equals to "image" has no `"alt"` attribute (**Test1** returns failed for at least one element)

#### Not Applicable

The page has no `<input>` tag with a `"type"` attribute equals to "image" tag (**Set1** is empty)



##  TestCases

[TestCases files for rule 1.1.3](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010103/)


