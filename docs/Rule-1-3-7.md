# Rule 1.3.7

## Summary

This test consists in checking for each informative vector image (<svg> tag) with an alternative are compatible with assistive Technology.

## Business description

### Criterion

[1.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-3)

###Test

[1.3.7](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-3-7)

### Description

Pour chaque image vectorielle porteuse d'information (balise `svg`) et poss&eacute;dant une alternative, cette alternative est-elle <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mRestitutionCorrecte">correctement restitu&eacute;e</a> par les technologies d'assistance ?

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

All the `<svg>` tags of the page (css selector : `svg`), with an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"INFORMATIVE_SVG_MARKER"` parameter, with a `"role"` attribute with value `"img"` and with a `aria-label` attribute not null or a `desc` tag children not null

#### Set2

All the `<svg>` tags of the page (css selector : `svg`) that don't have an `id` attribute or a `class` attribute that matches one the values set by the use through the `"INFORMATIVE_SVG_MARKER"` parameter or the `"DECORATIVE_SVG_MARKER"` parameter, with a `"role"` attribute with value `"img"` and with a `aria-label` attribute not null or a `desc` tag children not null

### Process

#### Messages

For each occurrence of Set1, raise a MessageA

For each occurrence of Set2, raise a MessageB

##### MessageA : Checked assistive technologie for informative `svg` 

-    code : CheckedAssistiveTechnologieForInformativeSvg
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Checked assistive technologie for suspected informative `svg` 

-    code : CheckedAssistiveTechnologieForSuspectedInformativeSvg
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag (Set1 and Set2 are empty)

#### Pre-qualified

In all other cases
