# Rule 2.1.1

## Summary

This test consists in checking the presence of the `"title"` attribute for all the `<iframe>` elements of the page.

## Business description

### Criterion

[2.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-2-1)

### Test

[2.1.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-2-1-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mCadreEnLigne">cadre en ligne</a> (balise `iframe`) a-t-il un attribut `title` ?

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

All the `<iframe>` tags of the page (css selector : `iframe`)

### Process

#### Test1 :

For each element of **Set1**, test the presence of the `"title"` attribute.

For each occurrence of false-result of **Test1**, raise a MessageA

##### MessageA: Title attribute is missing

-   code : TitleAttributeMissing
-   status: Failed
-   parameter : `"src"` attribute, tag name, snippet
-   present in source : yes

### Analysis

#### Passed

All the `<iframe>` tags have an `"title"` attribute (**Test1** returns true for all the elements of **Set1**)

#### Failed

At least one `<frame>` tag has no `"title"` attribute (**Test1** returns false for at least one element of **Set1**)

#### Not Applicable

The page has no `<iframe>` tag (**Set1** is empty)
