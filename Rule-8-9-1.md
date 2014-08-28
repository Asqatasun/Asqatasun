### Summary

This test consists in searching patterns indicating that forbidden tags
(not div, span or table) are used for layout purpose.

### Business description

Criterion : 8.9

Test : [8.9.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-9-1)

Test description :

On each Web page tags must not be used (except div, span and table)
[only for layout](index.php/glossary-76.html#mUniquPres). Does this rule
have been followed?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Selection1 : all the <a\> tags without "href", "name" or "id" attribute
(a:not([href]):not([name]):not([id]))

Selection2 : all the fieldset not within a form (fieldset:not(form
fieldset):not(\*[role=search] fieldset):not(\*[role=form] fieldset))

#### Process

**Test1 :**

We check whether Selection1 AND Selection2 are empty. If true, raise a
MessageA

###### MessageA : No suspect pattern detected

-   code :NoPatternDetected
-   status: NMI
-   present in source : no

For each occurence of the Selection1 raise a MessageB

###### MessageB : Link without target

-   code :LinkWithoutTarget
-   status: Failed
-   parameter : snippet
-   present in source : yes

For each occurence of the Selection2 raise a MessageC

###### MessageC : Fieldset not within a form

-   code :FieldsetNotWithinForm
-   status: Failed
-   parameter : snippet
-   present in source : yes

Test1 :

#### Analysis

##### Failed :

Test1 returns false (The page contains a link without target or a
fieldset not within a form)

##### NMI :

Test1 returns true

### Notes

On latest webdev data set (2013-10-30, 78,000 pages), links without
target (a:not([href]):not([name]):not([id])) have been found on 18256
pages, i.e on 23% of the pages.

On latest webdev data set (2013-10-30, 78,000 pages), fieldsets not
within form (fieldset:not(form fieldset):not(\*[role=search]
fieldset):not(\*[role=form] fieldset)) have been found on 982 pages, i.e
on 1.25% of the pages.
