### Summary

This test consists in checking whether the title of each clickable area
is relevant.

### Business description

Criterion : 6.2

Test : [6.2.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-2-3)

Test description :

For each [clickable area](index.php/glossary-76.html#mZoneCliquable)
(area tag) with a [link title](index.php/glossary-76.html#mTitreLien)
(title attribute), is the content of this attribute relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Set1 : All the <area\> tags with a "href" attribute and a "alt attribute
( area[href][alt] )

Set2 : All the elements of Set1 with a not empty alt attribute and a
title attribute

#### Process

##### Test1

For each element of Set2, we check whether the "title" attribute is not
empty

For each element returning false in Test1, raise a Message 1

##### Test2

For each element of Set2, we check whether the "title" attribute doesn't
only contain non alphanumerical characters.

For each element returning false in Test2, raise a Message 2

##### Test3

For each element of Set2, we check whether the "title" attribute value
doesn't belong to the text link blacklist.

For each element returning false in Test3, raise a Message 2

##### Test4

For each element of Set2, we check whether the "title" attribute is not
striclty identical to the link text.

For each element returning false in Test4, raise a Message 3

##### Test5

For each element of Set2, we check whether the "title" attribute
contains the link text and more.

For each element returning true in Test5, raise a Message 3

For each element returning false in Test5, raise a Message 4

##### Message 1:Empty title attribute of link

-   code : EmptyLinkTitle
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 2: Not Pertinent link Title Attribute

-   code : NotPertinentLinkTitle
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 3: Suspected Pertinent link Title Attribute

-   code : SuspectedPertinentLinkTitle
-   status: NMI
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 4: Suspected not Pertinent link Title Attribute

-   code : SuspectedNotPertinentTitleAttribute
-   status: NMI
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Used nomenclature

-   LinkTextBlacklist

#### Analysis

##### NA

-   The Set2 is empty

##### Failed

-   Test1 returns false for at least one element (At least one element
    of the Set2 has an empty title attribute text content which is
    blacklisted)
-   Test2 returns false for at least one element (At least one element
    of the Set2 has title attribute only composed of non alphanumerical
    characters)
-   Test3 returns false for at least one element (At least one element
    of the Set2 has a title attribute which is blacklisted)

##### NMI

-   In all other cases

### Notes

1.  We assume here that the textual alternative of the area corresponds
    to the link text.
2.  A link title is regarded as not-pertinent in the following cases :

-   the link title is empty
-   the link only contains not alphanumerics characters

1.  Due to the "Note 1" of the [definition of a link
    title](http://accessiweb.org/index.php/glossary-76.html#mTitreLien),
    a title attribute identical to the text link is seen as suspected
    passed.

