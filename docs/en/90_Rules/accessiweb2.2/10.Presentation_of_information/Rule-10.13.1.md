## Summary

This test consists in checking whether each hidden text of the page can
be displayed or is not supposed to be rendered to the user

## Business description

Criterion : 10.13

Test : [10.13.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-13-1)

Test description :

On each Web page, does each [hidden
text](http://accessiweb.org/glossaire.html#mTexteCache) pass one of the
conditions below?

-   The text is not intended to be rendered by assistive technologies
-   The text is made visible on user action on the element itself or on
    an element before the [hidden
    text](http://accessiweb.org/glossaire.html#mTexteCache)

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1**

All the hidden text elements. Elements from the DOM with :

-   a not empty text and
-   a "display" css property equals to "none" or a "visibility" css
    property equals to "hidden", these properties can be inherited from
    a parent (see Note for more details about the selection)

### Process

The selection handles the process.

For each element of Set1 raise a MessageA

##### Message 1: Hidden text detected

-   code : HiddenTextDetected
-   status: NMI
-   parameter : snippet
-   present in source : yes

### Analysis

**NA**

Set1 is empty (the page has no textual hidden element)

**NMI**

Set1 is not empty (the page has textual hidden elements)

## Notes

The visibility of each element of the page is determined while fetching
the page, through a javascript script.

If the "display" css property is equal to "none" or the "visibility" css
property is equal to "hidden" for an element or one of its parents, this
element is seen as hidden.
