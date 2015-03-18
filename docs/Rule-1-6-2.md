# Rule 1.6.2
## Summary

This test consists in checking whether an informative object image has a
detailed description if necessary

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-6)

### Test

[1.6.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-6-2)

### Description

Chaque image objet porteuse d'information (balise `object` avec l'attribut `type="image/..."`), qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe un lien adjacent (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> 
 * Il existe une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> clairement identifiable adjacente &agrave; l'image objet 


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
