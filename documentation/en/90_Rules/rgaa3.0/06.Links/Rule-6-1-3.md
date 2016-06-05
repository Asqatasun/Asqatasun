# RGAA 3.0 -  Rule 6.1.3

## Summary

This test consists in checking whether the text of each clickable area is enough explicit to understand the purpose and the target.

## Business description

### Criterion

[6.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-1)

### Test

[6.1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-1-3)

### Description
Does each
    link that doubles a <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mZoneCliquable">clickable
  area</a> of a server-side image map meet one
    of the following conditions (except in <a title="Particular cases for criterion 6.1" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit6-">particular
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

All the `<area>` tags with a `"href"` attribute and a `"alt"` attribute (area[href][alt] )

##### Set2

All the elements of **Set1** with a not empty text and without context (assuming [the definition of a link context in Rgaa3.0](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mContexteLien))

##### Set3

All the elements of **Set2** with a not empty text, with a context (assuming [the definition of a link context in Rgaa3.0](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mContexteLien))

In other words :

size(**Set1**) = size(**Set2**) + size(**Set3**)

### Process

##### Test1

For each element of **Set2**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test1**, raise a MessageA, raise a MessageB instead

##### Test2

For each element of **Set3**, we check whether the link content is not pertinent (see Notes about relevancy detection)

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

The page has no clickable area (**Set1** is empty)

#### Failed

At least one clickable area without context has a text content which is blacklisted or only composed of non-alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

**Definition of not-pertinent link title :**

A link title is seen as not-pertinent in the following cases :

-   the link title is blacklisted (regarding the LinkTextBlacklist nomenclature)
-   the link only contains not alphanumerics characters



##  TestCases 

[TestCases files for rule 6.1.3](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060103/) 


