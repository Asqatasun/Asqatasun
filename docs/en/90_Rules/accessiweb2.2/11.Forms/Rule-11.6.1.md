# AccessiWeb 2.2 - Rule 11.6.1

## Summary

This test consists in checking whether each fieldset tag is followed by
a legend tag in the source code.

## Business description

Criterion : 11.6

Test : [11.6.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-6-1)

Test description :

Test11.6.1: Is each form field grouping (fieldset tag) followed by a
legend (legend tag) in the source code?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

Set1 : All the `fieldset` tags.

Set2 : All the `fieldset` tags with a `legend` child tag.

### Process

##### Test1

For each element of Set1, test if the node is present in Set2

For each occurence of false-result of Test1, raise a MessageA

###### MessageA : FieldSet without legend

-   code :FieldSetWithoutLegend
-   status: Failed
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has no fieldset tag)

#### Failed

Test1 returns false for at least one element.

#### Passed

Test1 returns true for all the elements.

## Notes

No notes yet for that rule
