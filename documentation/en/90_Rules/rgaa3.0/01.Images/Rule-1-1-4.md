# RGAA 3.0 -  Rule 1.1.4

## Summary

This test consists in checking whether each `<area>` of a server image map is doubled by a link in the page.

## Business description

### Criterion

[1.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-1)

###Test

[1.1.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-1-4)

### Description
Does each area (<code>area</code> tag) of a server-side image map have an equivalent link in the page? 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags with an `"ismap"` attribute and all the `<input>` tags with a `"type"` attribute equals to "image" and an `"ismap"` attribute (css selector : `img[ismap],input[type=image][ismap]`)

### Process

#### Tests

##### Test1

For each element of Set1, produce a MessageA

#### Messages

##### MessageA : Check a link is associated with the server-sided image map

-    code : CheckALinkIsAssociatedWithTheServerSidedImageMap
-    status: Pre-qualified (NMI or warning)
-    case : For each element of Set1
-    parameter : `"alt"` attribute, `"src"` attribute, tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with a `"ismap"` attribute (**Set1** is empty)

#### Pre-qualified

**Set1** is not empty

## Notes

We only detect the elements of the **Set1** to determine whether the test is applicable



##  TestCases 

[TestCases files for rule 1.1.4](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010104/) 


