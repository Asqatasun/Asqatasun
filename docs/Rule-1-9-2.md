# Rule 1.9.2
## Summary

This test consists in checking whether each text area associated with an
image map is replaced with styled text.

## Business description

### Criterion

[1.9](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-9)

### Test

[1.9.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-9-2)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mimgReactive">image r&eacute;active</a> (balise `img` ou `object` avec l'attribut `usemap`), chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mZoneTexte">zone texte</a> (balise `area`) doit si possible &ecirc;tre remplac&eacute;e par du <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTexteStyle">texte styl&eacute;</a>. Cette r&egrave;gle est-elle respect&eacute;e (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit19-" title="Cas particuliers pour le crit&egrave;re 1.9">hors cas particuliers</a>) ?

### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <area\> tags of the page

### Process

The selection handles the process

### Analysis

#### Not Applicable

The selection is empty (The page has no <area\> tag)

#### Pre-qualified

In all other cases

## Notes

No notes yet for that rule
