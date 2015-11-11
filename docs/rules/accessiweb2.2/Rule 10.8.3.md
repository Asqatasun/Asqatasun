### Summary

This test consists in checking whether the background color of each text
of an `object`, `applet` or `embed` tag can be controlled by the user

### Business description

Criterion : 10.8

Test : [10.8.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-8-3)

Test description :

For each block of text inside of an object, applet or embed element, can
background colour be controlled by the user?

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

The selection handles the process. For each occurence of the Selection1
raise a MessageA MessageA : Check Manually the elements code
:ManualCheckOnElements status: NMI parameter : snippet present in source
: yes

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
