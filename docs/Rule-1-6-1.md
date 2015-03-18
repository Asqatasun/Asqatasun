# Rule 1.6.1
## Summary

This test consists in checking whether an informative image has a
detailed description if necessary

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-6)

### Test

[1.6.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-6-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mimgInfo">image porteuse d'information</a> (balise `img`) qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe un attribut `longdesc` qui donne l'adresse (`url`) d'une page contenant la <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mDescDetaillee">description d&eacute;taill&eacute;e</a> 
 * Il existe un attribut `alt` contenant la r&eacute;f&eacute;rence &agrave; une description d&eacute;taill&eacute;e adjacente &agrave; l'image 
 * Il existe un lien adjacent (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la description d&eacute;taill&eacute;e 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the <img\> of the page

### Process

The selection handles the process

### Analysis

#### Not Applicable:

The selection is empty (the page has no <img\> tag)

#### Pre-qualified:

The seletion is not empty

## Notes

No notes yet for that rule
