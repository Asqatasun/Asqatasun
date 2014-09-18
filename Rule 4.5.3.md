### Summary

This test consists in checking whether each synchronised or audio-only
time-based media defined with an <object\> or an <embed\> tag has a
well-defined text transcript when it is required.

### Business description

Criterion : 4.5

Test : [4.5.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-4-5-3)

Test description :

Does each synchronised or audio-only [time-based
media](index.php/glossary-76.html#mMediaTemp) that is embedded via
<object\> or <embed\> and that requires a [text
transcript](index.php/glossary-76.html#mTranscriptTextuel) pass one of
the conditions below ([except in special
cases](index.php/glossary-76.html#cpCrit4- "Special cases for criterion 4.5"))?

-   A reference to the [text
    transcript](index.php/glossary-76.html#mTranscriptTextuel) via a
    link or an anchor is available between <object\> and </object\>
-   A reference to the [text
    transcript](index.php/glossary-76.html#mTranscriptTextuel) via a
    link or an anchor is available between <noembed\> and </noembed\>

Level : [Silver](/en/category/rules-design/accessiweb-11/level/argent)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Selection1 : All the <embed\> and <object\> tags (embed, object)

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

Selection is empty (The page has neither <object\> nor <embed\> tag)

##### NMI

The selection is not empty

### Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(embed, object) has been found on 6772 pages, i.e on 8.7% of the pages
