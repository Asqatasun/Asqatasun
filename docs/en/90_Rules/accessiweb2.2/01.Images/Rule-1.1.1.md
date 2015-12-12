# AccessiWeb 2.2 - Rule 1.1.1

## Summary

This test consists in checking whether each `img` tag is defined with an `alt` attribute.

## Business description

Criterion : 1.1

Test : [1.1.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-1-1)

Test description :

Does each image (`img` tag) have an `alt` attribute?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

**Set1**

All the `img` tags of the page

### Process

**Test1**

Test the presence of the `alt` attribute for each element of the Set1.
For each element returning false in Test1, raise a MessageA

##### MessageA : Alt missing on image

-   code : AltMissing
-   status: Failed
-   parameter : `src` attribute, Snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `img` tag (Set1 is empty)

#### Failed

At least one `img` tag has no `alt` attribute (Test1 returns failed for at least one element)

#### Passed

All the `img` tags have an `alt` attribute
