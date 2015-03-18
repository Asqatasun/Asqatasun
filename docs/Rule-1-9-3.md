# Rule 1.9.3
## Summary

This test consists in checking whether each button "image of text" is
replaced with styled text.

## Business description

### Criterion

[1.9](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-9)

### Test

[1.9.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-9-3)

### Description

Pour chaque balise `form`, chacun de ses boutons "image texte" (balise `input` avec l'attribut `type="image"`) doit si possible &ecirc;tre remplac&eacute; par du <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTexteStyle">texte styl&eacute;</a>. Cette r&egrave;gle est-elle respect&eacute;e (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit19-" title="Cas particuliers pour le crit&egrave;re 1.9">hors cas particuliers</a>) ?

### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <input\> tags with a "type" attribute equals to "image"

### Process

The selection handles the process

### Analysis

#### Not Applicable

The selection is empty (The page has no <input\> tag with a "type"
attribute equals to "image")

#### Pre-qualified

In all other cases

## Notes

No notes yet for that rule
