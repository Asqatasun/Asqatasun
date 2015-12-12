# AccessiWeb 2.2 - Rule 9.1.4

## Summary

This test consists in checking whether each heading of the page is
relevant

## Business description

Criterion : 9.1

Test : [9.1.4](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-9-1-4)

Test description :

On each Web page, is each
[heading](http://accessiweb.org/index.php/glossary-76.html#mTitre) (h
tag) relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

Set1 : All the headings elements of the page (h1, h2, h3, h4, h5, h6)

### Process

##### Test1

For each element of Set1, we check whether the content of the tag is not
empty

For each element returning false in Test1, raise a MessageA

##### Test2

For each element of Set1, we check whether the content of the tag
doesn't only contain non alphanumerical characters.

For each element returning false in Test2, raise a MessageA, otherwiser
raise a MessageB

##### MessageA : Not pertinent heading

-   code : NotPertinentHeading
-   status: Failed
-   parameter : tag text, tag name, snippet
-   present in source : yes

##### MessageB : Check heading pertinence

-   code : CheckHeadingPertinence
-   status: NMI
-   parameter : tag text, tag name, snippet
-   present in source : yes

NotPertinentHeading

### Analysis

#### Not Applicable

-   The Set1 is empty

#### Failed

-   Test1 returns false for at least one element (At least one element
    of the Set1 has an empty content)
-   Test2 returns false for at least one element (At least one element
    of the Set1 has a content only composed of non alphanumerical
    characters)

#### Pre-qualified

-   In all other cases

#### Not Applicable

-   The Set2 is empty

## Notes

No notes yet for that rule
