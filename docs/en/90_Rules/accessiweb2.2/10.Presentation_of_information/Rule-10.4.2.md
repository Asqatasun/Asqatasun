# AccessiWeb 2.2 - Rule 10.4.2

## Summary

This test consists in checking wether the font-size property is always
defined with a relative unit.

## Business description

Criterion : 10.4

Test : [10.4.2](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-4-2)

Test description : In the [style
sheets](http://accessiweb.org/index.php/glossary-76.html#mFeuilleStyle)
of the [Web
site](http://accessiweb.org/index.php/glossary-76.html#mSiteWeb), do
font sizes only use relative units?

Level : Silver

## Technical description

Scope : page

Decision level :
decidable

## Algorithm

### Selection

Set1 : All the css rules with a "font-size" property.

### Process

**Test1**

Check whether the property "font-size" of the css rules of Set1 use a
forbidden unit (pt, pc, mm, cm, in).

For each occurence returning true, raise a MessageA

##### MessageA : Bad Unit type

-   code : BadUnitType
-   status: Failed
-   parameter : css-selector name
-   present in source : yes

##### Used nomenclature

-   RelativeCssUnits

### Analysis

**Passed**

All the "font-size" property of css rules of Set1 are defined with a
relative unit (Test1 returns false for all the elements of Set1)

**Failed**

At least one css-rule of Set1 contains a property "font-size" that uses
a non-relative unit (Test1 returns true for at least one ement of Set1)

**NMI**

If a css could not have parsed, and so not tested for any reason AND all
the "font-size" property of css rules of Set1 are defined with a
relative unit (Test1 returns false for all the elements of Set1)

A messageB is raised indicating that this css have to checked manually.

##### MessageB : Untested resource

-   code : UnTestedResource
-   status: NMI
-   parameter : the resource name

## Notes

No notes yet for that rule
