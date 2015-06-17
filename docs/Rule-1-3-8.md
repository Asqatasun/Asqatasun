# Rule 1.3.8

## Summary

This test consists in checking for each informative bitmap image (`canvas` tag) with an alternative are compatible with assistive Technology.

## Business description

### Criterion

[1.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-3)

###Test

[1.3.8](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-3-8)

### Description

Pour chaque image bitmap porteuse d'information (balise `canvas`) et poss&eacute;dant une alternative (contenu entre `<canvas>` et `</canvas>`), cette alternative est-elle <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mRestitutionCorrecte">correctement restitu&eacute;e</a> par les technologies d'assistance ?

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

All the `<canvas>` tags of the page (css selector : `canvas`) and with an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"INFORMATIVE_CANVAS_MARKER"` parameter and a text between `<canvas>` tags

#### Set2

All the `<canvas>` tags of the page (css selector : `canvas`) that don't have an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"INFORMATIVE_CANVAS_MARKER"` parameter or the `"DECORATIVE_CANVAS_MARKER"` parameter and a text between `<canvas>` tags 

### Process

#### Messages

For each occurrence of Set1, raise a MessageA

For each occurrence of Set2, raise a MessageB

##### MessageA : Checked assistive technologie for informative `canvas` 

-    code : CheckedAssistiveTechnologieForInformativeCanvas
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Checked assistive technologie for suspected informative `canvas` 

-    code : CheckedAssistiveTechnologieForSuspectedInformativeCanvas
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<canvas>` tag (Set1 and Set2 are empty)

#### Pre-qualified

In all other cases
