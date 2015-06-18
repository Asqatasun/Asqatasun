# Rule 1.1.4

## Summary

This test consists in checking whether each `<area>` of a server image map is doubled by a link in the page.

## Business description

### Criterion

[1.1](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-1)

###Test

[1.1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-1-4)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mZone">zone</a> (balise `area`) d'une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mImgReactive">image r&eacute;active cot&eacute; serveur</a> est-t-elle doubl&eacute;e d'un lien dans la page ?

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags with a `"ismap"` attribute 

### Process

#### Tests

##### Test1

For each element of Set1, produce a MessageA

#### Messages

##### MessageA : Check a link is associated with the server-sided image map

-    code : Check a link is associated with the server-sided image map
-    status: Pre-qualified (NMI or warning)
-    case : For each element of Set1
-    parameter : tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with a `"ismap"` attribute (Set1 is empty)

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the set1 to determine whether the test is applicable




