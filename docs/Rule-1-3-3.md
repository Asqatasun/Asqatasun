# Rule 1.3.3

## Summary

This test consists in checking whether each button associated with an image that handles information has a relevant alternative.

## Business description

### Criterion

[1.3](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-3)

### Test

[1.3.3](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-3-3)

### Description

Pour chaque bouton associ&eacute; &agrave; une image (balise `input` avec l'attribut `type="image"`) ayant un attribut `alt`, le contenu de cet attribut est-il pertinent (hors <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit1-3" title="Cas particuliers pour le crit&egrave;re 1.3">cas particuliers</a>) ?

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
- it has an extension of image type (loaded by the nomenclature named *ImageFileExtensions* composed of : jpg, gif, jpeg, png, bmp)
