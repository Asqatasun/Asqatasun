# RGAA 3.2016 - Rule 8.7.1

## Summary
This test consists in checking whether each textual node is identified by a lang declaration

## Business description

### Criterion
[8.7](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-8-7)

### Test
[8.7.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-8-7-1)

### Description
<div lang="fr">Dans chaque page Web, chaque texte &#xE9;crit dans une langue diff&#xE9;rente de la <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#langue-par-dfaut">langue par d&#xE9;faut</a> v&#xE9;rifie-t-il une de ces conditions (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-8-7" title="Cas particuliers pour le crit&#xE8;re 8.7">cas particuliers</a>)&nbsp;? <ul><li>L&#x2019;indication de langue est donn&#xE9;e sur l&#x2019;&#xE9;l&#xE9;ment contenant le texte&nbsp;;</li> <li>L&#x2019;indication de langue est donn&#xE9;e sur un des &#xE9;l&#xE9;ments parents.</li> </ul></div>

### Level
**AA**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

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

[TestCases files for rule 8.7.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule080701/)


