# AccessiWeb 2.2 - Rule 9.1.2

## Summary

This test consists in checking the relevancy of the headings hierarchy.

## Business description

Criterion : 9.1

Test : [9.1.2](www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-9-1-2)

Test description :

On each Web page, is the hierarchy between the [headings](http://www.accessiweb.org/index.php/glossary-76.html#mTitre) (h tags) relevant?

Level : Bronze

## Technical description

Scope : page

Decision level :
decidable

## Algorithm

### Selection

Set1 : All the `<Hx>` tags where x is comprise between 1 and 6

-   used nomenclature : none

-   reference : none

### Process
 
We assume that the index of the first encountered `<Hx>` tag represents the index of reference for the document.

Test1 : We check that the difference between the index of two following elements of Set1 is not superior or equal to 2.

Test2 : We check that the index of each element of Set1 is not inferior to the index of reference.

-   used nomenclature : none

-   reference : none

### Analysis

-   NA : The page has no `<H>` tag
-   Failed : Test1 or Test2 return false for at least one element of Set1
-   Passed : Test1 or Test2 return true for all the elements of Set1

-   Message :

1.  code : HeaderTagNotHierarchicallyWelldefined
2.  status: Failed
3.  case : For each element returning false in Test1 or Test2
4.  parameter : tag name
5.  present in source : yes

## Notes

No notes yet for that rule
