## Summary

This test consists in checking whether the page doesn't contain
attributes serving for the presentation of the information in the source
code.

## Business description

Criterion : 10.1

Test : [10.1.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-1-2)

Test description :

On each Web page, attributes serving for [information
presentation](http://www.accessiweb.org/index.php/glossary-76.html#mPresInfo)
must not be available in the page source code. Does this rule have been
followed?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

Set1 : All the tags with attributes defined as deprecated by the W3C.

-   Used nomenclature : "DeprecatedPresentationTags"
-   Reference : deprecated column in
    [http://www.w3.org/TR/html4/index/attributes.html](http://www.w3.org/TR/html4/index/attributes.html "http://www.w3.org/TR/html4/index/attributes.html")
    (except "heigth" and `weigth` attributes for `img` and `svg` tags)

### Process

The selection handles the process.

### Analysis

-   Passed : The selection is empty.

-   Failed : the selection is not empty (the document contains tags with
    deprecated atttributes)

-   Message :

1.  code : TagWithDeprecatedRepresentationAttributeFound
2.  status: Failed
3.  case : For each selected element
4.  parameter : tag name
5.  present in source : yes

## Notes

This rule may be tested for each kind of doctype. The current
implementation only tests the forbidden tags specified by w3c for HTML
4.
