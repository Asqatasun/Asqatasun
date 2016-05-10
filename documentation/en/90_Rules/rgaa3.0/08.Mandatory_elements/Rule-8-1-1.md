# RGAA 3.0 -  Rule 8.1.1

## Summary

This tests checks whether a document type is available on the page.

## Business description

### Criterion

[8.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-1)

### Test

[8.1.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-1-1)

### Description

Pour chaque page Web, le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#type-de-document">type de document</a> (balise `doctype`) est-il pr&eacute;sent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

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

[TestCases files for rule 8.1.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080101/) 


