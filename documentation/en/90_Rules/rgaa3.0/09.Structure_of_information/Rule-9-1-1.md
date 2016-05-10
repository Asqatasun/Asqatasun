# RGAA 3.0 -  Rule 9.1.1

## Summary

This test consists in checking whether a heading of level 1 is present on the page.

## Business description

### Criterion

[9.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-9-1)

###Test

[9.1.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-9-1-1)

### Description

Dans chaque page Web, y a-t-il un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTitre">titre</a> de niveau 1 (balise `h1` ou balise poss&eacute;dant un role ARIA `"heading"` associ&eacute; &agrave; une propri&eacute;t&eacute; `aria-level="1"`) ?

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

`<h1>` tags included in the `<body>` tag and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute equals to "1" (h1, [role=heading][aria-level=1])

#### Set2

The `<h1>` tag of the page within the `<head>` tag (head title)

### Process

#### Test1

Test whether **Set1** is not empty. If false, raise a MessageA

###### MessageA : Heading of level 1 missing

-   code : H1TagMissing
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Passed

The page has a heading of level1 (**Set1** is not empty)

#### Failed

The page has no heading of level1 (**Set1** is empty)









##  TestCases 

[TestCases files for rule 9.1.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule090101/) 


