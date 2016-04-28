# CSV export format description

## Important foreword

To understand how CSV export is crafted, we **strongly** suggest you read
[Structure of a result](userdoc-02b-structure_of_a_result.md). This describes
how Asqatasun results are organised. Once you get this, export formats
will be quite straightforward to understand.

## Structure of CSV export

An audit exported in CSV format is composed of one line per test. Each line contains the following fields:

```
Theme, Criterion, Test, Test Label, Level, Result, Details
```

First two lines are used to describe URL evaluated, level and date.

**Note:** Some fields (for instance Details) carry data that are themselves in a CSV-like format nested in the overall CSV export. Data may be nested up to 4 levels of CSV-like. (This will be extensively describded below.)

## Theme

Theme is the name of the topic. 

Example for *RGAA 3* referential:

* `Images`
* `Frames`
* `Colours`

## Criterion

Criterion is the reference of the criterion

Example for *RGAA 3* referential:

* `1.1`
* `1.2`
* `8.9`
* `13.7`

## Test

Test is the reference of the test. 

Example for *RGAA 3* referential:

* `1.1.1`
* `1.2.1`
* `8.9.1`
* `13.7.1`

## Test Label

Test Label is the complete description fo the test (from the official referential).

Example for test 1.1.1 from *RGAA 3* referential:

> Does each decorative image without legend (tag `img`) and with an `alt` attribute meet all these requirements?<ul><li>the content of the `alt` attribute is empty (`alt=""`)</li><li>the decorative image has no `title` attribute</li></ul>

## Level

Level is the priority of the test.

Examples for *RGAA 3* referential:

* `LEVEL_1` (equivalent of priority "A")
* `LEVEL_2` (equivalent of priority "AA")
* `LEVEL_3` (equivalent of priority "AAA")

## Result

Result is the actual result of the test. It can be one of:

* Passed
* Failed
* Not Applicable
* Pre-Qualified
* Not Tested

## Details

*Details* is organised in a CSV-like format.

Separator used is character `ø`:

```
Process remarks count ø Process remarks header ø Process remarks content
```

Let's get in the internals of *Details*.

### Process remarks counts

*Process remarks count* gives the number of intances of *Process remarks content*. Its value can be from 0 to N (N being an integer).

### Process remarks headers

*Process remarks headers* is a string defining the structure of *Process remarks content* (that will be detailed below).

Its value is:

```
{key; result; target; evidenceElementCounter; evidenceElementStructure; evidenceElementList}
```

### Process remarks content

*Process remarks content* is organised in a CSV-like format this way:

* Separator is character `;`
* Data are enclosed into curly brackets `{` and `}`

Data organisation for *Process remarks content* was in fact describded with the headers (just above). We place it again here:

```
{key; result; target; evidenceElementCounter; evidenceElementStructure; evidenceElementList}
```

Let's get in the internals of *Process remarks content*

#### Key

*Key* is the "message key" describing the Process Remark.

Here are a few examples of *Key* values:

* `AltMissing`
* `CheckNatureOfElementWithNotEmptyAltAttribute`
* `TitleAttributeMissing`
* `CheckTheContrastOfImagesWithText`

You may browse [whole list of Process Remarks keys](../30_Contributor_doc/Engine/Process_remarks_codes.md)

#### Result

*Result* corresponds to the result of the the Process Remark. Its value can be `failed` or `nmi` (nmi, "need more information", was the old name for Pre-Qualified).

#### Target

*Target* is the element targetted by the Process Remark. This is usually an HTML element.

#### EvidenceElementCounter

*EvidenceElementCounter* is the number of Evidence Elements present is *EvidenceElementList*

#### EvidenceElementStructure

*EvidenceElementStructure* is the description (headers) of the CSV-like structure used for the Evidence Elements.

Data are enclosed with brackets (`[` and `]`), and fields are separated by pipes `|`.

**Note:** it is important to understand this structure varies and depends on each test.

#### EvidenceElementList

*EvidenceElementList* is a CSV-like structure used to store all the Evidence Elements from a given Process Remark.

Data are enclosed with brackets (`[` and `]`), and fields are separated by pipes `|`.

As already said, the actual values inside *EvidenceElementList* depend on each test.

