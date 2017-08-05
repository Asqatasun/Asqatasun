# RGAA 3.2016 - Rule 6.5.1

## Summary
This test checks whether the page contains empty links.

## Business description

### Criterion
[6.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-6-5)

### Test
[6.5.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-6-5-1)

### Description
<div lang="fr">Dans chaque page Web, chaque lien (balise <code lang="en">a</code> avec un attribut <code lang="en">href</code>), &#xE0; l&#x2019;exception des <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#ancre">ancres</a>, a-t-il un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#intitul-de-lien">intitul&#xE9;</a> entre <code lang="en">&lt;a&gt;</code> et <code lang="en">&lt;/a&gt;</code>&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Decidable**

## Algorithm

### Selection

#### Set1** 

All the `<a>` tags of the page that are not an anchor (
a:not([name]):not([id]) )

#### Set2

All the tags from **Set1** that have an empty text (including
children text) and that have not children with a not empty `"alt"`
attribute

### Process

For each element of **Set2**, raise a MessageA

##### MessageA : Empty link

-   code : EmptyLink
-   status: Failed
-   parameter : `"href"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `<a>` tag (**Set1** is empty)

#### Passed

The page only doesn't contain empty links (**Set2** is empty)

#### Failed

The page only contains empty links (**Set2** is not empty)

## Notes

A `<a>` tag is seen as an anchor if and only if it has either a "name" or
an "id" attribute (assuming [the definition of an anchor in Rgaa3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#content-ancre))



##  TestCases

[TestCases files for rule 6.5.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule060501/)


