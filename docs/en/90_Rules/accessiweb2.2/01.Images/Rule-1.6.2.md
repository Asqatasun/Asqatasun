## Summary

This test consists in checking whether an informative object image has a detailed description if necessary

## Business description

Criterion : 1.6

Test : [1.6.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-2)

Test description :

Does each `<object>` image that conveys information (`<object>` tag with the attribute type="image/..."), that requires a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee),pass one of the conditions below?

-   Between `<object>` and `</object>` there is a reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) available on the page
-   An [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) (via an [url](http://www.accessiweb.org/index.php/glossary-76.html#mUrl)
    or an anchor) allowing to access to the content of the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is available

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `<object>` tags with a `type` attribute that starts with "image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<object>` tag with a `type` attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
