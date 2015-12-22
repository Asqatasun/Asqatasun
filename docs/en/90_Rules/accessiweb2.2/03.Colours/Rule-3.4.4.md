# AccessiWeb 2.2 - Rule 3.4.4

## Summary

This test consists in checking whether the contrast ratio between text
and its background is at least 4.5:1 for the bold textual elements with
a font size superior to 14px.

## Business description

Criterion : 3.4

Test : [3.4.4](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-3-4-4)

Test description :

On each Web page, **from 120%** of the [default font
size](http://accessiweb.org/index.php/glossary-76.html#mTaillePolice)
(or 1.2em), do bold text and image of text pass one of the conditions
below ([except in special
cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit3- "Special cases for criterion 3.4"))?

-   the contrast ratio between text and its background is at least 4,5:1
-   a mechanism allows the user to display text with a contrast ratio of
    at least 4,5:1

Level : Gold

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

**Set1**

All the textual elements from the DOM with a font-size superior to 14px
and bold

**Set2**

All the hidden textual elements from the DOM with a font-size superior
to 14px and bold

**Set3**

All the `img` tags

### Process

**Test1**

For each element of Set1, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 4.5.

For each element returning false in Test1, if the
"ALTERNATIVE\_CONTRAST\_MECHANISM" is set to true by the user, raise a
Message1, raise a Message2 instead.

If an element has a contrast ratio that cannot be determined (background
defined with an image or a gradient), raise a Message3

**Test2**

For each element of Set2, the contrast ratio is computed (regarding the
[contrast ratio
definition](http://www.w3.org/TR/WCAG20/#contrast-ratiodef)) to check
whether it is superior to 4.5.

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

