# AccessiWeb 2.2 - Rule 6.3.3

## Summary

This test consists in checking whether the text of each clickable area
is enough explicit to understand the purpose and the target out of its
context.

## Business description

Criterion : 6.3

Test : [6.3.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-3-3)

Test description : Is each [link
text](http://accessiweb.org/index.php/glossary-76.html#mIntituleLien)
such as a [clickable
area](http://accessiweb.org/index.php/glossary-76.html#mZoneCliquable)
(content of the alt attribute of an area tag) [explicit out of
context](http://accessiweb.org/index.php/glossary-76.html#mLienHorsContexte)
([except in special
cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit6- "Special cases for criterion 6.3"))?

Level : Gold

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

**Set1 :**

All the `area` tags with a "href" attribute and a "alt attribute (
area[href][alt] )

**Set2 :**

All the elements of Set1 with a not empty `alt` attribute

### Process

##### Test1

For each element of Set2, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test1, raise a MessageA, raise a
MessageB instead

##### Test2

For each element of Set2, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test2, raise a MessageA, raise a
MessageB instead

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

### Analysis

**NA :**

Set1 is empty (the page has no clickable area)

**Failed :**

Test1 OR Test2 returns false for at least one element (At least one
element of the Set2 has an `alt` attribute" content which is blacklisted
or that only contains non alphanumerical characters)

**NMI :\
**

In all other cases

## Notes

No notes yet for that rule
