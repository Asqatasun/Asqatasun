# RGAA 4.0 — Rule 7.4.1

## Summary

This test consists in detecting patterns that are known to change context automatically : 

- A `<select>` element with an `"onchange"` attribute
- A `<form>` element without submit button

## Business description

### Criterion

[7.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-7-4)

### Test

[7.4.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-4-1)

### Description

> Chaque [script](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#script) qui initie un [changement de contexte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#changement-de-contexte) vérifie-t-il une de ces conditions ?
> 
> * L’utilisateur est averti par un texte de l’action du script et du type de changement avant son déclenchement.
> * Le changement de contexte est initié par un bouton (input de type `submit`, `button` ou `image` ou balise `<button>`) explicite.
> * Le changement de contexte est initié par un lien explicite.

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



## Files

- [TestCases files for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule070401/)
- [Unit test file for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070401Test.java)
- [Class file for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070401.java)


