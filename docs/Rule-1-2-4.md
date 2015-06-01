# Rule 1.2.4

## Summary

This test consists in checking whether the ARIA attribute of each decorative vector image (`<svg>` tag) are implemented correctly and checking each decorative vector image or children have no `"title"` or `"desc"` attribute unless they are empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-2)

###Test

[1.2.4](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-2-4)

### Description

Chaque image vectorielle de d&eacute;coration (balise `<svg>`) non porteuse d'information et poss&eacute;dant une alternative v&eacute;rifie-t-elle ces conditions ? 
 
 * La balise `<svg>` poss&egrave;de un `role="img"` 
 * La balise `<svg>` ou l'un de ses enfants est d&eacute;pourvue de role, propri&eacute;t&eacute; ou &eacute;tat ARIA visant &agrave; labelliser l'image vectorielle (`aria-label`, `aria-describedby`, `aria-labelledby` par exemple). 
 * Les balises `<title>` et `<desc>` sont absentes ou vides 
 * La balise `<svg>` ou l'un de ses enfants est d&eacute;pourvue d'attribut `title` 

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

All the `<svg>` tags of the page 

#### Set2

All the `<svg>` tags of the page with a `"role"` attribute with value `"img"`

### Process

#### Test1

For each element of Set1, test the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test1, raise a MessageA

##### MessageA : Missing Alt attribute

code : RoleImgMissing
status: Failed
parameter : tag name
present in source : yes

### Analysis

#### Passed

#### Failed

#### Not Applicable

#### Pre-qualified

#### No Tested 






