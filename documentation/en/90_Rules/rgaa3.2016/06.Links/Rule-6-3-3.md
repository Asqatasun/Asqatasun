# RGAA 3.2016 - Rule 6.3.3

## Summary
This test consists in checking whether the text of each combined link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion
[6.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-6-3)

### Test
[6.3.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-6-3-3)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#lien-composite">lien composite</a> (contenu texte et de l&#x2019;attribut <code lang="en">alt</code>) est-il <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#lien-explicite-hors-contexte">explicite hors contexte</a> (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/cas-particuliers.html#cp-6-1,6-3" title="Cas particuliers pour le crit&#xE8;re 6.3">cas particuliers</a>)&nbsp;?</div>

### Level
**AAA**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<a>` tags with a `"href"` attribute, with children (a[href]:has(*) )

##### Set2

All the elements of **Set1** with own text or with more than 1 child
or with only one child not of type `<img>`, `<canvas>`, `<svg>` or `<object>` (where "img ,
object[type^=image], object[data^=data:image], object[data$=png],
object[data$=jpeg], object[data$=jpg],object[data$=bmp],
object[data$=gif], canvas, svg" returns empty)

##### Set3

All the elements of **Set2** with a not empty text.

### Process

##### Test1

For each element of **Set3**, we check whether the link content doesn't belong to the text link blacklist.

For each element returning false in **Test1**, raise a MessageA, raise a MessageB instead.

##### Test2

For each element of **Set3**, we check whether the link content doesn't only contain non alphanumeric characters.

For each element returning false in **Test2**, raise a MessageA, raise a MessageB instead.

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

##### Not Applicable

The page has no combined link (**Set1** is empty)

##### Failed

At least one combined link has a text content which is blacklisted or that only contains non alphanumerical characters (**Test1** OR **Test2** returns false for at least one element)

##### Pre-Qualified

In all other cases

## Notes

All the links that have children different from `<img>`, `<canvas>`, `<svg>` or `<object>`, are considered as combined links.



##  TestCases

[TestCases files for rule 6.3.3](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule060303/)


