### Summary

This test consists in extracting scripts that potentially change the context and let the user check manually whether the [change of context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte) is initiated by an explicit button, whether the [change of context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte) is initiated by an explicit link or whether the user is warned by a text about the [script](http://accessiweb.org/index.php/glossary-76.html#mScript) action and the kind of change before it is activated.

### Business description

Criterion : 7.5

Test : [7.5.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-7-5-1)

Test description :

Does each
[script](http://accessiweb.org/index.php/glossary-76.html#mScript) initiating a [change of context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte) pass one of the conditions below?

-   The user is warned by a text about the [script](http://accessiweb.org/index.php/glossary-76.html#mScript) action and the kind of change before it is activated
-   The [change of context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte) is initiated by an explicit button (input of type submit or button)
-   The [change of context](http://accessiweb.org/index.php/glossary-76.html#mChangContexte) is initiated by an explicit link

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

##### Set1

All the `<select>` tags with an `onchange` attribute (`select[onchange]`)

##### Set2

All the `<form>` tags with a `<select>` child but without child of type `button`, `<input type='submit'>`, `<input type='button'>` or `<input type='reset'>` (`form:has(select):not(:has(button)):not(:has(input[type=submit])):not(:has(input[type=button])):not(:has(input[type=reset]))`)

#### Process

##### Test1

For each occurence of Set1 and Set2, raise a MessageA

Test2

If Set1 AND Set2 are empty, raise a MessageB

##### MessageA : Context changed by script detected

-   code :ContextChangedScriptDetected
-   status: NMI
-   parameter : snippet
-   present in source : yes

##### MessageB : No Pattern detected

-   code : NoPatternDetected\_AW22-07051
-   status: NMI
-   present in source : no

#### Analysis

##### NMI

In all cases

### Notes

No notes yet for that rule
