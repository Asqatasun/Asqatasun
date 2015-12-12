## Summary

This test consists in checking whether each time-based media and each
non time-based media defined with an `object`, an `embed` or an
`applet` tag is supported by assistive technologies

## Business description

Criterion : 4.22

Test : [4.22.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-4-22-1)

Test description :

Does each [time-based media](index.php/glossary-76.html#mMediaTemp) and
each non time-based media that is inserted via a Object, Applet or Embed
tag pass one of the conditions below ([except in special
cases](index.php/glossary-76.html#cpCrit4-22 "Special cases for criterion 4.22"))?

-   The name, role, value, setting and status changes of the interface
    components are accessible to assistive technologies via an
    accessibility API
-   An alternative that is [supported by an accessibility
    API](index.php/glossary-76.html#mCompAccess) allows to access to the
    same functionalities

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

Selection1 : All the `embed`, `applet` and `object` tags (embed,
applet, object)

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

Selection is empty (The page has neither `object`, nor `applet` nor
`embed` tag)

#### Pre-qualified

The selection is not empty

## Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(embed, applet, object) has been found on 6795 pages, i.e on 8.7% of the
pages
