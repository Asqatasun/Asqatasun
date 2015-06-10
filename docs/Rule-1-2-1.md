# Rule 1.2.1

## Summary

This test consists in checking whether the `alt` attribute of each decorative image is empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-2)

### Test

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

**Semidecidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags of the page with an `"alt"` attribute but not within a `<a>` tag (in this case, the image would be considered as a link) and with an `id` attribute or a `class` attribute or a `role` attribute that matches one of the values set by the user through the `"DECORATIVE_IMAGE_MARKER"` parameter.

#### Set2

All the `<img>` tags of the page with an `alt` attribute that don't have an `id` attribute or a `class` attribute or a `role` attribute that matches one the values set by the use through the `"DECORATIVE_IMAGE_MARKER"` parameter or the `"INFORMATIVE_IMAGE_MARKER"` parameter. 
That means select all the `<img>` tags not within an a tag with an `alt` attribute of the page when these parameters are empty.

### Process

#### Tests

##### Test1 

For each element of Set1 (decorative `img` identified by a html marker), check that the content of the `"alt"` attribute is empty. For each element returning false in Test1, raise a MessageA. 

##### Test2

For each element of Set2 (`<img>` tags not identified as decorative image), check that the content of the alt attribute empty. For each element returning false in Test2, raise a MessageB For each element returning true in Test2, raise a MessageC

#### Messages

##### MessageA : Decorative image with not empty alternative

code : NotEmptyAlt
status: Failed
parameter : alt attribute, src attribute, Snippet
present in source : yes

##### MessageB : Check if the image is informative

code : CheckIfTheImageIsInformative
status: Pre-qualified
parameter : alt attribute, src attribute, Snippet
present in source : yes

##### MessageC : Check the nature of the image with a empty alternative

code : CheckNatureOfImageWithEmptyAlt
status: Pre-qualified
parameter : alt attribute, src attribute, Snippet
present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with an `"alt"` attribute (Set1 and Set2 is empty)

#### Failed

Test1 returns false for at least one element (one `img` identified as decorative has a not empty alternative)

#### Passed

Set2 is empty and Test1 returns true for all element (all `img` of the page is identified and all `img` identified as decorative has a empty alternative)

#### Pre-qualified

In all other cases




