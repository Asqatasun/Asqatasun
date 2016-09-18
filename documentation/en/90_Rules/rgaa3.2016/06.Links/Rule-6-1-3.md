# RGAA 3.2016 - Rule 6.1.3

## Summary
This test consists in checking whether the context of each combined link is enough explicit to understand the purpose and the target

## Business description

### Criterion
[6.1](https://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-1)

### Test
[6.1.3](https://references.modernisation.gouv.fr/referentiel-technique-0#test-6-1-3)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lien-composite">lien composite</a> v&#xE9;rifie-t-il une de ces conditions (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-6-1,6-3" title="Cas particuliers pour le crit&#xE8;re 6.1">cas particuliers</a>)&nbsp;? <ul><li>L&#x2019;<a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#intitul-de-lien">intitul&#xE9; de lien</a> seul permet d&#x2019;en comprendre la fonction et la destination&nbsp;;</li> <li>Le <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#contexte-du-lien">contexte du lien</a> permet d&#x2019;en comprendre la fonction et la destination.</li> </ul></div>

### Level
**A**

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

All the elements of **Set1** with own text or with more than 1 child or with only one child not of type `<img>`, `<canvas>`, `<svg>`, `<object>` (where "img , object[type^=image], object[data^=data:image], object[data$=png], object[data$=jpeg], object[data$=jpg],object[data$=bmp], object[data$=gif], canvas, svg" returns empty)

##### Set3

All the elements of **Set2** with a not empty text and without context (assuming [the definition of a link context in Rgaa3.0](https://references.modernisation.gouv.fr/referentiel-technique-0#contexte-du-lien))

##### Set4

All the elements of **Set2** with a not empty text, with a context (assuming [the definition of a link context in Rgaa3.0](https://references.modernisation.gouv.fr/referentiel-technique-0#contexte-du-lien))

in other words :

size(**Set2**) = size(**Set3**) + size(**Set4**)

### Process

##### Test1

For each element of **Set3**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test1**, raise a MessageA, raise a MessageB instead

##### Test2

For each element of **Set4**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test2**, raise a MessageC, raise a MessageD instead

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

##### MessageC : Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageD : Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no combined link (**Set2** is empty)

#### Failed

At least one combined link without context has a text content which is blacklisted or only composed of non-alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

All the links that have children different from `<img>`, `<object>`, `<canvas>`, or `<svg>` are considered as combined links.

examples :

-   `<a href="/target.html">` `<span>` my link`</span>` `</a>`
-   `<a href="/target.html">` my `<span>`my link`</span>` `</a>`
-   `<a href="/target.html">` `<p>`my link`</p>` `</a>`
-   `<a href="/target.html">` my `<p>` link`</p>` `</a>`

**Definition of not-pertinent link title :**

A link title is seen as not-pertinent in the following cases :

-   the link title is blacklisted (regarding the LinkTextBlacklist nomenclature)
-   the link only contains not alphanumerics characters




##  TestCases

[TestCases files for rule 6.1.3](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule060103/)


