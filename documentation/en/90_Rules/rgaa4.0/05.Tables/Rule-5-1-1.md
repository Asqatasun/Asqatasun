# RGAA 4.0 — Rule 5.1.1

## Summary ====================================================================

This tests consists in checking all "complex table" (`<table>` tags and elements with `role=table` attribute): 

- All element with `role=table` attribute having `aria-describedby` attribute.
- For an HTML5 page, all `<table>` having `<caption>` child tag.
- For a non-HTML5 page (HTML4, XHTML1, ...), all `<table>` having `summary` attribute.

"Complex table" may be characterized by HTML markers (see Notes for details about detection through markers).
When tables cannot be characterized, the test is applied but the result is semi-decidable.

## Business description =======================================================

### Criterion

[5.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-5-1)

### Test

[5.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-1-1)

### Description

> Pour chaque [tableau de données complexe](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#tableau-de-donnees-complexe) un [résumé](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#resume) est-il disponible ?

### Level

**A**


## Technical description ======================================================

### Scope

**Page**

### Decision level

**Decidable with marker**

## Algorithm ==================================================================

### Selection -----------------------------------------------------------------

#### Set1

All elements:

- with `role="table"` attribute
- with `aria-describedby` attribute

```jquery-css
[role=table][aria-describedby]
```

#### Set2

All the elements of **Set1** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set3

All the elements of **Set1** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).

-------------------

#### Set4

All elements:
- with `role="table"`  attribute
- without `aria-describedby` attribute

```jquery-css
[role=table]:not([aria-describedby])
```

#### Set5

All the elements of **Set4** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set6

All the elements of **Set4** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).

-------------------

#### Set7

For HTML5 page, all `table` elements with a `<caption>`  child element

```jquery-css
table:has(caption)
```

#### Set8

All the elements of **Set7** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set9

All the elements of **Set7** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).

-------------------


#### Set10

For HTML5 page, all `table` elements without a `<caption>`  child element

```jquery-css
table:not(:has(caption))
```

#### Set11

All the elements of **Set10** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set12

All the elements of **Set10** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).

-------------------

#### Set13

For HTML4 page (a non-HTML5 page: HTML4, XHTML1, ...),
all `table` elements with a `summary` attribute.

```jquery-css
table[summary]
```

#### Set14

All the elements of **Set13** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set15

All the elements of **Set13** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).

-------------------

#### Set16

For HTML4 page (a non-HTML5 page: HTML4, XHTML1, ...),
all `table` elements without a `summary` attribute.

```jquery-css
table:not([summary])
```

#### Set17

All the elements of **Set16** identified as complex table by marker usage 
(see Notes for details about detection through marker).

#### Set18

All the elements of **Set16** identified neither as complex table,
nor as data table, nor as presentation table by marker usage (see Notes for details about detection through marker).


### Process -------------------------------------------------------------------

#### Tests

##### Tests 1

If **Set2** is not empty, for each occurrence of **Set1** raise a MessageA.

##### Tests 2

If **Set3** is not empty, for each occurrence of **Set3** raise a MessageB.

##### Tests 3

If **Set5** is not empty, for each occurrence of **Set5** raise a MessageC.

##### Tests 4

If **Set6** is not empty, for each occurrence of **Set6** raise a MessageD.

##### Tests 5

If **Set8** is not empty, for each occurrence of **Set1** raise a MessageA.

##### Tests 6

If **Set9** is not empty, for each occurrence of **Set9** raise a MessageE.

##### Tests 7

If **Set11** is not empty, for each occurrence of **Set11** raise a MessageF.

##### Tests 8

If **Set12** is not empty, for each occurrence of **Set12** raise a MessageG.

##### Tests 9

If **Set14** is not empty, for each occurrence of **Set14** raise a MessageA.

##### Tests 10

If **Set15** is not empty, for each occurrence of **Set15** raise a MessageH.

##### Tests 11

If **Set17** is not empty, for each occurrence of **Set17** raise a MessageI.

##### Tests 12

If **Set18** is not empty, for each occurrence of **Set18** raise a MessageJ.

-----------------------

#### Messages

##### MessageA: 

- status: Passed
- parameter: Snippet
- present in source: yes

##### MessageB: Check element, with `role=table` and `aria-describedby` attributes, is a complex table

