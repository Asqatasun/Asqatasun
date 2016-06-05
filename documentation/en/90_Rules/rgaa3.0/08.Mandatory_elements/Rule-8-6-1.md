# RGAA 3.0 -  Rule 8.6.1

## Summary

This test consists in checking the relevancy of the title tag in the page.

## Business description

### Criterion

[8.6](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-8-6)

### Test

[8.6.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-8-6-1)

### Description
For each Web page with a
    <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTitrePage">page
  title</a> (<code>title</code> tag), is the content of this tag
    relevant? 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Set1

The content of the `<title>` tag within the `<head>` (head title)

### Process

#### Test1

We check whether the title is found amoung the blacklisted titles loaded by the nomenclature "UnexplicitPageTitle". 

For each element returning false in Test1, raise a MessageA. Raise a MessageB instead.

###### MessageA : Irrelevant title

-   code : NotPertinentTitle
-   status: Failed
-   parameter : the textual content, snippet
-   present in source : yes

###### MessageB : Check title pertinence

-   code : CheckTitlePertinence
-   status: Pre-Qualified
-   parameter : the textual content, snippet
-   present in source : yes

### Analysis

####  Not Applicable

The page has no `<title>` tag (**Set1** is empty)

#### Failed

The title of the page has been found in the blacklist (**Test1** returns true)

#### Pre-Qualified

The title of the page has not been found in the blacklist (**Test1** returns false)




##  TestCases 

[TestCases files for rule 8.6.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080601/) 


