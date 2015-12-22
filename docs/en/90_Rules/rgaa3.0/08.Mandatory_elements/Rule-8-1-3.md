# RGAA 3.0 -  Rule 8.1.3

## Summary

@@@ TO-DO

## Business description

### Criterion

[8.1](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-8-1)

###Test

[8.1.3](http://references.modernisation.gouv.fr/referentiel-technique-0#test-8-1-3)

### Description

Pour chaque page Web poss&eacute;dant une d&eacute;claration de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#type-de-document">type de document</a>, celle-ci est-elle situ&eacute;e avant la balise `html` dans le code source ?

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

The `<!doctype>` tag of the page

#### Set2

The `<html>` tag of the page

### Process

#### Test1 

Test whether the position of element of **Set1** is before the position of element of **Set2**. 

If **Test1** returns false, raise a MessageA

###### MessageA : Bad Doctype Location

-   code : BadDoctypeLocation
-   status: Failed
-   parameter : none
-   present in source : no

### Analysis

#### Passed

The doctype is declared before the `<html>` tag (**Test1** returns true)

#### Failed

The doctype is declared after the `<html>` tag (**Test1** returns false)

#### Not Applicable

The page has no doctype (**Set1**)
