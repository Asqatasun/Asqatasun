# Rule 1.2.5

## Summary

This test consists in checking whether neither text between `canvas` tags of each decorative bitmap image (`<canvas>` tag) are implemented correctly.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-2)

###Test

[1.2.5](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-5)

### Description

Pour chaque image bitmap de d&eacute;coration (balise `canvas`), le contenu entre `<canvas>` et `</canvas>` doit &ecirc;tre d&eacute;pourvu de contenus textuels, cette r&egrave;gle est-elle respect&eacute;e ?

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
All the `<canvas>` tags of the page (css selector : `canvas`) and with an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"DECORATIVE_CANVAS_MARKER"` parameter.

#### Set2
All the `<canvas>` tags of the page (css selector : `canvas`) that don't have an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"DECORATIVE_CANVAS_MARKER"` parameter or the `"INFORMATIVE_CANVAS_MARKER"` parameter. 

### Process

#### Tests

##### Test1

For each element of Set1, Check the presence of text between `<canvas>` tags.

For each occurrence of true-result of Test1, raise a MessageA

##### Test2

For each element of Set2, Check the presence of text between `<canvas>` tags.

For each occurrence of true-result of Test2, raise a MessageB

#### Messages

##### MessageA : Decorative canvas with text between `<canvas>` tags

-    code : DecorativeCanvasWithTextBetweenCanvasTags
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Suspected decorative canvas with text between `<canvas>` tags

-    code : SuspectedDecorativeCanvasWithTextBetweenCanvasTags
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Passed

Test1 returns false for all elements (all `canvas` identified as decorative and don't have text between `<canvas>` tags)

#### Failed

Test1 returns true at least one element (one `canvas` identified as decorative and have text between `<canvas>` tags)

#### Not Applicable

The page has no `<canvas>` tag (Set1 and Set2 are empty)

#### Pre-qualified

In all other cases






