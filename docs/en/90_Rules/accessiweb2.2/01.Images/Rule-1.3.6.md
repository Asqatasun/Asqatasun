# AccessiWeb 2.2 - Rule 1.3.6

## Summary

This test consists in checking the relevancy of the alternative of each embedded image that handles any information.

## Business description

Criterion : 1.3

Test : [1.3.6](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-3-6)

Test description :

For each embedded image (embed tag with the attribute type=&quot;image/...&quot;) that conveys information and with a text alternative, is the [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg) between &lt;noembed&gt; and &lt;/noembed&gt; relevant (except in special cases)?

Level : Bronze

## Technical description

Scope : page

Decision level : semidecidable

### Selection

**Set1** (`embed` tags with a `type` attribute that starts with "image" identified as an informative embedded image from html markers)

All the `embed` tags not within an `a` tag (in this case, the embedded image would be considered as a link) with an `type` attribute that starts with "image" and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.

**Set2** (`embed` tags with a `type` attribute that starts with "image" not identified as an informative embedded image from html markers)

All the `embed` tags not within an `a` tag with an with an `type` attribute that starts with "image" that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `embed` tags not within an `a` tag with an `type` attribute that starts with "image" of the page when these parameters are empty.

### Process

**Test1**

For each element of Set1, raise a MessageA.

**Test2**

For each element of Set2, raise a MessageB.

##### MessageA : Check the pertinence of the informative image

-   code : CheckPertinenceOfAltAttributeOfInformativeImage
-   status: Pre-qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

##### MessageB : Check the nature of the image and the pertinence of its alternative

-   code : CheckNatureOfImageAndAltPertinence
-   status: Pre-qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (The page has no `embed` tags with a `type` attribute that starts with "image")

##### Pre-Qualified

In all other cases
