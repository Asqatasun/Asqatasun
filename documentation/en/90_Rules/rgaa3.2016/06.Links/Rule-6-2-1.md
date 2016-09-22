# RGAA 3.2016 - Rule 6.2.1

## Summary
This test consists in checking whether the title of each text link is
relevant.

## Business description

### Criterion
[6.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-6-2)

### Test
[6.2.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-6-2-1)

### Description
<div lang="fr">Pour chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lien-texte">lien texte</a> ayant un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#titre-de-lien">titre de lien</a> (attribut <code lang="en">title</code>), le contenu de cet attribut est-il pertinent&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

##### Set1 :

All the `<a>` tags with a "href" attribute, without children (a[href]:not(:has(*)) )

##### Set2 :

All the elements of **Set1** with a not empty text and with a title attribute

### Process

##### Test1

For each element of **Set2**, we check whether the "title" attribute is not empty

For each element returning false in **Test1**, raise a MessageA

##### Test2

For each element of **Set2**, we check whether the "title" attribute doesn't only contain non alphanumerical characters.

For each element returning false in **Test2**, raise a MessageB

##### Test3

For each element of **Set2**, we check whether the "title" attribute value doesn't belong to the text link blacklist.

For each element returning false in **Test3**, raise a MessageB

##### Test4

For each element of **Set2**, we check whether the "title" attribute is not strictly identical to the link text.

For each element returning false in **Test4**, raise a MessageB

##### Test5

For each element of **Set2**, we check whether the "title" attribute contains the link text and more.

For each element returning true in **Test4**, raise a MessageC

For each element returning false in **Test4**, raise a MessageD

##### MessageA:Empty title attribute of link

-   code : EmptyLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageB: Not Pertinent link Title Attribute

-   code : NotPertinentLinkTitle
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageC: Suspected Pertinent link Title Attribute

-   code : SuspectedPertinentLinkTitle
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageD: Suspected not Pertinent link Title Attribute

-   code : SuspectedNotPertinentTitleAttribute
-   status: Pre-Qualified
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no textual link with a `"title"` attribute (**Set2** is empty)

#### Failed

-   At least one textual link has an empty title attribute (**Test1** returns false for at least one element)
-   At least one textual link has title attribute only composed of non alphanumerical characters (**Test2** returns false for at least one element)
-   At least one textual link has a title attribute which is blacklisted (**Test3** returns false for at least one element)
-   At least one textual link has a title attribute identical to the link text (**Test4** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

**Definition of not-pertinent link title attribute :**

A link `"title"` attribute is regarded as not-pertinent in the following cases :

-   the link `"title"` attribute is empty
-   the link `"title"` attribute is identical to the link text
-   the link `"title"` attribute is blacklisted (regarding the **LinkTextBlacklist** nomenclature)
-   the link `"title"` attribute only contains not alphanumerics characters



##  TestCases

[TestCases files for rule 6.2.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule060201/)


