# AccessiWeb 2.2 - Rule 1.3.4

## Summary

This test consists in checking the relevancy of the alternative of each `applet` image that handles any information.

## Business description

Criterion : 1.3

Test : [1.3.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-3-4)

Test description :

For each [applet image](http://www.accessiweb.org/index.php/glossary-76.html#mImgApplet)  (applet tag) that conveys information and that has an alt attribute, is the content of this attribute relevant (except in special cases)?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

## Algorithm

### Selection

**Set1** (`applet` tags identified as an informative applet from html markers)

All the `applet` tags not within an `a` tag (in this case, the applet would be considered as a link) with an `alt` attribute and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.

**Set2** (`applet` tags not identified as an informative applet from html markers)

All the `applet` tags not within an `a` tag with an `alt` attribute that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `applet` tags not within an `a` tag with an `alt` attribute of the page when these parameters are empty.

### Process

**Test1**

For each element of Set1 (informative `applet` identified by a html marker), check the relevancy of the content of the `alt` attribute (not empty, different from the value of the `code` attribute and without image extension such as jpg, jpeg, bmp, png, tiff).
For each element returning false in Test1, raise a MessageA
For each element returning true in Test1, raise a MessageB

**Test2**

For each element of Set2 (`applet` tags not identified as informative applet), check the relevancy of the content of the `alt` attribute (not empty, different from the value of the `code` attribute and without image extension such as jpg, jpeg, bmp, png, tiff).
For each element returning false in Test2, raise a MessageC
For each element returning true in Test2, raise a MessageD

##### MessageA : Informative applet with not pertinent alternative

-   code : NotPertinentAlt
-   status: Failed
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

##### MessageB : Check the pertinence of the informative applet

-   code : CheckPertinenceOfAltAttributeOfInformativeImage
-   status: Pre-qualified
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

##### MessageC : Check the nature of the applet with a not pertinent alternative

-   code : CheckNatureOfImageWithNotPertinentAlt
-   status: Pre-qualified
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

##### MessageC : Check the nature of the applet and the pertinence of its alternative

-   code : CheckNatureOfImageAndAltPertinence
-   status: Pre-qualified
-   parameter : `alt` attribute, `code` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (The page has no `applet` tag)

#### Failed

Test1 returns false for at least one element (one `applet` identified as informative has a not pertinent alternative)

##### Pre-Qualified

In all other cases




