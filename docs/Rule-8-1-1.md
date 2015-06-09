# Rule 8.1.1
## Summary

This tests checks whether a document type is available on the page.

## Business description

### Criterion

[8.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-8-1)

### Test

[8.1.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-8-1-1)

### Description

Pour chaque page Web, le <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDTD">type de document</a> (balise `doctype`) est-il pr&eacute;sent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

## Algorithm

### Selection

The `<!doctype>` tag on the page

### Process

The selection handles the process

### Analysis

#### Failed

The page has no doctype (the selection is empty)

#### Passed

A doctype is available on the page (the selection is not empty)

## Notes

No notes yet for that rule
