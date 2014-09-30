### Summary

This test consists in checking the presence and the unicity of the "id"
attribute of each form field **associated with a label tag**. We assume
here that a form field is associated with a label tag when its "id"
attribute corresponds to the "for" attribute of any label tag or
﻿﻿﻿﻿﻿﻿when the form field is within the contents of a label element.

### Business description

Criterion : 11.1

Test : [11.1.2](http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-11-1-2)

Test description :

Does each [form
field](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mChpSaisie)
(input tag of type text, password, checkbox, radio, file, or textarea
and select tags), that is associated with a label (label tag), pass the
conditions below?

-   The [form
    field](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mChpSaisie)
    has an id attribute
-   The value of the id attribute is unique

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

##### Set1 (fields with explicit label)

-   All the `input` tags with a "type" attribute equals to:
    -   "text"
    -   or "password"
    -   or "checkbox"
    -   or "radio"
    -   or "file"

-   all the `textarea` tags
-   and all the `select` tags
-   and **for all of them**not within a `label` tag
-   and **for all of them** those whose the value of the "id" attribute
    corresponds to the value of the "for" attribute of any label tag in
    the page

##### Set2 (fields with implicit label)

-   All the `input` tags with a "type" attribute equals to "text" or
    "password" or "checkbox" or "radio" or "file", all the `textarea`
    tags and all the `select` tags within a `label` tag

#### Process

##### Test1

For each element of Set2 (fields with implicit labels), test if the node
contains an "id" attribute

For each occurence of false-result of Test1, raise a MessageA

###### MessageA : Id attribute missing

-   code :IdMissing
-   status: Failed
-   parameter : tag name
-   present in source : yes

##### Test2

SetA (fields with implicit labels and id) : All the occurences of Set2
(fields with implicit labels) with an "id" attribute.

For each element of Set1 (fields with explicit labels) and SetA (fields
with implicit labels and id), test if the "id" attribute is unique on
the page

For each occurence of false-result of Test2, raise a MessageB

###### MessageB : Id Not Unique

-   code :IdNotUnique
-   status: Failed
-   parameter : tag name
-   present in source : yes

#### Analysis

##### NA

Set1 and Set2 are empty (The page has no form fields associated with a
label tag)

##### Failed

Test1 or Test 2 return false for at least one element.

##### Passed

Test1 and Test2 return true for all the elements

### Notes

No notes yet for that rule
