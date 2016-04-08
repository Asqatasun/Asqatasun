# RGAA 3.0 -  Rule 9.1.2

## Summary

This test consists in checking the relevancy of the headings hierarchy.

## Business description

### Criterion

[9.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-9-1)

###Test

[9.1.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-9-1-2)

### Description

Dans chaque page Web, la hi&eacute;rarchie entre les <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTitre">titres</a> (balises `h` ou balise poss&eacute;dant un role ARIA `"heading"` associ&eacute; &agrave; une propri&eacute;t&eacute; `aria-level`) est-elle pertinente ?

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

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

We assume that the index of the first encountered <Hx> tag represents the index of reference for the document.

#### Test1

We check that the difference between the index of each element of **Set1** and the index of its previous element is not superior to 1.

For each occurrence of false-result of **Test1**, raise a MessageA

#### Test2

We check that the index of each element of **Set1** is not inferior to the index of reference.

For each occurrence of false-result of **Test1**, raise a MessageB

###### MessageA : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : previous heading tag, snippet
-   present in source : yes

###### MessageB : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : first heading tag, snippet
-   present in source : yes

### Analysis

#### Passed

**Test1** or **Test2** return true for all the elements of **Set1**

#### Failed

**Test1** or **Test2** return false for at least one element of **Set1**

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)
