### Summary

This test consists in checking the use of appropriate tags (`<ul>` and `<li>`) in case of information grouped in unordered list.

### Business description

Criterion : 9.2

Test : [9.2.1](http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-9-2-1)

Test description :

On each Web page, does information grouped in unordered [lists](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mListes) use the ul and li tags?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the set of tags respecting the following pattern :

`    <p>

        text 

        <br/>

        text 

        <br/>

        ......

    </p>
`
where more than 2 `<br>` tags are encountered and where each text part does not exceed 80 characters.

-   used nomenclature : none
-   reference : none

#### Process


#### Analysis

-   NMI : In all cases

-   Message :

1.  code : SuspectedNotWellFormattedUnordererdList
2.  status: NMI
3.  case : For each pattern found
4.  parameter : tag name
5.  present in source : yes

### Notes

No notes yet for that rule
