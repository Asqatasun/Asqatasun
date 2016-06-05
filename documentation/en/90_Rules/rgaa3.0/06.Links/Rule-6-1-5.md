# RGAA 3.0 -  Rule 6.1.5

## Summary

This test consists in checking whether the context of each svg link is enough explicit to understand the purpose and the target.

## Business description

### Criterion

[6.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-1)

###Test

[6.1.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-1-5)

### Description
Does each&#xA0; <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mVectorLink">vector
  link</a> meet one of the following conditions (except in <a title="Particular cases for criterion 6.1" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit6-">particular
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

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<a>` tags with a `"href"` attribute, with children (a[href]:has(*) )

##### Set2

All the elements of **Set1** without own text and with only one child of type `<svg>`(svg)

##### Set3

All the elements of **Set2** with a not empty text and without context (assuming [the definition of a link context in Rgaa3.0](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mContexteLien))

##### Set4

All the elements of **Set2** with a not empty text, with a context (assuming [the definition of a link context in Rgaa3.0](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mContexteLien))

in other words :

size(**Set2**) = size(**Set3**) + size(**Set4**)

### Process

##### Test1

For each element of **Set3**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test1**, raise a MessageA, raise a MessageB instead.

##### Test2

For each element of **Set4**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test2**, raise a MessageC, raise a MessageD instead.

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

The page has no svg link (**Set2** is empty)

#### Failed

At least one svg link without context has a text content which is blacklisted or only composed of non-alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

We assume here that the svg links have only one child of type `<svg>`

**Definition of not-pertinent link title :**

A link title is seen as not-pertinent in the following cases :

-   the link title is blacklisted (regarding the **LinkTextBlacklist** nomenclature)
-   the link only contains not alphanumerics characters



##  TestCases 

[TestCases files for rule 6.1.5](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060105/) 


