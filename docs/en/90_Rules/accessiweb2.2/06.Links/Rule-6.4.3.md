# AccessiWeb 2.2 - Rule 6.4.3

## Summary

This test consists in checking whether each indentical clickable area
have the same purpose and target

## Business description

Criterion : 6.4

Test : [6.4.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-4-3)

Test description :

Does each [identical
link](http://www.accessiweb.org/index.php/glossary-76.html#mLienIdentique)
of type [clickable
area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneCliquable)
have the same purpose and target?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

Set1 : All the clickable area (see [the definition of a clickable
area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneCliquable))
with a "href" attribute, without "title" attribute, without context
(assuming [the definition of a link context in AccessiWeb
2.2](http://www.accessiweb.org/glossaire.html#mContexteLien))
and with an identical link text.

Set2 : All the clickable area with a "href" attribute, a not empty
"title" attribute, without context and with an identical link text
(combination of the link text and the "title" attribute text)

Set3 : All the clickable area with a "href" attribute, with a context
and with an identical link text (combination of the link text and the
"title" attribute text if it exists)

-   used nomenclature : none

-   reference : none

### Process

Test1 : We check whether all the elements of Set1 have an identical
"href" attribute

Test2 : We check whether all the elements of Set2 have an identical
"href" attribute

Test3 : We check whether all the elements of Set3 have an identical
"href" attribute

-   used nomenclature : none

-   reference : none

### Analysis

-   NA : Set1, Set2 and Set3 are empty
-   Failed :

1.  At least one element of the Set1 has a "href" attribute different
    from the others (Test1 returns false for at least one element)
2.  At least one element of the Set2 has a "href" attribute different
    from the others (Test2 returns false for at least one element)

-   NMI : In all other cases

-   Message 1:

1.  code : IdenticalLinkWithDifferentTarget
2.  status: Failed
3.  case : For each element returning false in Test1 and Test2
4.  parameter : tag name
5.  present in source : yes

-   Message 2:

1.  code : SuspectedIdenticalLinkWithDifferentTarget
2.  status: NMI
3.  case : For each element returning true in Test3
4.  parameter : tag name
5.  present in source : yes

## Notes

No notes yet for that rule
