## Summary

This test consists in checking whether each `optgroup` in a `select`
tag has a label attribute.

## Business description

Criterion : 11.8

Test : [11.8.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-8-2)

Test description :

In each [selection list](http://www.accessiweb.org/index.php/glossary-76.html#mListeChoix) (select tag), does each list item grouping (optgroup tag) have a label attribute?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

Set1 : All the `optgroup` tags within a `select` tag

### Process

##### Test1

For each element of Set1, test the presence of a label attribute

For each occurence of false-result of Test1, raise a MessageA

###### MessageA : Optgroup without label

-   code : OptgroupWithoutLabel
-   status: Failed
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has no optgroup in a select tag)

#### Failed

Test1 returns false for at least one element.

#### Passed

Test1 returns true for all the elements.

## Notes