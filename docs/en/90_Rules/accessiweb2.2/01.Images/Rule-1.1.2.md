### Summary

This test consists in checking whether each area of an image map is
defined with an `alt` attribute

### Business description

Criterion : 1.1

Test : [1.1.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-1-2)

Test description :

Does each [area](http://www.accessiweb.org/index.php/glossary-76.html#mZone) (area tag) of an [image map](http://www.accessiweb.org/index.php/glossary-76.html#mImgReactive) have an `alt` attribute?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

All the `area` tags, defined in a `map` tag associated with the `usemap` attribute of an `img` tag

-   used nomenclature : none

-   reference : none

#### Process

Test the presence of an `alt` attribute in each element of the selection set.

-   used nomenclature : none

-   reference : none

#### Analysis

##### NA

-   The page has no `img` tag
-   no `img` tag is defined with an `usemap` attribute

##### Failed

The `map` associated with the `usemap` attribute of a `img` tag has at
least one `area` tag without `alt` attribute

##### Passed

All the `area` tags, defined in a `map` tag associated with the
`usemap` attribute of an `img` tag have an `alt` attribute

### Notes

No notes yet for that rule
