### Summary

This test consists in checking whether the alt attribute of each
decorative image is empty.

### Business description

Criterion : 1.2

Test : (http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#test-1-2-1)[1.2.1]

Test description :

For each [decorative
image](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mImgDeco)
(img tag) with an alt attribute, is the content of this attribute empty
(alt="")?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

For this rule, we assume that all <img\> tags with a "longdesc"
attribute are informative images and the <img\> tags without "src"
attribute are ignored (unusable tag).

Set1 : All the <img\> tags of the page with an "alt" attribute and a
"src" attribute but not within a <a\> tag (in this case, the image would
be considered as a link)

Set2 : All the <img\> tags of the page with an "alt" attribute, a "src"
attribute and a "longdesc" attribute but not within a <a\> tag (in this
case, the image would be considered as a link)

Set3 : All the <img\> tags of the page with an "alt" attribute and a
"src" attribute but without a "longdesc" attribute and not within a <a\>
tag (in this case, the image would be considered as a link)

-   used nomenclature : none
-   reference : none

#### Process

For this rule, we assume that all <img\> tags with a "longdesc"
attribute are informative images.

Test1 : Test if Set1 and Set2 have the same size OR set1 is empty

Test2 : Returns elements of Set3 that are potentially decorative. We
test if one dimension of each image is equal to 1 pixel or if it
displays only one color.

Test3 : Test if elements returned by Test2 have an empty "alt" attribute
and a not empty "src" attribute

-   used nomenclature : none
-   reference : none

#### Analysis

##### NA

-   The page has no <img\> tag with "alt" attribute (Set2 is empty and
    Test1 returns true)
-   All the images of the page are informative (Set1 and Set2 have the
    same size and Test1 returns true)

##### NMI

All other cases with message:

-   code : SuspectedDecorativeImageWithNotEmptyAltAttribute
-   status: NMI
-   case : For each element returning false in Test3
-   parameter : tag name
-   present in source : yes

### Notes

No notes yet for that rule
