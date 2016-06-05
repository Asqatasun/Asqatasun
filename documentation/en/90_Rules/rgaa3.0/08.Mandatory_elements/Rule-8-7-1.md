# RGAA 3.0 -  Rule 8.7.1

## Summary

This test consists in checking whether each textual node is identified by a lang declaration

## Business description

### Criterion

[8.7](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-7)

### Test

[8.7.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-7-1)

### Description
On each Web page, does
    each text written in a language differing from the <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mLangueDefaut">
  default human language</a> meet one of the following
    conditions (except
    in <a title="Particular cases for criterion 8.7" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit8-7">particular cases</a>)?
    <ul><li> The human language identification is provided via
   the element containing the text</li>
  <li> The human language identification is provided via
   one of the parent elements</li>
    </ul> 


### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1

All the textual nodes of the page.

### Process

We start by extracting the default lang of the page.

#### Test1

Test whether the detected lang for each textual node is identical to the default lang of the page.

For each occurrence of false-result of **Test1**, raise a MessageA

#### Test2

Test whether the detected lang for each textual node is identical to the lang overidden by the current element or one of its parent.

For each occurrence of false-result of **Test2**, raise a MessageA

#### Test3

Test whether the size of each element of **Set1** is superior to 20 words

For each occurrence of false-result of **Test3**, raise a MessageB

###### MessageA : Lang change missing on element or one its parents

-   code : LangChangeMissingOnElementOrOneOfItsParent
-   status: Failed
-   parameter : default lang, current lang (if different from the default), detected lang, extracted text, snippet
-   present in source : yes

###### MessageB : Check manually short texts

-   code : CheckManuallyShortText
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

### Analysis

#### Not Applicable

No default lang declaration is done on the page

#### Passed 

All the textual nodes have a detected lang identical to the declared one

#### Failed

At least one textual node have a detected lang different from the declared one

#### Pre-qualified

The page contains at least one textual node with a size inferior to 20 words




##  TestCases 

[TestCases files for rule 8.7.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080701/) 


