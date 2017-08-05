# RGAA 3.2016 - Rule 8.1.3

## Summary
@@@ TO-DO

## Business description

### Criterion
[8.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-8-1)

### Test
[8.1.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-8-1-3)

### Description
<div lang="fr">Pour chaque page Web poss&#xE9;dant une d&#xE9;claration de <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#type-de-document">type de document</a>, celle-ci est-elle situ&#xE9;e avant la balise <code lang="en">html</code> dans le code source&nbsp;?</div>

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

The `<!doctype>` tag of the page

#### Set2

The `<html>` tag of the page

### Process

#### Test1 

Test whether the position of element of **Set1** is before the position of element of **Set2**. 

If **Test1** returns false, raise a MessageA

###### MessageA : Bad Doctype Location

-   code : BadDoctypeLocation
-   status: Failed
-   parameter : none
-   present in source : no

### Analysis

#### Passed

The doctype is declared before the `<html>` tag (**Test1** returns true)

#### Failed

The doctype is declared after the `<html>` tag (**Test1** returns false)

#### Not Applicable

The page has no doctype (**Set1**)



##  TestCases

[TestCases files for rule 8.1.3](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule080103/)


