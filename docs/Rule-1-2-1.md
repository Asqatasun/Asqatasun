# Rule 1.2.1

## Summary

This test consists in checking whether the alt attribute of each decorative image (`<img>` tag) is empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-2)

###Test

[1.2.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-2-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mimgDeco">image de d&eacute;coration</a> sans <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLegendeImage">l&eacute;gende</a> (balise `img`) et ayant un attribut `alt` v&eacute;rifie-t-elle ces conditions : 
 
 * le contenu de l'attribut alt est vide (`alt=""`) 
 * l'image de d&eacute;coration ne poss&egrave;de pas d'attribut `title` 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags of the page with an `"alt"` attribute and a `"src"` attribute but not within a `<a>` tag (in this case, the image would be considered as a link)

### Process

#### Test1 

Test if elements of Set1 have an empty alt attribute and a not empty `"src"` attribute

### Analysis

#### Not Applicable

The page has no `<img>` tag with an `"alt"` attribute (Set1 is empty)

#### Pre-qualified

The selection is not empty and at least one element has an `"alt"` attribute 

##### MessageA : Suspected decorative image with not empty alt attribute

-    code : SuspectedDecorativeImageWithNotEmptyAltAttribute
-    status: NMI
-    case : For each element returning false in Test1
-    parameter : tag name
-    present in source : yes

#### Passed

All the `<img>` tags have an `"alt"` attribute empty




