### Summary

This test consists in checking the use of appropriate tags (`<ol>` and `<li>`) in case of information grouped in ordered list.

### Business description

Criterion : 9.2

Test : [9.2.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-9-2-2)

Test description :

On each Web page, does information grouped in ordered [lists](http://www.accessiweb.org/index.php/glossary-76.html#mListes) use the ol and li tags?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the set of tags respecting the following pattern :
`
    <p>

        1 (or A or I) text 

        <br/>

        2 (or B or II) text 

        <br/>

        ......

    </p>
`

where more than 2 `<br>` tags are encountered and where each text part does not exceed 80 characters.

-   used nomenclature : none
-   reference : none

#### Process

The selection handles the process

#### Analysis

-   NMI : In all cases

-   Message :

1.  code : SuspectedNotWellFormattedOrdererdList
2.  status: NMI
3.  case : For each pattern found
4.  parameter : tag name
5.  present in source : yes

### Notes

No notes yet for that rule
