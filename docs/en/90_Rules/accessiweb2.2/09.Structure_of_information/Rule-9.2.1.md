# AccessiWeb 2.2 - Rule 9.2.1

## Summary

This test consists in checking the use of appropriate tags (`<ul>` and `<li>`) in case of information grouped in unordered list.

## Business description

Criterion : 9.2

Test : [9.2.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-9-2-1)

Test description :

On each Web page, does information grouped in unordered [lists](http://www.accessiweb.org/index.php/glossary-76.html#mListes) use the ul and li tags?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the set of tags respecting the following pattern :

```html
    <p>
        text 
        <br/>
        text 
        <br/>
        ......
    </p>
``` 

where more than 2 `<br>` tags are encountered and where each text part does not exceed 80 characters.

-   used nomenclature : none
-   reference : none

### Process


### Analysis

-   NMI : In all cases

-   Message :

1.  code : SuspectedNotWellFormattedUnordererdList
2.  status: NMI
3.  case : For each pattern found
4.  parameter : tag name
5.  present in source : yes

## Notes

No notes yet for that rule
