# RGAA 4.0 — Rule 8.8.1

## Summary

This test consists in checking whether each change of language is valid and relevant

## Business description

### Criterion

[8.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-8)

### Test

[8.8.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-8-1)

### Description

> Pour chaque page web, le code de langue de chaque [changement de langue](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#changement-de-langue) vérifie-t-il ces conditions ?
> 
> * Le code de langue est valide.
> * Le code de langue est pertinent.

### Level

**AA**


## Technical description

### Scope

**Page**

### Decision level

**Decidable**


## Algorithm

### Selection

### Set1

All the tags different from `<html>` that have a `lang` or a `xml:lang` attribute

### Process

#### Test1

We extract the language code from the `lang` or the `xml:lang`
attributes from elements of **Set1**. If these attributes are both set and
different, we keep the `xml:lang` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `lang` attribute instead.

For each extracted lang attribute, we check its validity regarding the following regexp : `\\w{2,3}(\\-\\w{2,})?$`

For each invalid element, raise a messageA.

###### MessageA : Malformed Language Declaration

-   code : MalformedLanguageDeclaration
-   status: Failed
-   parameter : extracted lang
-   present in source : yes

#### Test2

We extract the language code from the `lang` or the "xml:lang"
attributes from elements of **Set1**. If these attributes are both set and
different, we keep the `xml:lang` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `lang` attribute instead.

For each extracted lang attribute, we check its validity regarding the
"ValidLanguageCode" whitelist

-   Used nomenclature : "ValidLanguageCode", "XhtmlDoctypeDeclarations"
-   Reference : All the valid language codes recommanded in
    [http://www.loc.gov/standards/iso639-2/php/code_list.php](http://www.loc.gov/standards/iso639-2/php/code_list.php "http://www.loc.gov/standards/iso639-2/php/code_list.php")
    and all the xhtml doctypes declarations recommanded in
    [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

For each invalid element, raise a messageA.

###### MessageB : Wrong Language Declaration

-   code : WrongLanguageDeclaration
-   status: Failed
-   parameter : extracted lang
-   present in source : yes

#### Test3

We extract the language code from the `lang` or the `xml:lang`
attributes from elements of **Set1**. If these attributes are both set and
different, we keep the `xml:lang` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `lang` attribute instead.

For each extracted text, we detect the language and compare with the declaration of the lang attribute.

If the detected language and the declared language are different, if the size of the extracted text is superior to 20 words, raise a messageA. Raise a MessageB instead.

If the detected language and the declared language are identical, but the size of the extracted text is inferior to 20 words, raise a messageC.

###### MessageA : Irrelevant Language Declaration

-   code : IrrelevantLanguageDeclaration
-   status: Failed
-   parameter : extracted lang, declared lang, extracted text, snippet
-   present in source : yes

###### MessageB : Suspected Irrelevant Language Declaration

-   code : SuspectedIrrelevantLanguageDeclaration
-   status: Pre-Qualified
-   parameter : extracted lang, declared lang, extracted text, snippet
-   present in source : yes

###### MessageC : Suspected relevant Language Declaration

-   code : SuspectedRelevantLanguageDeclaration
-   status: Pre-Qualified
-   parameter : extracted lang, declared lang, extracted text, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no tag different from `<html>` with the `lang` or `xml:lang` attribute (**Set1** is empty)

#### Passed

All the lang declarations are valid and identical to the detected lang and all the tested text size are superior to 20 words

#### Failed

At least one lang declaration is invalid or badly-formed (**Test1** OR **Test2** return false for at least one element of **Set1**)

or 
 
At least one lang declaration is different from the detected lang, with a text size superior to 20 words

#### Pre-Qualified

In all other cases

## Files

- [TestCases files for rule 8.8.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080801/)
- [Unit test file for rule 8.8.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080801Test.java)
- [Class file for rule 8.8.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080801.java)


