## Summary

This test consists in detecting the embedded images on the page.

## Business description

Criterion : 1.9

Test : [1.9.6](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-9-6)

Test description :

Each embedded image of text (embed tag with the attribute type=&quot;image/...&quot;) must be replaced with styled text. Does this rule have been followed (except in special cases)?

Level : [Gold](/en/category/rules-design/accessiweb-11/level/gold)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

**Set1**

All the `embed` tags with a `type` attribute that starts with "image" of the page

### Process

**Test1**

For each element of Set1, raise a MessageA

##### MessageA : Check manually the elements of the scope

-   code : ManualCheckOnElements
-   status: Pre-qualified
-   parameter : `src` attribute, Snippet
-   present in source : yes

### Analysis

#### Not Applicable

Set1 is empty (the page has no `embed` tag with a `type` attribute that starts with "image")

#### Pre-qualified

In all other cases
