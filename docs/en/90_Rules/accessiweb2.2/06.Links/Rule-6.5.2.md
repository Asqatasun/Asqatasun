# AccessiWeb 2.2 - Rule 6.5.2

## Summary

This test consists in checking whether a server side image map is
well-defined

## Business description

Criterion : 6.5

Test : [6.5.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-5-2)

Test description : Does each server-side [image map](http://www.accessiweb.org/index.php/glossary-76.html#mImgReactive) (img tag or input tag as image with the ismap attribute) pass one of the conditions below?

-   The link that doubles the [clickable area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneCliquable) allows to access to the same function and the same target as the [clickable area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneCliquable)
-   the form submission allows to access the same purpose and the same target as the [clickable area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneCliquable).

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `img` tags and the `input` tags with the "type" attribute
equals to image, with the "ismap" attribute (img[ismap] ,
input[type=image][ismap])

### Process

The selection handles the process.

For each occurence of the selection raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: NMI
-   parameter : src attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
