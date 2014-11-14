### Summary

This test consists in checking the relevancy of the alternative of each `img` that handles any information.

### Business description

Criterion : 1.3

Test : [1.3.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-3-1)

Test description :

For each [image that conveys information](http://www.accessiweb.org/index.php/glossary-76.html#mImgInfo) (img tag) with an alt attribute, is the content of this attribute relevant (except in special cases)?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

**Set1** (`img` tags identified as a ninformative image from html markers)

All the `img` tags not within an `a` tag (in this case, the image would be considered as a link) with an `alt` attribute and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.
The elements of Set2 with a `longdesc` attribute are added to that collection.

**Set2** (`img` tags not identified as an informative image from html markers)

All the `img` tags not within an `a` tag with an `alt` attribute, without a `longdesc` attribute that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `img` tags not within an `a` tag with an `alt` attribute of the page when these parameters are empty.

#### Process

**Test1**

For each element of Set1 (informative `img` identified by a html marker), check the relevancy of the content of the `alt` attribute (not empty, different from the value of the `src` attribute and without image extension such as jpg, jpeg, bmp, png, tiff).
For each element returning false in Test1, raise a MessageA
For each element returning true in Test1, raise a MessageB

**Test2**

For each element of Set2 (`img` tags not identified as informative image), check the relevancy of the content of the `alt` attribute (not empty, different from the value of the `src` attribute and without image extension such as jpg, jpeg, bmp, png, tiff).
For each element returning false in Test3, raise a MessageC
For each element returning true in Test4, raise a MessageD

##### MessageA : Informative image with not pertinent alternative

-   code : NotPertinentAlt
-   status: Failed
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

##### MessageB : Check the pertinence of the informative image
 
-   code : CheckPertinenceOfAltAttributeOfInformativeImage
-   status: Pre-qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

##### MessageC : Check the nature of the image with a not pertinent alternative

-   code : CheckNatureOfImageWithNotPertinentAlt
-   status: Pre-qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

##### MessageC : Check the nature of the image and the pertinence of its alternative

-   code : CheckNatureOfImageAndAltPertinence
-   status: Pre-qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

#### Analysis

##### Not Applicable

Set1 and Set2 are empty (The page has no `img` tag)

##### Failed

Test1 returns false for at least one element (one `img` identified as informative has a not pertinent alternative)

##### Pre-Qualified

In all other cases




