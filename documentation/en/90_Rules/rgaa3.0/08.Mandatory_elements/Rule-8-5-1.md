# RGAA 3.0 -  Rule 8.5.1

## Summary

This test consists in detecting the presence of the `<title>` tag

## Business description

### Criterion

[8.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-5)

###Test

[8.5.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-5-1)

### Description
Does each Web page have a
    <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTitrePage">page
  title</a> (<code>title</code> tag)? 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

#### Set1

The `<title>` tag of the page within the `<head>` tag (head title)

### Process

#### Test1

Test whether **Set1** is not empty. If false, raise a MessageA.

###### MessageA : Title tag missing

-   code : TitleTagMissing
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Passed

The `<title>` tag is present on the page (**Test1** returns true)

#### Failed

The `<title>` tag is not present on the page (**Test1** returns false)



##  TestCases 

[TestCases files for rule 8.5.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080501/) 


