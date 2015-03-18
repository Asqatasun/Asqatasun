# Rule 1.2.3
## Summary

This test consists in checking whether each object image that doesn't
handle any information is defined with an empty "alt" attribute.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-2)

### Test

[1.2.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-2-3)

### Description

Pour chaque image objet sans <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLegendeImage">l&eacute;gende</a> (balise `object` avec l'attribut `type="image/..."`) non porteuse d'information, l'alternative textuelle entre `<object>` et `</object>` est-elle vide ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <object\> tags with a "type" attribute that starts with
"image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no <object\> tag with a "type"
attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
