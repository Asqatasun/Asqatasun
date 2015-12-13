# AccessiWeb 2.2 - Rule 13.7.1

## Summary

This test consists in checking whether each downloadable office document have an accessible version.

## Business description

Criterion : 13.7

Test : [13.7.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-7-1)

Test description :

On each Web page, does each downloading functionality for an electronic document pass one of the conditions below ([except in special cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit13-7 "Special cases for criterion 13.7"))?

-   The [document to download](http://accessiweb.org/index.php/glossary-76.html#mVaccessible) is accessibility-supported
-   An alternative accessibility-supported version of the [document to download](http://accessiweb.org/index.php/glossary-76.html#mVaccessible) is available
-   An alternative version of the [document to download](http://accessiweb.org/index.php/glossary-76.html#mVaccessible) is available in HTML format

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

Set1 : All the `<a>` tags with an `href` attribute (`a[href]`)

Set2: All the elements from Set1 with an `href` attribute that does not contain a fragment (presence of the hash sign (\#))

Set3 : All the elements from Set2 that have a proper extension (no parameters, a path after the domain that contains a "." character)

Set4 : All the `<form>` tags (form)

### Process

##### Test1

For each element of Set3, we check whether the content of the `href` attribute of the link ends with an extension that belongs to the [office document extension list](#office-document-extension-list)

For each element returning true in Test1, raise a Message1

##### Test2

IF Test1 returns false, we check whether the size of Set2 is equals to size of Set3. In other words, we verify that all the links of the page have a well-defined extension.

If Test2 returns false (some links have no extension on the page), raise a Message2.

##### Test3

If Test2 returns true (all the links have a well-defined extension), we check whether Set4 is empty (the page contains forms that may lead to a downloadable document).

If Test3 returns false (some form are found on the page), raise a Message3.

##### Message1: Office Document Detected

-   code : OfficeDocumentDetected
-   status: NMI
-   parameter : `href` attribute, snippet
-   present in source : yes

##### Message2: Check manually links without extension

-   code : CheckManuallyLinkWithoutExtension\_Aw22-13071
-   status: NMI
-   present in source : no

##### Message3: Check downloadable document from form

-   code : CheckDownloadableDocumentFromForm\_Aw22-13071
-   status: NMI
-   present in source : no

### Analysis

**NA**

-   Set2 is empty (the page has no link that are not anchor)
-   Test3 returns true (all the links of the page have a well-defined extension AND all the extension are not of office document type AND the page has no form)

**NMI**

In all other cases

## Notes

We assume that a targetted document (pointed by the `href` attribute of the link) can be characterized by its extension.

Here is the content of the office document extension list (feel free to help us improving it or to criticise it) :

-   ods
-   fods
-   odt
-   fodt
-   odp
-   fodp
-   odg
-   fodg
-   pdf
-   doc
-   docx
-   docm
-   dot
-   dotm
-   xls
-   xlsx
-   xlsm
-   xlt
-   xltx
-   xltm
-   xlc
-   xlr
-   xlam
-   csv
-   ppt
-   pptx
-   pps
-   vsd
-   vst
-   vss
-   sxc
-   sxd
-   sxi
-   sxm
-   sxw
-   sda
-   sdc
-   sdd
-   sdf
-   sdp
-   sds
-   sdw
-   otf
-   otg
-   oth
-   ots
-   ott

