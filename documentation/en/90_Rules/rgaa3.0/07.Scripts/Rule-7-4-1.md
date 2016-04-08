# RGAA 3.0 -  Rule 7.4.1

## Summary

This test consists in detecting patterns that are known to change context automatically : 

- A `<select>` element with an `"onchange"` attribute
- A `<form>` element without submit button

## Business description

### Criterion

[7.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-7-4)

### Test

[7.4.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-7-4-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#script">script</a> qui initie un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#changement-de-contexte">changement de contexte</a> v&eacute;rifie-t-il une de ces conditions ? 
 
 *  L'utilisateur est averti par un texte de l'action du <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#script">script</a> et du type de changement avant son d&eacute;clenchement 
 *  Le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#changement-de-contexte">changement de contexte</a> est initi&eacute; par un bouton (`input` de type `submit`, `button` ou `image` ou balise `button`) explicite 
 *  Le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#changement-de-contexte">changement de contexte</a> est initi&eacute; par un lien explicite 

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1

All the `<select>` tags with an "onchange" attribute (select[onchange])

#### Set2

All the `<form>` tags with a `<select>` child but without child of type
`<button>`, `<input type='submit'>`, `<input type='button'>` or `<input
type='reset'> (form:has(select):not(:has(button)):not(:has(input[type=submit])):not(:has(input[type=button])):not(:has(input[type=reset])))

### Process

#### Test1

For each occurence of **Set1** and **Set2**, raise a MessageA

#### Test2

If **Set1** AND **Set2** are empty, raise a MessageB

##### MessageA : Context changed by script detected

-   code :ContextChangedScriptDetected
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

##### MessageB : No Pattern detected

-   code : NoPatternDetected_Rgaa30-07041
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Pre-qualified

In all cases
