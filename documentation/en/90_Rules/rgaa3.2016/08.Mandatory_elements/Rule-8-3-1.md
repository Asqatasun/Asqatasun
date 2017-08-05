# RGAA 3.2016 - Rule 8.3.1

## Summary
We check whether a language is specified for each textual element of the page

## Business description

### Criterion
[8.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-8-3)

### Test
[8.3.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-8-3-1)

### Description
<div lang="fr">Pour chaque page Web, l&#x2019;indication de <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#langue-par-dfaut">langue par d&#xE9;faut</a> v&#xE9;rifie-t-elle une de ces conditions&nbsp;? <ul><li>L&#x2019;indication de la langue de la page (attribut <code lang="en">lang</code> et/ou <code lang="en">xml:lang</code>) est donn&#xE9;e pour l&#x2019;&#xE9;l&#xE9;ment <code lang="en">html</code>&nbsp;;</li> <li>L&#x2019;indication de la langue de la page (attribut <code lang="en">lang</code> et/ou <code lang="en">xml:lang</code>) est donn&#xE9;e sur chaque &#xE9;l&#xE9;ment de texte ou sur l&#x2019;un des &#xE9;l&#xE9;ments parents.</li> </ul></div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Decidable**

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




##  TestCases

[TestCases files for rule 8.3.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule080301/)


