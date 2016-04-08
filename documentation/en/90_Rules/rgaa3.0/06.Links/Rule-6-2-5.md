# RGAA 3.0 -  Rule 6.2.5

## Summary

This test consists in checking whether the title of each svg link is relevant.

## Business description

### Criterion

[6.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-2)

###Test

[6.2.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-2-5)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienVectoriel">lien vectoriel</a> ayant un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTitreLien">titre de lien</a> (attribut `title`), le contenu de cet attribut est-il pertinent ?

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

All the elements of **Set2** with a not empty text and a `"title"` attribute

### Process

##### Test1

For each element of **Set3**, we check whether the `"title"` attribute is not empty

For each element returning false in **Test1**, raise a MessageA.

##### Test2

For each element of **Set3**, we check whether the `"title"` attribute doesn't only contain non alphanumerical characters.

For each element returning false in **Test2**, raise a MessageB

##### Test3

For each element of **Set3**, we check whether the `"title"` attribute value doesn't belong to the text link blacklist.

For each element returning false in **Test3**, raise a MessageB

##### Test4

For each element of **Set3**, we check whether the `"title"` attribute is not striclty identical to the link text.

For each element returning false in **Test4**, raise a MessageB

##### Test5

For each element of **Set3**, we check whether the `"title"` attribute contains the link text and more.

For each element returning true in **Test5**, raise a MessageC

For each element returning false in **Test5**, raise a MessageD

##### MessageA : Empty `"title"` attribute of link

-   code : EmptyLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageB : Not Pertinent link `"title"` attribute

-   code : NotPertinentLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageC : Suspected Pertinent link `"title"` attribute

-   code : SuspectedPertinentLinkTitle
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageD : Suspected not Pertinent link `"title"` attribute

-   code : SuspectedNotPertinentTitleAttribute
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no svg link with a `"title"` attribute (**Set2** is empty)

#### Failed

-   At least one svg link has an empty title attribute (**Test1** returns false for at least one element)
-   At least one svg link has title attribute only composed of non alphanumerical characters (**Test2** returns false for at least one element)
-   At least one svg link has a title attribute which is blacklisted (**Test3** returns false for at least one element)
-   At least one svg link has a title attribute identical to the link text (**Test4** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

We assume here that the svg links have only one child of type `<svg>`

**Definition of not-pertinent link title attribute :**

A link `"title"` attribute is regarded as not-pertinent in the following cases :

-   the link `"title"` attribute is empty
-   the link `"title"` attribute is identical to the link text
-   the link `"title"` attribute is blacklisted (regarding the **LinkTextBlacklist** nomenclature)
-   the link `"title"` attribute only contains not alphanumerics characters
