# Rule 1.6.3
## Summary

This test consist in checking whether an informative embedded image has
a detailed description if necessary

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-6)

### Test

[1.6.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-6-3)

### Description

Chaque image embarqu&eacute;e porteuse d'information (balise `embed`), qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe un lien adjacent (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> 
 * Il existe une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> clairement identifiable adjacente &agrave; l'image embarqu&eacute;e 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <embed\> tags

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no <embed\> tag)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
