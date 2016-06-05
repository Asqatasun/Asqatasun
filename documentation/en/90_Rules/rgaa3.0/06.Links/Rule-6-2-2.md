# RGAA 3.0 -  Rule 6.2.2

## Summary

This test consists in checking whether the title of each image link is
relevant.

## Business description

### Criterion

[6.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-2)

### Test

[6.2.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-2-2)

### Description
For each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mLienImage">image
  link</a> with a <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTitreLien">link
  title</a> (<code>title</code> attribute), is the content of this
    attribute relevant? 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<a>` tags with a "href" attribute, with children (a[href]:has(*) )

#### Set2

All the elements of **Set1** without own text and with only one child
of type `<img>`, `<canvas>` or `<object>` (img , object[type^=image],
object[data^=data:image], object[data$=png], object[data$=jpeg],
object[data$=jpg],object[data$=bmp], object[data$=gif], canvas) (assuming [the definition of an image link in Rgaa 3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#title-lien-image))

#### Set3

All the elements of **Set2** with a not empty text and a `"title"` attribute 

### Process

##### Test1

For each element of **Set3**, we check whether the `"title"` attribute is not empty

For each element returning false in **Test1**, raise a MessageA

##### Test2

For each element of **Set3**, we check whether the `"title"` attribute doesn't only contain non alphanumerical characters.

For each element returning false in **Test2**, raise a MessageB

##### Test3

For each element of **Set3**, we check whether the `"title"` attribute value doesn't belong to the text link blacklist.

For each element returning false in **Test3**, raise a MessageB

##### Test4

For each element of **Set2**, we check whether the `"title"` attribute is not strictly identical to the link text.

For each element returning false in **Test4**, raise a MessageC

##### Test5

For each element of **Set3**, we check whether the `"title"` attribute contains the link text and more.

For each element returning true in **Test5**, raise a MessageC

For each element returning false in **Test5**, raise a MessageD

##### MessageA: Empty `"title"` attribute of link

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

The page has no image link with a `"title"` attribute (**Set3** is empty)

#### Failed

-   At least one image link has an empty title attribute (**Test1** returns false for at least one element)
-   At least one image link has title attribute only composed of non alphanumerical characters (**Test2** returns false for at least one element)
-   At least one image link has a title attribute which is blacklisted (**Test3** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

1.  We assume here that the textual alternative of the image corresponds to the link text.

2. **Definition of not-pertinent link title attribute :**

A link `"title"` attribute is regarded as not-pertinent in the following cases :

-   the `"title"` attribute is empty
-   the `"title"` attribute is blacklisted (regarding the **LinkTextBlacklist** nomenclature)
-   the `"title"` attribute only contains not alphanumerics characters

3.  Due to the "Note 1" of the [definition of a link
    title](http://references.modernisation.gouv.fr/referentiel-technique-0#content-titre-de-lien),
    a `"title"` attribute identical to the text link is seen as suspected
    passed.




##  TestCases 

[TestCases files for rule 6.2.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060202/) 


