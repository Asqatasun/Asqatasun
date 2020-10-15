# RGAA 4.0 — Rule 8.1.2

## Summary

We check the doctype validity regarding the W3C recommandations ([W3C
Recommended list of Doctype
declarations](http://www.w3.org/QA/2002/04/valid-dtd-list.html))

## Business description

### Criterion

[8.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-1)

### Test

[8.1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-1-2)

### Description

> Pour chaque page web, le [type de document](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#type-de-document) (balise `doctype`) est-il valide ?

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

### Process

#### Test1

We check the validity of the doctype of the page regarding the
"RecommendedDoctypeDeclarations" and
"RecommendedCaseInsensitiveDoctypeDeclarations" whitelists

-   Used nomenclatures : "RecommendedDoctypeDeclarations",
    "RecommendedCaseInsensitiveDoctypeDeclarations"
-   Reference : All the doctypes declarations recommanded in
    [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

###### MessageA : Wrong Doctype Declaration

-   code : WrongDoctypeDeclaration
-   status: Failed
-   parameter : none
-   present in source : no

### Analysis

#### Not Applicable

The page has no doctype (**Set1**)

#### Failed

The doctype is not found among the doctype whitelist

#### Passed

The doctype is found among the doctype whitelist

## Notes

HTML5 doctype is NOT case-sensitive:
[http://www.w3.org/TR/html5/syntax.html#the-doctype](http://www.w3.org/TR/html5/syntax.html#the-doctype "http://www.w3.org/TR/html5/syntax.html#the-doctype")

HTML4: could not find formal statement about case-sensitiveness of
doctype: http://www.w3.org/TR/html4/intro/sgmltut.html

XHTML1: same as HTML4 http://www.w3.org/TR/xhtml1/

From version 2.0, Asqatasun works on the generated html rendered by
Firefox. 
We've noticed that for HTML 4.01 doctypes that define the doctype with
the "HTML" key in Uppercase, the doctype is transformed and the "HTML"
key is changed to lowercase. 
This behaviour is the same with chromium and opera. 
Regarding this fact, we decided to consider all the doctypes as case
insensitive despite the W3c recommandations.



## Files

- [TestCases files for rule 8.1.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080102/)
- [Unit test file for rule 8.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080102Test.java)
- [Class file for rule 8.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080102.java)


