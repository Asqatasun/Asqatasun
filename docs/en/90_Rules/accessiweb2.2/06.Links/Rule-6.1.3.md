# AccessiWeb 2.2 - Rule 6.1.3

## Summary

This test consists in checking whether the context of each clickable
area is enough explicit to understand the purpose and the target

## Business description

Criterion : 6.1

Test : [6.1.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-1-3)

Test description :

Does each link such as [clickable
area](http://accessiweb.org/index.php/glossary-76.html#mZoneCliquable)
(content of the alt attribute of an area tag) pass one of the conditions
below (except in [special
cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit6- "Special cases for criterion 6.1"))?

-   The [link
    text](http://accessiweb.org/index.php/glossary-76.html#mIntituleLien)
    alone allows to understand the link purpose and target
-   The [link
    context](http://accessiweb.org/index.php/glossary-76.html#mContexteLien)
    allows to understand the link purpose and target

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

##### Set1 :

All the `area` tags with a "href" attribute and a "alt attribute (
area[href][alt] )

##### Set2 :

All the elements of Set1 with a not empty text and without context
(assuming [the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

##### Set3 :

All the elements of Set2 with a not empty text, with a context (assuming
[the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

in other words :

size(Set1) = size(Set2) + size(Set3)

### Process

##### Test1

For each element of Set2, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test1, raise a Message1, raise a
Message2 instead

##### Test2

For each element of Set3, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test2, raise a Message3, raise a
Message4 instead

##### Test3

For each element of Set2, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test3, raise a Message1, raise a
Message2 instead

##### Test4

For each element of Set3, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test4, raise a Message3, raise a
Message4 instead

##### Message1 : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message2 : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message3 : Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message4 : Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

Set1 is empty

#### Failed

Test1 returns false for at least one element (At least one element of
the Set2 has a text content which is blacklisted)

#### Pre-qualified

In all other cases

## Notes

No notes yet for that rule
