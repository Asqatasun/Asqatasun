# RGAA 3.0 -  Rule 8.8.2

## Summary

This test consists in checking whether each change of language is valid

## Business description

### Criterion

[8.8](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-8)

###Test

[8.8.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-8-2)

### Description
On each Web page, is each
    change in human language identification (lang and/or
    xml:lang attribute) relevant? 


### Level

**AA**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Set1

All the tags different from `<html>` that have a `"lang"` or a `"xml:lang"` attribute

### Process

#### Test1

We extract the language code from the `"lang"` or the `"xml:lang"`
attributes from elements of **Set2**. If these attributes are both set and
different, we keep the `"xml:lang"` attribute if the doctype of the page
is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep
the `"lang"` attribute instead.

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

The page has no tag different from `<html>` with the `"lang"` or `"xml:lang"` attribute (**Set1** is empty)

#### Passed

All the lang declarations are identical to the detected lang and all the tested text size are superior to 20 words

#### Failed

At least one lang declaration is different from the detected lang, with a text size superior to 20 words

#### Pre-Qualified

In all other cases



##  TestCases 

[TestCases files for rule 8.8.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080802/) 


