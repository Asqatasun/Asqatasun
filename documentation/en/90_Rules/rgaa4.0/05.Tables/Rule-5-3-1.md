# RGAA 4.0 — Rule 5.3.1

## Summary

This test consists in detecting the presentation tables. 

Checking whether the linearised content is understandable and the table owns a role attribute with "presentation" value has to be done manually.

## Business description

### Criterion

[5.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-5-3)

### Test

[5.3.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-3-1)

### Description

> Chaque [tableau de mise en forme](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#tableau-de-mise-en-forme) vérifie-t-il ces conditions (hors cas particuliers) ?
> 
> * Le contenu linéarisé reste compréhensible.
> * La balise `<table>` possède un attribut `role="presentation"`.

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1 (table tags identified as complex table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "PRESENTATION_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as complex table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class" attribute or a "role" attribute that matches one the values set by the user through the "PRESENTATION_TABLE_MARKER" parameter or the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter. 
That means select all the `<table>` tags of the page when these parameters are empty.

### Process

#### Test1 

For each element of **Set1**, raise a MessageA.

#### Test2 

For each element of **Set1**, test whether the `"role"` attribute is present with a value equals to "presentation".

For each element returning true in **Test2**, raise a MessageB.

#### Test3

For each element of **Set2**, raise a MessageC.

#### Test4

For each element of **Set2**, test whether the `"role"` attribute is present with a value equals to "presentation".

For each element returning true in **Test4**, raise a MessageD, raise a MessageE instead.

###### MessageA : Check Linearised Content is understandable for presentation tables

-   code : CheckLinearisedContent
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageB : Role presentation missing on presentation tables

-   code : PresentationTableWithoutAriaMarkup
-   status: Failed
-   parameter : Snippet
-   present in source : yes

###### MessageC : Check nature of table and linearised content is understandable if presentation table

-   code : CheckNatureOfTableAndLinearisedContent
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageD : Check table is presentation table with aria role defined as presentation

-   code : CheckTableIsPresentationWithRoleAria
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageE : Check table is not presentation table with aria role not defined as presentation

-   code : CheckTableIsNotPresentationWithoutRoleAria
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `<table>` tags or only tables identifed as data or complex tables (**Set1** AND **Set2** are empty)

#### Failed

The `"role"` attribute with the "presentation" value is missing on at least one presentation table (**Test2** returns false for at least one element)

#### Pre-qualified

In all other cases






## Files

- [TestCases files for rule 5.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule050301/)
- [Unit test file for rule 5.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050301Test.java)
- [Class file for rule 5.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050301.java)


