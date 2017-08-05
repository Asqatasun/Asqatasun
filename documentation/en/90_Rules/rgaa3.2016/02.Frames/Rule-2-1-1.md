# RGAA 3.2016 - Rule 2.1.1

## Summary
This test consists in checking the presence of the `"title"` attribute for all the `<iframe>` elements of the page.

## Business description

### Criterion
[2.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-2-1)

### Test
[2.1.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-2-1-1)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#cadre-en-ligne">cadre en ligne</a> (balise <code lang="en">iframe</code>) a-t-il un attribut <code lang="en">title</code>&nbsp;?</div>

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

All the `<iframe>` tags of the page (css selector : `iframe`)

### Process

#### Test1 :

For each element of **Set1**, test the presence of the `"title"` attribute.

For each occurrence of false-result of **Test1**, raise a MessageA

##### MessageA: Title attribute is missing

-   code : TitleAttributeMissing
-   status: Failed
-   parameter : `"src"` attribute, tag name, snippet
-   present in source : yes

### Analysis

#### Passed

All the `<iframe>` tags have an `"title"` attribute (**Test1** returns true for all the elements of **Set1**)

#### Failed

At least one `<frame>` tag has no `"title"` attribute (**Test1** returns false for at least one element of **Set1**)

#### Not Applicable

The page has no `<iframe>` tag (**Set1** is empty)



##  TestCases

[TestCases files for rule 2.1.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule020101/)


