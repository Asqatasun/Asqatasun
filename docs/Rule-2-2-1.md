# Rule 2.2.1
## Summary

This test consists in checking the relevancy of the title associated
with each frame tag

## Business description

### Criterion

[2.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-2-2)

### Test

[2.2.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-2-2-1)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mCadreEnLigne">cadre en ligne</a> (balise `iframe`) ayant un attribut `title`, le contenu de cet attribut est-il pertinent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

**Set1 :**

All the <frame\> tags with a "title" attribute ( frame[title] )

### Process

##### Test1

For each element of Set1, we check whether the "title" attribute is not
empty

For each element returning false in Test1, raise a Message1.

##### Test2

For each element of Set1, we check whether the "title" attribute doesn't
only contain non alphanumerical characters.

For each element returning false in Test2, raise a Message1.

##### Test3

For each element of Set1, we check whether the "title" attribute is not
striclty identical to the "src" attribute.

For each element returning false in Test3, raise a Message1.

**Test4**

If Test1 AND Test2 AND Test3 return true (no pattern detected), raise a
Message2**.**

##### Message1: Not Pertinent title of frame

-   code : NotPertinentTitleOfFrame
-   status: Failed
-   parameter : title attribute, snippet
-   present in source : yes

##### Message2 : Check Pertinence of title of frame

-   code : CheckTitleOfFramePertinence
-   status: NMI
-   parameter : title attribute, snippet
-   present in source : yes

### Analysis

**NA :**

The page has no <frame\> tag with a "title" attribute (Set1 is empty)

**Failed :**

Test1 OR Test2 OR Test3 returns true for at least one element of Set1

**NMI :**

In all other cases

## Notes

***Definition of not-pertinent frame title :***

A frame title is regarded as not-pertinent in the following cases :

-   the frame title is empty
-   the frame title is identical to the src attribute of the frame
-   the frame title only contains not alphanumerics characters

