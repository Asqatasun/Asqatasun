### Summary

This test consists in checking whether pre-recorded audio-only
time-based media is enough audible.

### Business description

Criterion : 4.19

Test : [4.19.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-4-19-1)

Test description :

Does each audio [time-based
media](index.php/glossary-76.html#mMediaTemp) that is prerecorded and
played via an Object, Applet, Embed tag or that is provided for download
pass one of the conditions below ([except in special
cases](index.php/glossary-76.html#cpCrit4-19 "Special cases for criterion 4.19"))?

-   The background sounds can be turned off
-   The dialogue track(s) are 20 decibels higher than the background
    sounds.
-   An alternative version in which the backgroun sounds can be turned
    off is available
-   An alternative version in which the dialogue track(s) are 20
    decibels higher than the background sounds is available.

Level : [Or](/en/category/rules-design/accessiweb-11/level/or)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Selection1 : All the `embed`, `applet` and `object` tags (embed,
applet, object)

#### Process

The selection handles the process.

For each occurence of the Selection1 raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: NMI
-   parameter : snippet
-   present in source : yes

#### Analysis

##### NA

Selection is empty (The page has neither `object`, nor `applet` nor
`embed` tag)

##### NMI

The selection is not empty

### Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(embed, applet, object) has been found on 6795 pages, i.e on 8.7% of the
pages
