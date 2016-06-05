# RGAA 3.0 -  Rule 6.1.4

## Summary

This test consists in checking whether the context of each combined link is enough explicit to understand the purpose and the target

## Business description

### Criterion

[6.1](https://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-1)

### Test

[6.1.4](https://references.modernisation.gouv.fr/referentiel-technique-0#test-6-1-4)

### Description
Does each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mLienComposite">combined
  link</a> meet one of the following conditions (except
    in <a title="Particular cases for criterion 6.1" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit6-">particular
  cases</a>)?
    <ul><li>The <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mIntituleLien">link
    text</a> alone is sufficient to understand the link
   purpose and target</li>
  <li>The <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mContexteLien">link
    context</a> is sufficient to understand the link purpose
   and target</li>
    </ul> 


### Level

**A**

## Technical description

### Scope

**page**

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

[TestCases files for rule 6.1.4](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060104/) 


