# RGAA 3.0 -  Rule 8.3.1

## Summary

We check whether a language is specified for each textual element of the page

## Business description

### Criterion

[8.3](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-8-3)

### Test

[8.3.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-3-1)

### Description

Pour chaque page Web, l'indication de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLangueDefaut"> langue par d&eacute;faut</a> v&eacute;rifie-t-elle une de ces conditions ? 
 
 *  L'indication de la langue de la page (attribut `lang` et/ou `xml:lang`) est donn&eacute;e pour l'&eacute;l&eacute;ment `html` 
 *  L'indication de la langue de la page (attribut `lang` et/ou `xml:lang`) est donn&eacute;e sur chaque &eacute;l&eacute;ment de texte ou sur l'un des &eacute;l&eacute;ments parents 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

## Algorithm

### Selection

##### Set1

The `<html>` tag with a `"lang"` or `"xml:lang"` attribute.

##### Set2

The tags with a `"lang"` or `"xml:lang"` attribute.

##### Set3

The textual tags without `"lang"` or `"xml:lang"` attribute considering that
these attributes can be set to the current tag or to one of its
ascendants.

### Process

#### Test1

Test whether **Set2** is empty. If yes, raise a MessageA

#### Test2

Test whether **Set1** is empty and **Set2** and **Set3** are not. If yes, raise a MessageB

##### MessageA : Lang Attribute Missing On Whole Page

-   code : LangAttributeMissingOnWholePage
-   status: Failed
-   parameter : none
-   present in source : no

##### MessageB : Lang Attribute Missing On Html

-   code : LangAttributeMissingOnHtml
-   status: Failed
-   parameter : none
-   present in source : no

### Analysis

#### Passed

-   The `<html>` tag has a `"lang"` or a `"xml:lang"` attribute (**Set1** is not
    empty)
-   The language is provided for each textual element by the tag or by
    one of its parents (**Set1** is empty AND **Set2** is not empty AND **Set3** is
    empty)

#### Failed

-   The page has no language specification (**Set2** is empty).
-   Some textual tags are missing the language attribute (**Set1** is empty
    AND **Set2** is not empty AND **Set3** is not empty)

