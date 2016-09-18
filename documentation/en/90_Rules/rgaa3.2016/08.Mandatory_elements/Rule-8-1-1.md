# RGAA 3.2016 - Rule 8.1.1

## Summary
This tests checks whether a document type is available on the page.

## Business description

### Criterion
[8.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-8-1)

### Test
[8.1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-8-1-1)

### Description
<div lang="fr">Pour chaque page Web, le <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#type-de-document">type de document</a> (balise <code lang="en">doctype</code>) est-il pr&#xE9;sent&nbsp;?</div>

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

The `<!doctype>` tag on the page

### Process

The selection handles the process

### Analysis

#### Failed

The page has no doctype (**Set1** is empty)

#### Passed

A doctype is available on the page (**Set1** is empty)



##  TestCases

[TestCases files for rule 8.1.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule080101/)


