# Rule 3.3.4
## Summary

This test consists in checking whether the contrast ratio between text
and its background is at least 3:1 for the bold textual elements with a
font size superior to 14px.

## Business description

### Criterion

[3.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-3-3)

### Test

[3.3.4](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-3-3-4)

### Description

Dans chaque page Web, <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTailleCaract%C3%A8re"><strong>&agrave; partir de 120%</strong> de la taille de police par d&eacute;faut</a> (ou 1.2em), le texte et le texte en image en gras v&eacute;rifient-ils une de ces conditions (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit3-" title="Cas particuliers pour le crit&egrave;re 3.3">hors cas particuliers</a>) ? 
 
 *  le rapport de contraste entre le texte et son arri&egrave;re-plan est de 3:1, au moins 
 *  un m&eacute;canisme permet &agrave; l'utilisateur d'afficher le texte avec un rapport de contraste de 3:1, au moins 


### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

**Set1**

All the textual elements from the DOM with a font-size superior to 14px
and bold

**Set2**

All the hidden textual elements from the DOM with a font-size superior
to 14px and bold

**Set3**

All the <img\> tags

### Process

**Test1**

For each element of Set1, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 3.

For each element returning false in Test1, if the
"ALTERNATIVE\_CONTRAST\_MECHANISM" is set to true by the user, raise a
Message1, raise a Message2 instead.

If an element has a contrast ratio that cannot be determined (background
defined with an image or a gradient), raise a Message3

**Test2**

For each element of Set2, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 3.

For each element returning false in Test2, raise a Message4

##### Message1 : Bad Contrast

-   code : BadContrast
-   status: Failed
-   parameter : foreground color, background color, contrast ratio,
    Snippet
-   present in source : yes

##### Message2 : Bad Contrast But Alternative Contrast Mechanism Present On Page

-   code : BadContrastButAlternativeContrastMechanismOnPage
-   status: NMI
-   parameter : foreground color, background color, contrast ratio,
    Snippet
-   present in source : yes

##### Message3 : Not Treated Background Color

-   code : NotTreatedBackgroundColor
-   status: NMI
-   parameter : none
-   present in source : no

##### Message4 : Bad Contrast on Hidden Element

-   code : BadContrastHiddenElement
-   status: NMI
-   parameter : foreground color, background color, contrast ratio,
    Snippet
-   present in source : yes

### Analysis

#### Not Applicable

-   Set1 AND Set2 are empty

#### Passed

-   Test1 returns true for all elements AND Set2 AND Set3 are empty

#### Failed

-   Test1 returns false for at least one element

#### Pre-qualified

-   In all other cases

## Notes

1.  By approximation, we consider that 1.2em of the default font size is
    equivalent to 14 px.
2.  The background color ("background" css property), the font color
    ("color" css property), the font size ("font-size" css property) and
    the font weight ("font-weight css property) are determined while
    fetching the page, through the javacript script. Each html element
    is analyzed to extract these info, as well as whether it is
    displayed ("display" css property equals to "none" or "visibility"
    css property equals to "hidden") and whether it is a textual node.

