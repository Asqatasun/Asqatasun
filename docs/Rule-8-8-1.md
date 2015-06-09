# Rule 8.8.1
## Summary

This test consists in checking whether each change of language is valid

## Business description

### Criterion

[8.8](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-8-8)

### Test

[8.8.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-8-8-1)

### Description

Dans chaque page Web, chaque changement de langue (attribut `lang` et/ou `xml:lang`) est-il valide ?

### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the tags different from `<html>` that have a "lang" or a "xml:lang"
attribute

### Process

The selection handles the process.

For each occurence of the selection raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: Pre-Qualified
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has no tag with different from html the
"lang" or "xml:lang" attribute)

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
