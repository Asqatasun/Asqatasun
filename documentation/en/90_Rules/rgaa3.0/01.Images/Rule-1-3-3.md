# RGAA 3.0 -  Rule 1.3.3

## Summary

This test consists in checking whether each button associated with an image that handles information has a relevant alternative.

## Business description

### Criterion

[1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-3)

### Test

[1.3.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-3-3)

### Description
For each button associated
    with an image (<code>input</code> tag with the attribute
    <code>type="image"</code>), and with an <code>alt</code> attribute, is the content
    of this attribute relevant (except in <a title="Particular cases for criterion 1.3" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit1-3">particular cases</a>)? 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<input>` tags with a `"type"` attribute equals to "image" and an `"alt"` attribute (css selector : `input[type=image][alt]`)

### Process

#### Test1

For all elements of **Set1**, check whether the content of the `"alt"` attribute is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test1**, raise a MessageA.

For each occurrence of false-result of **Test1**, raise a MessageB

##### MessageA 

-    code : **NotPertinentAlt** 
-    status: Failed
-    parameter : `"alt"` attribute, `"src"` attribute, tag name
-    present in source : yes

##### MessageB 

-    code : **CheckPertinenceOfAltAttributeOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"src"` attribute, tag name
-    present in source : yes

### Analysis

#### Failed

At least one `<input>` tag with a `"type"` attribute equals to "image" has an irrelevant `"alt"` attribute (**Test1** returns true for at least one element)

#### Pre-qualified

The alternatives of all the `<input>` tags with a `"type"` attribute equals to "image" need to be manually checked (**Test1** returns false for all the elements of **Set1**) 

#### Not Applicable

The page has no `<input>` tag with a `"type"` attribute equals to "image" tag and an `"alt"` attribute (**Set1** is empty)

## Notes

The content of the `"alt"` attribute is seen as not relevant if :

- empty
- only composed of non-alphanumerical characters
- identical to the `"src"` attribute
- it has an extension of image type (loaded by the nomenclature named **ImageFileExtensions** composed of : jpg, gif, jpeg, png, bmp)



##  TestCases 

[TestCases files for rule 1.3.3](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010303/) 


