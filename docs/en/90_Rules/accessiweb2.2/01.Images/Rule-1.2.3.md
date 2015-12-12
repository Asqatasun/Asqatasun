# AccessiWeb 2.2 - Rule 1.2.3

## Summary

This test consists in checking whether each `applet` that doesn't handle any information is defined with an empty `alt` attribute.

## Business description

Criterion : 1.2

Test : [1.2.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-3)

Test description :

For each [applet image](http://www.accessiweb.org/index.php/glossary-76.html#mImgApplet) (applet tag) that does not convey information and that has an alt attribute, is the content of this attribute empty (alt=&quot;&quot;)?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

**Set1** (`applet` tags identified as a decorative applet image from html markers)

All the `applet` tags with an `alt` attribute and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "DECORATIVE_IMAGE_MARKER" parameter.

**Set2** (`applet` tags not identified as a decorative applet image from html markers)

All the `applet` tags with an `alt` attribute that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `applet` tags with an `alt` attribute of the page when these parameters are empty.

### Process

**Test1** (only applied when the "DECORATIVE_IMAGE_MARKER" parameter is not empty) :

For each element of Set1 (decorative applets identified by a html marker), check whether the content of the `alt` attribute is empty:
For each element returning false in Test1, raise a MessageA

**Test2**

For each element of Set2 (applets not identified as decorative applet), check whether the content of the `alt` attribute is empty:
For each element returning false in Test2, raise a MessageB
For each element returning true in Test2, raise a MessageC

##### MessageA : Decorative applet with not empty alternative

-   code : DecorativeElementWithNotEmptyAltAttribute
-   status: Failed
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

##### MessageB : Check nature of element with not empty alternative

-   code : CheckNatureOfElementWithNotEmptyAltAttribute
-   status: Pre-Qualified
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

##### MessageC : Check nature of element with empty alternative

-   code : CheckNatureOfElementWithEmptyAltAttribute
-   status: Pre-Qualified
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (The page has no `applet` tag)

#### Failed

Test1 returns false (one `applet` identified as decorative has a not empty alternative)

#### Passed

Test1 returns true for all the elements of Set 1 and Set2 is empty (all the `applet` of the page are identified as decorative and they all have an empty alternative)

##### Pre-Qualified

In all other cases

