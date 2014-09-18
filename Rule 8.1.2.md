### Summary

We check the doctype validity regarding the W3C recommandations ([W3C
Recommended list of Doctype
declarations](http://www.w3.org/QA/2002/04/valid-dtd-list.html))

### Business description

Criterion : 8.1

Test : [8.1.2](http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-8-1-2)

Test description :

For each Web page is the [document
type](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDTD)
(doctype tag) valid?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

The <!doctype\> tag of the page

#### Process

We check the validity of the doctype of the page regarding the
"RecommendedDoctypeDeclarations" and
"RecommendedCaseInsensitiveDoctypeDeclarations" whitelists

-   Used nomenclatures : "RecommendedDoctypeDeclarations",
    "RecommendedCaseInsensitiveDoctypeDeclarations"
-   Reference : All the doctypes declarations recommanded in
    [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

###### MessageA : Wrong Doctype Declaration

-   code : WrongDoctypeDeclaration
-   status: Failed
-   parameter : none
-   present in source : no

#### Analysis

##### NA

Selection is empty (The page has no doctype)

##### Failed

The doctype is not found among the doctype whitelist

##### Passed

The doctype is found among the doctype whitelist

### Notes

HTML5 doctype is NOT case-sensitive:
[http://www.w3.org/TR/html5/syntax.html\#the-doctype](http://www.w3.org/TR/html5/syntax.html#the-doctype "http://www.w3.org/TR/html5/syntax.html#the-doctype")

HTML4: could not find formal statement about case-sensitiveness of
doctype: http://www.w3.org/TR/html4/intro/sgmltut.html

XHTML1: same as HTML4 http://www.w3.org/TR/xhtml1/

From version 2.0, Tanaguru works on the generated html rendered by
Firefox. \
We've noticed that for HTML 4.01 doctypes that define the doctype with
the "HTML" key in Uppercase, the doctype is transformed and the "HTML"
key is changed to lowercase. \
This behaviour is the same with chromium and opera. \
Regarding this fact, we decided to consider all the doctypes as case
insensitive despite the W3c recommandations.
