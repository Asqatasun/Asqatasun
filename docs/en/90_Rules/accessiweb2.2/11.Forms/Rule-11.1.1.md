# AccessiWeb 2.2 - Rule 11.1.1

## Summary

This test consists in checking whether each form field contains either a
title attribute or is associated with a label tag. We assume here that a
form field is associated with label tag when its "id" attribute
corresponds to the "for" attribute of any label tag or ﻿﻿﻿﻿﻿﻿when the
form field is within the contents of the label elements

## Business description

Criterion : 11.1

Test : [11.1.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-1-1)

Test description :

Does each [form field](http://www.accessiweb.org/index.php/glossary-76.html#mChpSaisie)
(input tag of type text, password, checkbox, radio, file, or textarea and select tags), pass one of the conditions below?

-   The form field has a title attribute
-   A [label](http://www.accessiweb.org/index.php/glossary-76.html#mEtiquette) (label tag) is associated with the form field

Level : Bronze

## Technical description

Scope : page

Decision level :
decidable

## Algorithm

### Selection

##### Set1 (fields without implicit labels)

-   all the `input` tags with a "type" attribute equals to:
    -   "text"
    -   or "password"
    -   or "checkbox"
    -   or "radio"
    -   or "file",

-   and all the `textarea` tags
-   and all the `select` tags
-   and **for all of them** not within a `label` tag

##### Set2 (fields with implicit labels)

-   All the `input` tags with a "type" attribute equals to:
    -   "text"
    -   or "password"
    -   or "checkbox"
    -   or "radio"
    -   or "file"

-   and all the `textarea` tags
-   and all the `select` tags
-   and **for all of them** within a `label` tag

### Process

##### Test1

SetA ("for values") : All the "for" attributes values of each `label`
tag of the page.

For each element of Set1 (fields without implicit labels), test wether:

-   the node contains a not empty "title" attribute
-   or contains an "id" attribute value present in SetA (for values)

For each occurence of false-result of Test1, raise a MessageA

##### Test2

IF Set1 is empty (no field with explicit label)

THEN test whether Set2 is not empty

ELSE return result of Test1

##### MessageA : Invalid Form Field

-   code :InvalidFormField
-   status: Failed
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has no form fields, that means that Set1
and Set2 are empty)

#### Failed

Test2 returns false

#### Passed

Test2 returns true

## Notes

No notes yet for that rule
