# RGAA 3.0 -  Rule 6.2.3

## Summary

This test consists in checking whether the title of each clickable area
is relevant.

## Business description

### Criterion

[6.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-2)

### Test

[6.2.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-2-3)

### Description

pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#content-zone-cliquable">zone cliquable</a> (balise `area`) ayant un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTitreLien">titre de lien</a> (attribut `title`), le contenu de cet attribut est-il pertinent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<area>` tags with a `"href"` attribute and a `"href"` attribute ( area[href][alt] )

#### Set2

All the elements of **Set1** with a not empty alt attribute and a `"title"` attribute

### Process

##### Test1

For each element of **Set2**, we check whether the `"title"` attribute is not
empty

For each element returning false in **Test1**, raise a MessageA

##### Test2

For each element of **Set2**, we check whether the `"title"` attribute doesn't
only contain non alphanumerical characters.

For each element returning false in **Test2**, raise a MessageB

##### Test3

For each element of **Set2**, we check whether the `"title"` attribute value
doesn't belong to the text link blacklist.

For each element returning false in **Test3**, raise a MessageB

##### Test4

For each element of **Set2**, we check whether the `"title"` attribute is not
striclty identical to the link text.

For each element returning false in **Test4**, raise a MessageC

##### Test5

For each element of **Set2**, we check whether the `"title"` attribute
contains the link text and more.

For each element returning true in **Test5**, raise a MessageC

For each element returning false in **Test5**, raise a MessageD

##### MessageA:Empty `"title"` attribute of link

-   code : EmptyLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageB: Not Pertinent link `"title"` attribute

-   code : NotPertinentLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageC: Suspected Pertinent link `"title"` attribute

-   code : SuspectedPertinentLinkTitle
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageD: Suspected not Pertinent link `"title"` attribute

-   code : SuspectedNotPertinentTitleAttribute
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no clickable area (**Set2** is empty)

#### Failed

-   At least one clickable area has an empty title attribute (**Test1** returns false for at least one element)
-   At least one clickable area has title attribute only composed of non alphanumerical characters (**Test2** returns false for at least one element)
-   At least one clickable area a title attribute which is blacklisted (**Test3** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

1.  We assume here that the textual alternative of the area corresponds
    to the link text.

2. **Definition of not-pertinent link title attribute :**

A link title attribute is regarded as not-pertinent in the following cases :

-   the `"title"` attribute is empty
-   the `"title"` attribute is blacklisted (regarding the **LinkTextBlacklist** nomenclature)
-   the `"title"` attribute only contains not alphanumerics characters

3.  Due to the "Note 1" of the [definition of a link title](http://references.modernisation.gouv.fr/referentiel-technique-0#title-titre-de-lien),
    a `"title"` attribute identical to the text link is seen as suspected passed.




##  TestCases 

[TestCases files for rule 6.2.3](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060203/) 


