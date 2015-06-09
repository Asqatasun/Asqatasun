# Rule 7.4.1
## Summary

This test consists in extracting scripts that potentially change the
context and let the user check manually whether the [change of
context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte)
is initiated by an explicit button, whether the [change of
context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte)
is initiated by an explicit link or whether the user is warned by a text
about the
[script](http://accessiweb.org/index.php/glossary-76.html#mScript)
action and the kind of change before it is activated.

## Business description

### Criterion

[7.4](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-7-4)

### Test

[7.4.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-7-4-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mScript">script</a> qui initie un <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mChangContexte">changement de contexte</a> v&eacute;rifie-t-il une de ces conditions ? 
 
 *  L'utilisateur est averti par un texte de l'action du <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mScript">script</a> et du type de changement avant son d&eacute;clenchement 
 *  Le <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mChangContexte">changement de contexte</a> est initi&eacute; par un bouton (`input` de type `submit`, `button` ou `image` ou balise `button`) explicite 
 *  Le <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mChangContexte">changement de contexte</a> est initi&eacute; par un lien explicite 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

##### Set1

All the `<select>` tags with an "onchange" attribute (select[onchange])

##### Set2

All the `<form>` tags with a `<select>` child but without child of type
`<button>`, `<input type='submit'>`, `<input type='button'>` or `<input
type='reset'>
(form:has(select):not(:has(button)):not(:has(input[type=submit])):not(:has(input[type=button])):not(:has(input[type=reset])))

### Process

##### Test1

For each occurence of **Set1** and **Set2**, raise a MessageA

Test2

If **Set1** AND **Set2** are empty, raise a MessageB

##### MessageA : Context changed by script detected

-   code :ContextChangedScriptDetected
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

##### MessageB : No Pattern detected

-   code : NoPatternDetected_AW22-07051
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Pre-qualified

In all cases

## Notes

No notes yet for that rule
