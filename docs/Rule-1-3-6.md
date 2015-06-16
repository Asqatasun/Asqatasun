# Rule 1.3.6

## Summary

This test consists in checking for each informative vector image (`<svg>` tag) are implemented correctly and checking each informative vector image have a `"desc"` tags or a `aria-label` attribute with relevante alternative.

## Business description

### Criterion

[1.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-3)

###Test

[1.3.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-3-6)

### Description

Chaque image vectorielle porteuse d'information (balise `svg`) et poss&eacute;dant une alternative v&eacute;rifie-t-elle une de ces conditions (hors <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit1-3" title="Cas particuliers pour le crit&egrave;re 1.3">cas particuliers</a>) ? 
 
 * La balise `svg` poss&egrave;de un `role="img"` 
 * La balise `svg` poss&egrave;de une propri&eacute;t&eacute; `aria-label` dont le contenu est pertinent et identique &agrave; l'attribut `title` s'il est pr&eacute;sent 
 * La balise `svg` poss&egrave;de une balise `desc` dont le contenu est pertinent et identique &agrave; l'attribut `title` de la balise `svg` s'il est pr&eacute;sent 
 * Un lien adjacent permet d'acc&eacute;der &agrave; une alternative dont le contenu est pertinent et identique &agrave; l'attribut `title` de la balise `svg` s'il est pr&eacute;sent 

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semidecidable**

## Algorithm

### Selection

#### Set1

All the `<svg>` tags of the page (css selector : `svg`) and with an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"INFORMATIVE_SVG_MARKER"` parameter.
That means select all the `<svg>` tags not within an `<a>` tag when these parameters are empty.

#### Set2

All the `<svg>` tags of the page (css selector : `svg`) that don't have an `id` attribute or a `class` attribute that matches one the values set by the use through the `"INFORMATIVE_SVG_MARKER"` parameter or the `"DECORATIVE_SVG_MARKER"` parameter. 
That means select all the `<svg>` tags not within an `<a>` tag when these parameters are empty.

#### Set3

All the elements of set1 with a `"role"` attribute with value `"img"` 

#### Set4

All the elements of set2 with a `"role"` attribute with value `"img"` 

#### Set5

All the `<desc>` tags who are the children of the Set3 elements 

#### Set6

All the `<desc>` tags who are the children of the Set4 elements 

### Process

#### Tests

##### Test1

For each element of Set1, check the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test1, raise a MessageA

##### Test2

For each element of Set2, check the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test2, raise a MessageB

##### Test3 

For each element of Set3, check the presence of the `aria-label` attribute and that its content is not null

##### Test4 

For each element of Set4, check the presence of the `aria-label` attribute and that its content is not null

##### Test5 

For each element of Set3, check the presence of a `desc` tag children of the `svg` tag and that its content is same as the content of the `aria-label` attribute

##### Test6 

For each element of Set4, check the presence of a `desc` tag children of the `svg` tag and that its content is same as the content of the `aria-label` attribute

##### Test7 

For each occurrence of Set3 that return true-result of Test3 or Test5, check the presence of a `title` attribute and that its content is same as the content of the `aria-label` attribute or the `desc` tag

##### Test8 

For each occurrence of Set4 that return true-result of Test4 or Test6, check the presence of a `title` attribute and that its content is same as the content of the `aria-label` attribute or the `desc` tag

#### Messages

For each occurrence of Set3 that return false-result of Test3, Test5 and Test7, raise a MessageC

For each occurrence of Set4 that return false-result of Test4, Test6 and Test8, raise a MessageC

##### MessageA : Informative `svg` without role `img` attribute 

-    code : InformativeSvgWithoutRoleImgAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Suspected informative `svg` without role `img` attribute 

-    code : SuspectedInformativeSvgWithoutRoleImgAttribute
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageC : Suspected informative `svg` without alternative 

-    code : SuspectedInformativeSvgWithoutAlternative
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Failed

For each occurrence of false-result of Test1

#### Not Applicable

The page has no `<svg>` tag (Set1 and Set2 are empty)

#### Pre-qualified

In all other cases

For each occurrence of Set3 that return a true-result of Test3, Test5 or Test7, raise a MessageD

For each occurrence of Set4 that return a true-result of Test4, Test6 or Test8, raise a MessageE

##### MessageD : Checked alternative of informative `svg` 

-    code : CheckedAlternativeOfInformativeSvg
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageE : Checked alternative of suspected informative `svg` 

-    code : CheckedAlternativeOfSuspectedInformativeSvg
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes
