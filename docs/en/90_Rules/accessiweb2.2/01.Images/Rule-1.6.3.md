# AccessiWeb 2.2 - Rule 1.6.3

## Summary

This test consists in checking whether an informative applet image has a detailed description if necessary

## Business description

Criterion : 1.6

Test : [1.6.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-3)

Test description :

Does each [applet image](http://www.accessiweb.org/index.php/glossary-76.html#mImgApplet) that conveys information (applet tag), requiring a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee), pass one of the conditions below?

-   An `alt` attribute containing the reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) just next to the image is available
-   Between `<applet>` and `</applet>` there is a reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) available on the page
-   An [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) (via a [url](http://www.accessiweb.org/index.php/glossary-76.html#mUrl)
    or an anchor) allowing to access to the content of the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is available

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `<applet>` tags

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<applet>` tag)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
