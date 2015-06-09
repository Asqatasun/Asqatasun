# Rule 1.7.3
## Summary

This test consists in checking whether the detailed description of an
informative embedded image is relevant.

## Business description

### Criterion

[1.7](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-7)

### Test

[1.7.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-7-3)

### Description

Chaque image embarqu&eacute;e (balise `embed` avec l'attribut `type="image/..."`) ayant une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> v&eacute;rifie-t-elle une de ces conditions ? 
 
 *  La <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> adjacente &agrave; l'image embarqu&eacute;e est pertinente 
 *  La <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> via un <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienAdj">lien adjacent</a> est pertinente 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the `<embed>` tags

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<embed>` tag)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
