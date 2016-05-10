# RGAA 3.0 -  Rule 8.8.1

## Summary

This test consists in checking whether each change of language is valid

## Business description

### Criterion

[8.8](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-8)

### Test

[8.8.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-8-1)

### Description

Dans chaque page Web, chaque changement de langue (attribut `lang` et/ou `xml:lang`) est-il valide ?

### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Set1

All the tags different from `<html>` that have a `"lang"` or a `"xml:lang"` attribute

### Process

#### Test1

We extract the language code from the `"lang"` or the "xml:lang"
attributes from elements of **Set2**. If these attributes are both set and
different, we keep the `"xml:lang"` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `"lang"` attribute instead.

For each extracted lang attribute, we check its validity regarding the following regexp : "\\w{2,3}(\\-\\w{2,})?$"

For each invalid element, raise a messageA.

###### MessageA : Malformed Language Declaration

-   code : MalformedLanguageDeclaration
-   status: Failed
-   parameter : extracted lang
-   present in source : yes

#### Test2

We extract the language code from the `"lang"` or the "xml:lang"
attributes from elements of **Set2**. If these attributes are both set and
different, we keep the `"xml:lang"` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `"lang"` attribute instead.

For each extracted lang attribute, we check its validity regarding the
"ValidLanguageCode" whitelist

-   Used nomenclature : "ValidLanguageCode", "XhtmlDoctypeDeclarations"
-   Reference : All the valid language codes recommanded in
    [http://www.loc.gov/standards/iso639-2/php/code_list.php](http://www.loc.gov/standards/iso639-2/php/code_list.php%20 "http://www.loc.gov/standards/iso639-2/php/code_list.php ")
    and all the xhtml doctypes declarations recommanded in
    [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

For each invalid element, raise a messageA.

###### MessageB : Wrong Language Declaration

-   code : WrongLanguageDeclaration
-   status: Failed
-   parameter : extracted lang
-   present in source : yes

### Analysis

#### Not Applicable

The page has no tag different from `<html>` with the `"lang"` or `"xml:lang"` attribute (**Set1** is empty)

#### Passed

All the lang declarations are valid and well-formed (**Test1** AND **Test2** return true for all elements of **Set1**)

#### Failed

At least one lang declaration is valid or badly-formed (**Test1** OR **Test2** return false for at least one element of **Set1**)



##  TestCases 

[TestCases files for rule 8.8.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080801/) 


