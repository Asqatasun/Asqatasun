### Summary

This test consists in checking that each html element that can grab the
focus does not override the outline css properties to make the browser
visual indication invisible

### Business description

Criterion : 10.7

Test : [10.7.1](www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-7-1)

Test description :

For each element that receives focus, the browser visual indication must
not be removed (CSS property outline, outline-color, outline-width,
outline-style). Does this rule have been followed?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

**Selection1:**

All the html elements that can grab the focus and not of type `input`,
`button`, `iframe`, `textarea` and `select`

**Selection2:**

All the html elements that can grab the focus and of type `input`,
`button`, `iframe`, `textarea` and `select`

#### Process

**Test1 :**

For each occurence of the Sélection1, we check whether the value of the
"outline-style" css property is different from "none" or "hidden".

For each element returning false in Test1, raise a MessageA

**Test2 :**

For each occurence of the Sélection1, we check whether the value of the
"outline-color" css property is different from the background-color css
property.

For each element returning false in Test2, raise a MessageA

**Test3 :**

For each occurence of the Sélection1, we check whether the value of the
"outline-width" css property is different from 0.

For each element returning false in Test3, raise a MessageA

**Test4 :**

If Selection2 is not empty, raise a MessageB

##### MessageA : Invisible Outline on Focus

-   code : InvisibleOutlineOnFocus
-   status: NMI
-   parameter : tag name, Snippet
-   present in source : yes

##### MessageB : Check Manually Outline For Form Element And Iframe

-   code : CheckManuallyOutlineForFormElementAndIframe
-   status: NMI
-   present in source : no

#### Analysis

**Not applicable :**

Selection1 AND Selection2 are empty (the page has no focusable
element)**\
**

**Passed :**

Test1 AND Test2 AND Test3 return true for all elements AND Selection2 is
empty (all the elements have a visible focus visual indication and no
element that needs a human check is present on the page)

**NMI :**

In all other cases

### Notes

The value of the css properties outline, outline-color, outline-width,
outline-style are determined while fetching the page, through a
javacript script. Each html element is analyzed to extract these info,
as well as whether it is focusable and it is displayed ("display" css
property equals to "none" or "visibility" css property equals to
"hidden").

Some elements are de facto excluded from the selection:

-   input
-   button
-   textarea
-   iframe
-   select

Their look&feel is defined by the browser.


