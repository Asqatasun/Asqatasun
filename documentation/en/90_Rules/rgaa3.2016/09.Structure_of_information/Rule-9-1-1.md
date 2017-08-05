# RGAA 3.2016 - Rule 9.1.1

## Summary
This test consists in checking whether a heading of level 1 is present on the page.

## Business description

### Criterion
[9.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-9-1)

### Test
[9.1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-9-1-1)

### Description
<div lang="fr">Dans chaque page Web, y a-t-il un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#titre">titre</a> de niveau 1 (balise <code lang="en">h1</code> ou balise poss&#xE9;dant un r&#xF4;le ARIA <code lang="en">"heading"</code> associ&#xE9; &#xE0; une propri&#xE9;t&#xE9; <code lang="en">aria-level="1"</code>)&nbsp;?</div>

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

[TestCases files for rule 9.1.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule090101/)


