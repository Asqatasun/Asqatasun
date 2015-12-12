## Summary

This test consists in checking whether each audio sequence played
automatically and defined with an `object`, an `embed`, an `applet`
or a `bgdound` tag can be stopped by the user or the volume can be
shutdowned

## Business description

Criterion : 4.18

Test : [4.18.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-4-18-1)

Test description :

Does each audio sequence that is played automatically via an object,
applet, embed tag, a javascript code or bgsound property pass one of the
conditions below?

-   The audio sequence lasts less than or equal to 3 seconds
-   The audio sequence can be stopped by an action initiated by the user
-   The volume of the audio sequence can be controlled by the user
    independently from the system volume control.

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

Selection1 : All the `embed`, `object`, `applet` and `bgsound` tags
(embed, object, bgsound, applet)

### Process

The selection handles the process.

For each occurence of the Selection1 raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: NMI
-   parameter : snippet
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has neither `object`, nor `applet`, nor
`bgsound`, nor `embed` tag)

#### Pre-qualified

The selection is not empty

## Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(embed, applet, bgsound, object) has been found on 6804 pages, i.e on
8.7% of the pages
