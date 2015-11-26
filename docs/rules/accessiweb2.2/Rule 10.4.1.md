### Summary

Check wether a forbidden unit is present in all CSS media types screen,
tv, handheld and projection.

### Business description

Criterion : 10.4

Test : [10.4.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-4-1)

Test description :

In the [style
sheets](http://accessiweb.orgindex.php/glossary-76.html#mFeuilleStyle)
of the [Web
site](http://accessiweb.orgindex.php/glossary-76.html#mSiteWeb), non
relative units (pt, pc, mm, cm, in) must not be used for media types
screen, tv, handheld, projection. Does this rule have been followed?

Level : [Silver](/en/category/rules-design/accessiweb-11/level/argent)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

Set1 : All the css Rules whose media is "screen", "tv", "handheld" or
"projection".

##### Used nomenclature

-   MediaListNotAcceptingRelativeUnits

#### Process

**Test1**

Check whether the properties of the css rules of Set1 use a forbidden
unit (pt, pc, mm, cm, in).

For each occurence returning true, raise a MessageA

##### MessageA : Bad Unit type

-   code : BadUnitType
-   status: Failed
-   parameter : css-selector name
-   present in source : yes

##### Used nomenclature

-   RelativeCssUnits

#### Analysis

**Passed**

No property of css rules of Set1 uses a forbidden unit (Test1 returns
false for all the elements of Set1)

**Failed**

At least one css-rule of Set1 contains a property that uses a forbidden
unit (Test1 returns true for at least one ement of Set1)

**NMI**

If a css could not have parsed, and so not tested for any reason AND no
property of css rules of Set1 uses a forbidden unit (Test1 returns false
for all the elements of Set1)

A messageB is raised indicating that this css have to checked manually

##### MessageB : Untested resource

-   code : UnTestedResource
-   status: NMI
-   parameter : the resource name

### Notes

No notes yet for that rule
