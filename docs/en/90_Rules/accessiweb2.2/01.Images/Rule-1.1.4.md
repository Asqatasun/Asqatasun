# AccessiWeb 2.2 - Rule 1.1.4

## Summary

This test consists in checking whether each `applet` tag is defined with an `alt` attribute

## Business description

Criterion : 1.1

Test : [1.1.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-1-4)

Test description :

Does each [applet image](http://www.accessiweb.org/index.php/glossary-76.html#mImgApplet) (applet tag) have an alt attribute?

Level : Bronze

## Technical description

Scope : page

Decision level : decidable

## Algorithm

### Selection

**Set1**

All the `applet` tags of the page

### Process

**Test1**

Test the presence of the `alt` attribute for each element of the Set1.
For each element returning false in Test1, raise a Message1

##### Message1 : Alt missing on applet

-   code : AltMissing
-   status: Failed
-   parameter : `code` attribute, Snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `applet` tag (Set1 is empty)

#### Failed

At least one `applet` tag has no `alt` attribute (Test1 returns failed for at least one element)

#### Passed

All the `applet` tags have an `alt` attribute
