# RGAA 3.2016 - Rule 8.5.1

## Summary
This test consists in detecting the presence of the `<title>` tag

## Business description

### Criterion
[8.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-8-5)

### Test
[8.5.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-8-5-1)

### Description
<div lang="fr">Chaque page Web a-t-elle un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#titrePage">titre de page</a> (balise <code lang="en">title</code>)&nbsp;?</div>

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

The `<title>` tag of the page within the `<head>` tag (head title)

### Process

#### Test1

Test whether **Set1** is not empty. If false, raise a MessageA.

###### MessageA : Title tag missing

-   code : TitleTagMissing
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Passed

The `<title>` tag is present on the page (**Test1** returns true)

#### Failed

The `<title>` tag is not present on the page (**Test1** returns false)



##  TestCases

[TestCases files for rule 8.5.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule080501/)


