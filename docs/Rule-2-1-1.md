# Rule 2.1.1
## Summary

This test consists in checking the presence of the title attribute for
all the frame elements of the page.

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

**page**

### Decision level

**decidable**

## Algorithm

### Selection

**Set1**

All the <frame\> tags (frame)

### Process

**Test1 :**

Test the presence of a "title" attribute for each element of Set1.

For each element returning false in Test1, raise a Message1.

##### Message1: Title attribute is missing

-   code : TitleAttributeMissing
-   status: Failed
-   parameter : src attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no <frame\> tag (Set1 is empty)

#### Failed

At least one <frame\> tag has no "title" attribute (Test1 returns false
for at least one element of Set1)

#### Passed

All the <frame\> tags have an "title" attribute (Test1 returns true for
all the elements of Set1)

## Notes

No notes yet for that rule
