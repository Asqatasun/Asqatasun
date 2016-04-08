# RGAA 3.0 -  Rule 8.9.1

## Summary

This test consists in searching patterns indicating that forbidden tags
(not div, span or table) are used for layout purpose.

## Business description

### Criterion

[8.9](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-9)

### Test

[8.9.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-9-1)

### Description

Dans chaque page Web les balises (&agrave; l'exception de `div`, `span` et `table`) ne doivent pas &ecirc;tre utilis&eacute;es <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mUniquPres">uniquement &agrave; des fins de pr&eacute;sentation</a>. Cette r&egrave;gle est-elle respect&eacute;e ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1

All the `<a>` tags without "href", "name" or "id" attribute
(a:not([href]):not([name]):not([id]))

#### Set2

All the fieldset not within a form (fieldset:not(form
fieldset):not(*[role=search] fieldset):not(*[role=form] fieldset))

### Process

#### Test1

We check whether **Set1** AND **Set2** are empty. If true, raise a
MessageA

###### MessageA : No suspect pattern detected

-   code :NoPatternDetected
-   status: Pre-Qualified
-   present in source : no

For each occurence of the **Set1** raise a MessageB

###### MessageB : Link without target

-   code :LinkWithoutTarget
-   status: Failed
-   parameter : snippet
-   present in source : yes

For each occurence of the **Set2** raise a MessageC

###### MessageC : Fieldset not within a form

-   code :FieldsetNotWithinForm
-   status: Failed
-   parameter : snippet
-   present in source : yes

Test1 :

### Analysis

#### Failed :

The page contains a link without target or a fieldset not within a form (**Test1** returns false)

#### Pre-qualified :

**Test1** returns true

## Notes

On latest webdev data set (2013-10-30, 78,000 pages), links without
target (a:not([href]):not([name]):not([id])) have been found on 18256
pages, i.e on 23% of the pages.

On latest webdev data set (2013-10-30, 78,000 pages), fieldsets not
within form (fieldset:not(form fieldset):not(*[role=search]
fieldset):not(*[role=form] fieldset)) have been found on 982 pages, i.e
on 1.25% of the pages.
