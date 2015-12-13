# AccessiWeb 2.2 - Rule 1.7.2

## Summary

This test consists in checking whether the detailed description of an informative object image is relevant.

## Business description

Criterion : 1.7

Test : [1.7.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-7-2)

Test description :

Does each [object image](http://www.accessiweb.org/index.php/glossary-76.html#mImgObj) (object tag with the attribute type=&quot;image/...&quot;) with a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) pass one of the conditions below? 
<ul> 
 <li> The [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) available on the page and indicated between &lt;object&gt; and &lt;/object&gt;, is relevant</li> 
 <li> The [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) via an [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) is relevant</li> 
</ul>

Level : Bronze

## Technical description

Scope : page

Decision level : semidecidable

## Algorithm

### Selection

**Set1** (`object` tags with a `type` attribute that starts with "image" identified as an informative embedded image from html markers)

All the `object` tags not within an `a` tag (in this case, the object image would be considered as a link) with an `type` attribute that starts with "image" and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.

**Set2** (`object` tags with a `type` attribute that starts with "image" not identified as an informative object image from html markers)

All the `object` tags not within an `a` tag and with a `type` attribute that starts with "image" that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `object` tags not within an `a` tag with an `type` attribute that starts with "image" of the page when these parameters are empty.

### Process

**Test1**

For each element of Set1, raise a MessageA.

**Test2**

For each element of Set2, raise a MessageB.

##### MessageA : Check detailed description pertinence of the informative object image

-   code : CheckDescriptionPertinenceOfInformativeImage
-   status: Pre-qualified
-   parameter : text, `data` attribute, Snippet
-   present in source : yes

##### MessageB : Check the nature of the object image and the pertinence of its detailed description

-   code : CheckNatureOfImageAndDescriptionPertinence
-   status: Pre-qualified
-   parameter : text, `data` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (the page has no `object` tag not within an `a` tag and with a `type` attribute that starts with "image")

##### Pre-Qualified

In all other cases
