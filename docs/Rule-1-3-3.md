# Rule 1.3.3
## Summary

This test consists in checking whether each button associated with an
image that handles information has a relevant "alt" attribute.

## Business description

### Criterion

[1.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-3)

### Test

[1.3.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-3-3)

### Description

Pour chaque bouton associ&eacute; &agrave; une image (balise `input` avec l'attribut `type="image"`) ayant un attribut `alt`, le contenu de cet attribut est-il pertinent (hors <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit1-3" title="Cas particuliers pour le crit&egrave;re 1.3">cas particuliers</a>) ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <input\> tags with a "alt" attribute and the type attribute
equals to "image"

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no<input\> tags with a "alt" attribute
and the type attribute equals to "image")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
