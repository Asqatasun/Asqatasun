# Rule 1.1.2
## Summary

This test consists in checking whether each area of an image map is
defined with an "alt" attribute

## Business description

### Criterion

[1.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-1)

### Test

[1.1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-1-2)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mZone">zone</a> (balise `area`) d'une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mImgReactive">image r&eacute;active</a> a-t-elle un attribut `alt` ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

## Algorithm

### Selection

All the <area\> tags, defined in a <map\> tag associated with the
"usemap"\
attribute of an <img\> tag

-   used nomenclature : none

-   reference : none

### Process

Test the presence of an "alt" attribute in each element of the selection
set.

-   used nomenclature : none

-   reference : none

### Analysis

#### Not Applicable

-   The page has no <img\> tag
-   no <img\> tag is defined with an "usemap" attribute

#### Failed

The <map\> associated with the "usemap" attribute of a <img\> tag has at
least one <area\> tag without "alt" attribute

#### Passed

All the <area\> tags, defined in a <map\> tag associated with the
"usemap" attribute of an <img\> tag have an "alt" attribute

## Notes

No notes yet for that rule
