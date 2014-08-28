### Summary

This test consists in checking whether the context of each text link is
enough explicit to understand the purpose and the target

### Business description

Criterion : 6.1

Test : [6.1.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-1-1)

Test description :

Does each [link
context](http://accessiweb.org/index.php/glossary-76.html#mContexteLien)
pass one of the conditions below (except in [special
cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit6- "Special cases for criterion 6.1"))?

-   The [link
    text](http://accessiweb.org/index.php/glossary-76.html#mIntituleLien)
    alone allows to understand the link purpose and target
-   The [link
    context](http://accessiweb.org/index.php/glossary-76.html#mContexteLien)
    allows to understand the link purpose and target

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

##### Set1 :

All the <a\> tags with a "href" attribute, without children (
a[href]:not(:has(\*)) )

##### Set2 :

All the elements of Set1 with a not empty text and without context
(assuming [the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

##### Set3 :

All the elements of Set1 with a not empty text, with a context (assuming
[the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

in other words :

size(Set1) = size(Set2) + size(Set3)

#### Process

##### Test1

For each element of Set2, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test1, raise a Message 1, raise a
Message 2 instead

##### Test2

For each element of Set3, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test2, raise a Message 3, raise a
Message 4 instead

##### Test3

For each element of Set2, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test3, raise a Message 1, raise a
Message 2 instead

##### Test4

For each element of Set3, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test4, raise a Message 3, raise a
Message 4 instead

##### Message 1: Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 2: Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 3: Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message 4: Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

#### Analysis

##### NA

Set1 is empty

##### Failed

Test1 returns false for at least one element (At least one element of
the Set2 has a text content which is blacklisted)

##### NMI

In all other cases

### Notes

We assume here that the links are only composed of a text. (<a
href="http://www.tanaguru.org/target.html"\> my link</a\>)

All the links that have children different from img or object, are
considered as combined links
