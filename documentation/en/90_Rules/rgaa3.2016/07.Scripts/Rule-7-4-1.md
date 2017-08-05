# RGAA 3.2016 - Rule 7.4.1

## Summary
This test consists in detecting patterns that are known to change context automatically : 

- A `<select>` element with an `"onchange"` attribute
- A `<form>` element without submit button

## Business description

### Criterion
[7.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-7-4)

### Test
[7.4.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-7-4-1)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#script">script</a> qui initie un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#changement-de-contexte">changement de contexte</a> v&#xE9;rifie-t-il une de ces conditions&nbsp;? <ul><li>L&#x2019;utilisateur est averti par un texte de l&#x2019;action du script et du type de changement avant son d&#xE9;clenchement&nbsp;;</li> <li>Le changement de contexte est initi&#xE9; par un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#bouton-formulaire">bouton</a> (<code lang="en">input</code> de type <code lang="en">submit</code>, <code lang="en">button</code> ou <code lang="en">image</code> ou balise <code lang="en">button</code>) explicite&nbsp;;</li> <li>Le changement de contexte est initi&#xE9; par un lien explicite.</li> </ul></div>

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



##  TestCases

[TestCases files for rule 7.4.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule070401/)


