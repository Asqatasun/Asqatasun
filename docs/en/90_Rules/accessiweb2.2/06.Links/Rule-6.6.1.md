## Summary

This test checks whether the page contains empty links.

## Business description

Criterion : 6.6

Test : [6.6.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-6-1)

Test description : On each Web page, does each link (a tag), except in [named anchors](index.php/glossary-76.html#mAncreNom), have a text between `<a>` an `</a>`?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

Set1 : All the `<a>` tags of the page that are not an anchor (`a:not([name]):not([id])` )

Set2 : All the tags from Set1 that have an empty text (including children text) and that have not children with a not empty `alt` attribute

### Process

The selection handles the process.

### Analysis

#### Not Applicable

Set1 is empty (The page has no `<a>` tag)

#### Passed

Set2 is empty (The page only contains not empty links)

#### Failed

Set2 is not empty. For each element of Set2, raise a Message1

##### Message 1 : Empty link

-   code : EmptyLink
-   status: Failed
-   case : For each element of Set2
-   parameter : `href` attribute, snippet
-   present in source : yes

## Notes

A `<a>` tag is seen as an anchor if and only if it has either a `name` or an `id` attribute (assuming [the definition of an anchor in AccessiWeb 2.2](http://accessiweb.org/index.php/glossary-76.html#mAncreNom))
