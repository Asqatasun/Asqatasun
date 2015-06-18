# Rule 1.7.1
## Summary

This test consists in checking whether the detailed description of an
informative image is relevant.

## Business description

### Criterion

[1.7](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-7)

### Test

[1.7.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-7-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mimgInfo">image porteuse d'information</a> (balise `img` ou `input` avec l'attribut `type="image"`) ayant une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> v&eacute;rifie-t-elle une de ces conditions ? 
 
 *  La <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> via l'adresse r&eacute;f&eacute;renc&eacute;e dans l'attribut `longdesc` est pertinente 
 *  La <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> dans la page et signal&eacute;e dans l'attribut `alt` est pertinente 
 *  La <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> via un <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienAdj">lien adjacent</a> est pertinente 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

## Algorithm

### Selection

##### Set1

All the `<img>` tags of the page

##### Set2

All the `<input>` tags with the "type" attribute equals to "image"

### Process

The selection handles the process

### Analysis

#### Not Applicable

**Set1** AND **Set2** are empty (The page has no `<img>` tag and no `<input>` tag
with a type" attribute equals to "image")

#### Pre-qualified

**Set1** OR **Set2** is not empty

## Notes

No notes yet for that rule
