# RGAA 4.0 — Rule 3.2.2

## Summary

This test consists in checking whether the contrast ratio between text
and its background is at least 4.5:1 for the bold, under 14px sized,
textual elements

## Business description

### Criterion

[3.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-3-2)

### Test

[3.2.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-3-2-2)

### Description

> Dans chaque page web, le texte et le texte en image en gras d’une taille restituée inférieure à 18,5px vérifient-ils une de ces conditions (hors cas particuliers) ?
> 
> * Le rapport de [contraste](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contraste) entre le texte et son arrière-plan est de 4.5:1, au moins.
> * Un mécanisme permet à l’utilisateur d’afficher le texte avec un rapport de [contraste](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contraste) de 4.5:1, au moins.

#### Particular cases (criterion 3.2)

> Dans ces situations, les critères sont non applicables pour ces éléments.
> 
> * Le texte fait partie d’un logo ou d’un nom de marque d’un organisme ou d’une société ;
> * Le texte ou l’image de texte est purement décoratif ;
> * Le texte fait partie d’une image véhiculant une information mais le texte lui-même n’apporte aucune information essentielle ;
> * Le texte ou l’image de texte fait partie d’un élément d’interface sur lequel aucune action n’est possible (par exemple un bouton avec l’attribut `disabled`).

### Level

**AA**


## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

#### Set1

All the textual elements from the DOM with a font-size inferior or
equals to 14px and bold

#### Set2

All the hidden textual elements from the DOM with a font-size inferior
or equals to 14px and bold

#### Set3

All the `<img>` tags

### Process

#### Test1

For each element of **Set1**, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 4.5.

For each element returning false in **Test1**, if the
*ALTERNATIVE_CONTRAST_MECHANISM* is set to true by the user, raise a
MessageA, raise a MessageB instead.

If an element has a contrast ratio that cannot be determined (background
defined with an image or a gradient), raise a MessageC

#### Test2

For each element of **Set2**, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 4.5.

For each element returning false in **Test2**, raise a MessageD

##### MessageA : Bad Contrast

-   code : BadContrast
-   status: Failed
-   parameter : foreground color, background color, contrast ratio, Snippet
-   present in source : yes

##### MessageB : Bad Contrast But Alternative Contrast Mechanism Present On Page

-   code : BadContrastButAlternativeContrastMechanismOnPage
-   status: Pre-Qualified
-   parameter : foreground color, background color, contrast ratio, Snippet
-   present in source : yes

##### MessageC : Not Treated Background Color

-   code : NotTreatedBackgroundColor
-   status: Pre-Qualified
-   parameter : none
-   present in source : no

##### MessageD : Bad Contrast on Hidden Element

-   code : BadContrastHiddenElement
-   status: Pre-Qualified
-   parameter : foreground color, background color, contrast ratio, Snippet
-   present in source : yes

### Analysis

#### Not Applicable

No element with a font-size inferior or equals to 14px and bold have been found (**Set1** AND **Set2** are empty)

#### Passed

All the elements with a font-size inferior or equals to 14px and bold have a correct ratio and the page contains no images (**Test1** returns true for all elements AND **Set2** AND **Set3** are empty)

#### Failed

At least one element with a font-size inferior or equals to 14px and bold have an incorrect ratio (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

1.  By approximation, we consider that 1.2em of the default font size is
    equivalent to 14 px.
2.  The background color (`"background"` css property), the font color
    (`"color"` css property), the font size (`"font-size"` css property) and
    the font weight (`"font-weight"` css property) are retrieved while
    fetching the page, through a javacript script. Each html element
    is parsed to extract these info, as well as its
    visibility (`"display"` css property equals to *none* or `"visibility"`
    css property equals to *hidden*) and whether it is a textual node.




## Files

- [TestCases files for rule 3.2.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule030202/)
- [Unit test file for rule 3.2.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030202Test.java)
- [Class file for rule 3.2.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030202.java)


