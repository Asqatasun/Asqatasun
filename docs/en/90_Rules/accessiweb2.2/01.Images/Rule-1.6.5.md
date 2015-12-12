# AccessiWeb 2.2 - Rule 1.6.5

## Summary

This test consist in checking whether an informative image form button has a detailed description if necessary

## Business description

Criterion : 1.6

Test : [1.6.5](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-5)

Test description :

Does each image form button (input tag with the attribute type=&quot;image&quot;, requiring a detailed description, pass one of the conditions below? 
<ul> 
 <li> An alt attribute containing the reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) just next to the image is available</li> 
 <li> An [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) (via a [url](http://www.accessiweb.org/index.php/glossary-76.html#mUrl) or an anchor) allowing to access to the content of the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is available</li> 
</ul>

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)


## Algorithm

### Selection

**Set1** (`input` tags with a `type` attribute that starts with "image" identified as an informative image form button from html markers)

All the `input` tags with a `type` attribute that starts with "image" and with an `id` attribute or a `class` attribute or a `role` that matches one of the values set by the user through the "INFORMATIVE_IMAGE_MARKER" parameter.

**Set2** (`input` tags with a `type` attribute that starts with "image" not identified as an informative image form button from html markers)

All the `input` tags with a `type` attribute that starts with "image" that don't have an `id` attribute or a `class` attribute or a `role` that matches one the values set by the use through the "DECORATIVE_IMAGE_MARKER" parameter or the "INFORMATIVE_IMAGE_MARKER" parameter. That means select all the `input` tags with a `type` attribute that starts with "image" of the page when these parameters are empty.

### Process

**Test1**

For each element of Set1, raise a MessageA

**Test2**

For each element of Set2, raise a MessageB

##### MessageA : 

-   code : CheckLongdescDefinitionOfInformativeImage
-   status: Pre-Qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

##### MessageB : 

-   code : CheckNatureOfImageAndLongdescDefinition
-   status: Pre-Qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

Set1 and Set2 are empty (the page has no `input` tag with a `type` attribute that starts with "image")

##### Pre-Qualified

In all other cases
