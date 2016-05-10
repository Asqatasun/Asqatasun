# RGAA 3.0 -  Rule 8.1.3

## Summary

@@@ TO-DO

## Business description

### Criterion

[8.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-1)

###Test

[8.1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-1-3)

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



##  TestCases 

[TestCases files for rule 8.1.3](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080103/) 


