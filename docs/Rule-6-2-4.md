# Rule 6.2.4

## Summary

This test consists in checking whether the title of each composite link is relevant.

## Business description

### Criterion

[6.2](https://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-2)

### Test

[6.2.4](https://references.modernisation.gouv.fr/referentiel-technique-0#test-6-2-4)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#lien-composite">lien composite</a> ayant un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#content-titre-de-lien">titre de lien</a> (attribut `title`), le contenu de cet attribut est-il pertinent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1

All the `<a>` tags with a `"href"` attribute, with children (a[href]:has(*) )

#### Set2

All the elements of **Set1** with own text or with more than 1 child
or with only one child not of type img or object (where "img ,
object[type^=image], object[data^=data:image], object[data$=png],
object[data$=jpeg], object[data$=jpg],object[data$=bmp],
object[data$=gif]" returns empty)

#### Set3

All the elements of **Set2** with a not empty text and a `"title"` attribute

### Process

##### Test1

For each element of **Set3**, we check whether the `"title"` attribute is not
empty

For each element returning false in **Test1**, raise a MessageA

##### Test2

For each element of **Set3**, we check whether the `"title"` attribute doesn't
only contain non alphanumerical characters.

For each element returning false in **Test2**, raise a MessageB

##### Test3

For each element of **Set3**, we check whether the `"title"` attribute value
doesn't belong to the text link blacklist.

For each element returning false in **Test3**, raise a MessageB

##### Test4

For each element of **Set3**, we check whether the `"title"` attribute is not
striclty identical to the link text.

For each element returning false in **Test4**, raise a MessageB

##### Test5

For each element of **Set3**, we check whether the `"title"` attribute
contains the link text and more.

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

##### Used nomenclature

-   LinkTextBlacklist

### Analysis

#### Not Applicable

-   The **Set3** is empty

#### Failed

-   At least one element of the **Set3** has an empty `"title"` attribute text content which is blacklisted (**Test1** returns false for at least one element)
-   At least one element of the **Set3** has `"title"` attribute only composed of non alphanumerical characters (**Test2** returns false for at least one element)
-   At least one element of the **Set3** has a `"title"` attribute which is blacklisted (**Test3** returns false for at least one element)
-   At least one element of the **Set3** has a `"title"` attribute identical to the link text (**Test4** returns false for at least one element)

#### Pre-qualified

-   In all other cases

## Notes

All the links that have children different from img or object, are
considered as combined links.

examples :

-   `<a href="/target.html">` `<span>` my link`</span>``</a>`
-   `<a href="/target.html">` my `<span>`my link`</span>``</a>`
-   `<a href="/target.html">` `<p>`my link`</p>``</a>`
-   `<a href="/target.html">` my `<p>` link`</p>``</a>`

A link title is regarded as not-pertinent in the following cases :

-   the link title is empty
-   the link title is identical to the link text
-   the link only contains not alphanumerics characters

