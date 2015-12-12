# AccessiWeb 2.2 - Rule 13.2.1

## Summary

This test consists in checking whether the user is warned when he's about to open a new window via a link or a javascript command.

## Business description

Criterion : 13.2

Test : [13.2.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-2-1)

Test description :

On each Web page, for each new window opening launched via a link (target="_blank" attribute) or a javascript command, is the user warned?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

Set1 : All the `<a>` tags with an `href` attribute AND a not empty `target` attribute with a content different from "_self" (`a[href][target]:not([target=\_self]):not([target\~=\^\\\\s\*$])`)

### Process

**Test1**

If Set1 is not empty, raise a MessageA for each occurence.

Raise a MessageB instead.

##### MessageA : Check user is warned when a new window is opened

-   code : CheckUserIsWarnedWhenNewWindowOpen
-   status: NMI
-   parameter : text, title attribute, snippet
-   present in source : yes

##### MessageB : Check whether js prompt eventually a new window

-   code : CheckJavaScriptPromptANewWindow
-   status: NMI
-   present in source : no

### Analysis

returns **NMI** in all cases

## Notes

We assume here that a not empty value different from "_self" for the
`target` attribute lead to the opening of the page in a new window. The
detection is not only done with the "_target" value.

On latest webdev data set (2013-10-30, 78,000 pages), the selection has
been found on 62039 pages, i.e on 79% of the pages
