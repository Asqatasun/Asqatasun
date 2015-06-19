# Rule 1.4.4
## Summary

This test consists in checking the pertinence of the altenative
associated with an object image used as a CAPTCHA.

## Business description

### Criterion

[1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-4)

### Test

[1.4.4](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-4-4)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgObj">image objet</a> (balise `object` avec l'attribut `type="image/..."`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgTest">image-test</a>, et ayant une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mAltTexteImg">alternative textuelle</a>, l'<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mAltTexteImg">alternative textuelle</a> permet-elle d'identifier la nature et la fonction de l'image ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

All the `<object>` tags with a "type" attribute that starts with
"image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<object>` tag with a "type"
attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
