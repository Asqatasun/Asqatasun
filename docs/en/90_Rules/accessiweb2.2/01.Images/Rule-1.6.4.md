# AccessiWeb 2.2 - Rule 1.6.4

## Summary

This test consist in checking whether an informative embedded image has a detailed description if necessary

## Business description

Criterion : 1.6

Test : [1.6.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-4)

Test description :

Does each embedded image that conveys information (`<embed>` tag), requiring a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee), pass one of the conditions below? 

-   Between `<noembed>` and `</noembed>` there is a reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) available on the page
-   An [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) (via a [url](http://www.accessiweb.org/index.php/glossary-76.html#mUrl)
    or an anchor) allowing to access to the content of the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is available

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `<embed>` tags

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<embed>` tag)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
