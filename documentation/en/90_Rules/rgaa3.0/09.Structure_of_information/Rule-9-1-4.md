# RGAA 3.0 -  Rule 9.1.4

## Summary

This test consists in checking whether each heading of the page is relevant

## Business description

### Criterion

[9.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-9-1)

###Test

[9.1.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-9-1-4)

### Description
On each Web page, is each
    <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTitre">heading</a>
    (<code>h1</code> to <code>h6</code> tag or a tag with an ARIA
    <code>role="heading"</code> associated to an <code>aria-level</code> property)
    relevant? 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

#### Test1

For each element of **Set1**, we check whether the content of the tag is pertinent (see Notes about relevancy detection).

For each element returning false in **Test1**, raise a MessageA. Raise a MessageB instead.

###### MessageA : Not pertinent heading

-    code : NotPertinentHeading
-    status: Failed
-    parameter : tag text, tag name, snippet
-    present in source : yes

###### MessageB : Check heading pertinence

-    code : CheckHeadingPertinence
-    status: Pre-Qualified
-    parameter : tag text, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)

#### Failed

At least one element of **Set1** has an empty content or a content only composed of non alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-Qualified

In all other cases

## Notes

***Definition of not-pertinent legend :***

A heading is seen as not-pertinent in the following cases :

-   the content of the tag is empty
-   the content of the tag only contains non alphanumerics characters



##  TestCases 

[TestCases files for rule 9.1.4](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule090104/) 


