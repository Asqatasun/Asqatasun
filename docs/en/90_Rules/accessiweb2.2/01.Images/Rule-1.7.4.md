# AccessiWeb 2.2 - Rule 1.7.4

## Summary

This test consists in checking whether the detailed description of an informative applet image is relevant.

## Business description

Criterion : 1.7

Test : [1.7.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-7-4)

Test description :

Does each [applet image](http://www.accessiweb.org/index.php/glossary-76.html#mImgApplet) (applet tag) with a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) pass one of the conditions below? 
<ul> 
 <li> The [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) that is available on the page and indicated between &lt;applet&gt; and &lt;/applet&gt; is relevant</li> 
 <li> The [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) that is available on the page and indicated in the alt attribute is relevant</li> 
 <li> The [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is accessible via an [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) and is relevant</li> 
</ul>

Level : Bronze

## Technical description

Scope : page

Decision level : semidecidable

## Algorithm

### Selection

**Set1** (`applet` tags identified as an informative applet image from html markers)

All the `applet` tags not within an `a` tag (in this case, the applet image would be considered as a link) and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.

**Set2** (`applet` tags not identified as an informative applet image from html markers)

All the `applet` tags not within an `a` tag that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `applet` tags not within an `a` tag of the page when these parameters are empty.

### Process

**Test1**

For each element of Set1, raise a MessageA.

**Test2**

For each element of Set2, raise a MessageB.

##### MessageA : Check detailed description pertinence of the informative applet image

-   code : CheckDescriptionPertinenceOfInformativeImage
-   status: Pre-qualified
-   parameter : `alt` attribute, text, `code` attribute, Snippet
-   present in source : yes

##### MessageB : Check the nature of the applet image and the pertinence of its detailed description

-   code : CheckNatureOfImageAndDescriptionPertinence
-   status: Pre-qualified
-   parameter : `alt` attribute, text, `code` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (the page has no `applet` tag not within an `a` tag)

##### Pre-Qualified

In all other cases