- code: CheckTableRoleWithAriaDescribedbyIsComplex
- status: Pre-Qualified
- parameter: `aria-describedby` attribute and snippet
- present in source: yes

##### MessageC: `aria-describedby` attribute is missing on complex table (element with `role=table`)

- code: AriaDescribedbyMissingOnComplexTableRole
- status: Failed
- parameter: Snippet
- present in source: yes

##### MessageD: Check element, with `role=table` attribute and without `aria-describedby` attribute, is not a complex table

- code: CheckTableRoleWithoutAriaDescribedbyIsNotComplex
- status: Pre-Qualified
- parameter: Snippet
- present in source: yes

##### MessageE: Check table with a `<caption>` child is a complex table

- code: CheckTableWithCaptionChildElementIsComplex
- status: Pre-Qualified
- parameter: Snippet
- present in source: yes

##### MessageF: `<caption>` child is missing on complex table
- code: CaptionMissingOnComplexTable
- status: Failed
- parameter: Snippet
- present in source: yes

##### MessageG: Check table without `<caption>` child is not a complex table
- code: CheckTableWithoutCaptionChildElementIsNotComplex
- status: Pre-Qualified
- parameter: Snippet
- present in source: yes

##### MessageH: Check table with a `summary` attribute is a complex table

- code: CheckTableWithSummaryIsComplex
- status: Pre-Qualified
- parameter: `summary` attribute and snippet
- present in source: yes

##### MessageI: `summary` attribute is missing on complex tables

- code: SummaryMissingOnComplexTable
- status: Failed
- parameter: Snippet
- present in source: yes

##### MessageJ: Check table without `summary` attribute is not a complex table

- code: CheckTableWithoutSummaryIsNotComplex
- status: Pre-Qualified
- parameter: Snippet
- present in source: yes


### Analysis ------------------------------------------------------------------

#### Not Applicable

**Set1**, **Set4**, **Set7**, **Set10**, **Set13** and **Set16** are empty

- If the markers are not used: the page has no array (`<table>` tag or element with `role=table` attribute).
- If markers are used: the page has no array (a `<table>` tag or an element with the `role=table` attribute)
  identified as a complex array.
  
(see Notes for details about detection through marker)

#### Failed

Requires the use of the "complex table" marker. (see Notes for details about detection through marker)

If one of the following conditions is met:

- At least one complex table (element with `role=table` attribute) has no `aria-describedby` attribute.
- For an HTML5 page, at least one complex `<table>` has no `<caption>` child tag.
- For a non-HTML5 page (HTML4, XHTML1, ...), at least one complex `<table>` has no `summary` attribute.

**Set5**, **Set11** or **Set17** are not empty.

#### Passed

Requires the use of the "complex table" marker. (see Notes for details about detection through marker)

All of the following conditions are required:

- All tables of the page are identified as complex tables
- All element with `role=table` attribute have an `aria-describedby` attribute.
- For an HTML5 page, all `<table>` have `<caption>` child tag.
- For a non-HTML5 page (HTML4, XHTML1, ...), all `<table>` have a `summary` attribute.

Which means (all of the following conditions are necessary):

- **Set2**, **Set8** or **Set14** are not empty.
- **Set5**, **Set11** and **Set17** are empty.
- **Set3**, **Set6**, **Set9**, **Set12**, **Set15**  and **Set18** are empty.

#### Pre-qualified:

In all other cases


## Notes ======================================================================

### non-HTML5 page (HTML4, XHTML1, ...)

For non-HTML5 page (HTML4, XHTML1, ...), 
we only take into account the `summary` attribute.

### Markers 

Web developer or content editor can explicitly define the nature of a table by adding a special value
in an HTML attribute. This is what we call *markers*

- **Complex table** markers can be set through the **COMPLEX_TABLE_MARKER** parameter.
- **Data table** markers can be set through the **DATA_TABLE_MARKER** parameter.
- **Presentation table** markers can be set through the **PRESENTATION_TABLE_MARKER** parameter.

The value(s) passed as marker(s) will be checked against the following attributes:

- `class`
- `id`
- `role`

## Files

- [TestCases files for rule 5.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule050101/)
- [Unit test file for rule 5.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050101Test.java)
- [Class file for rule 5.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050101.java)


