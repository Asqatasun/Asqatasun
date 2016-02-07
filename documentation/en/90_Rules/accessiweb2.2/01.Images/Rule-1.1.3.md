# AccessiWeb 2.2 - Rule 1.1.3

## Summary

This test consists in checking whether each form button is defined with
an `alt` attribute

## Business description

Criterion : 1.1

Test : [1.1.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-1-3)

Test description :

Does each form button (input tag with the type="image" attribute) have
an alt attribute?

Level : Bronze

## Technical description

Scope : page

Decision level :
decidable

## Algorithm

### Selection

Set1 : All the `input` tags with a "type" attribute equals to "image"

### Process

Test1 : Check whether each element of Set1 contains an `alt` attribute.

-   Message :

1.  Type : SourceCodeRemark
2.  Code : AltAttributeMissing
3.  Issue : Failed
4.  Target : `form`
5.  Value : x
6.  Case : For each element returning false in Test1

-   used nomenclature : none

-   reference : none

### Analysis

#### Not Applicable

The page has no `input` tag with a "type" attribute equals to "image"
(Set1 is empty)

#### Failed

At least one form button has no `alt` attribute (Test1 returns false for
at least one element)

#### Passed

All the `input` tags with a "type" attribute equals to "image" have an
`alt` attribut

## Notes

No notes yet for that rule
