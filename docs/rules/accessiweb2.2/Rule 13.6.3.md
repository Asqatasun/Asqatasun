### Summary

This test consists in checking whether each downloadable file provide information about its language

### Business description

Criterion : 13.6

Test : [13.6.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-16-3)

Test description :

On each Web page, does each file to download via a link or a form have information about its human language if necessary ([except in special cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit13-6 "Special cases for criterion 13.6"))?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Set1 : All the `<a>` tags with an `href` attribute (`a[href]`)

Set2: All the elements from Set1 with an `href` attribute that does not contain a fragment (presence of the hash sign (\#))

Set3 : All the elements from Set2 that have a proper extension (no parameters, a path after the domain that contains a "." character)

Set4 : All the `<form>` tags (form)

#### Process

##### Test1

For each element of Set3, we check whether the content of the `href` attribute of the link ends with an extension that belongs to the [downloadable document extension list](#downloadable-document-extension-list)

For each element returning true in Test1, raise a Message1

##### Test2

IF Test1 returns false, we check whether the size of Set2 is equals to size of Set3. In other words, we verify that all the links of the page have a well-defined extension.

If Test2 returns false (some links have no extension on the page), raise a Message2.

##### Test3

If Test2 returns true (all the links have a well-defined extension that belongs to the web content extension list), we check whether Set4 is empty (the page contains forms that may lead to a downloadable
document).

If Test3 returns false (some form are found on the page), raise a Message3.

##### Message1: File To Download Detected, check language

-   code : FileToDownloadDetectedCheckLanguage
-   status: NMI
-   parameter : href title attribute, title attribute, snippet
-   present in source : yes

##### Message2: Check manually links without extension

-   code : CheckManuallyLinkWithoutExtension\_Aw22-13063
-   status: NMI
-   present in source : no

##### Message3: Check downloadable document from form

-   code : CheckDownloadableDocumentFromForm\_Aw22-13063
-   status: NMI
-   present in source : no

#### Analysis

**NA**

-   Set2 is empty (the page has no link that are not anchor)
-   Test3 returns true (all the links of the page have a well-defined
    extension AND all the extension are of web content type AND the page
    has no form)

**NMI**

In all other cases

### Notes

We assume that a targetted document (pointed by the `href` attribute of the link) can be characterized by its extension.

Here is the content of the downloadable document extension list (feel free to help us improving it or to criticise it) :

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
-   oth
-   otg
-   ots
-   ott
-   cwk
-   cws
-   tar
-   tgz
-   bz
-   bz2
-   zip
-   gzip
-   gz
-   Z
-   7z
-   rar
-   r00
-   rpm
-   deb
-   msi
-   exe
-   bat
-   pif
-   class
-   torrent
-   dmg
-   apk
-   bin
-   bak
-   dat
-   jar
-   mdk
-   dsk
-   vmdk
-   r00
-   r01
-   r02
-   r03
-   r04
-   r05
-   r06
-   r07
-   r08
-   r09
-   r10
-   r11
-   r12
-   r13
-   r14
-   r15
-   r16
-   r17
-   r18
-   r19
-   r20
-   r21
-   r22
-   r23
-   r24
-   r25
-   r26
-   r27
-   r28
-   r29
-   r30
-   r31
-   r32
-   r33
-   r34
-   r35
-   r36
-   r37
-   r38
-   r39
-   r40
-   r41
-   r42
-   r43
-   r44
-   r45
-   r46
-   r47
-   r48
-   r49
-   r50
-   r51
-   r52
-   r53
-   r54
-   r55
-   r56
-   r57
-   r58
-   r59
-   r60
-   r61
-   r62
-   r63
-   r64
-   r65
-   r66
-   r67
-   r68
-   r69
-   r70
-   r71
-   r72
-   r73
-   r74
-   r75
-   r76
-   r77
-   r78
-   r79
-   r80
-   r81
-   r82
-   r83
-   r84
-   r85
-   r86
-   r87
-   r88
-   r89
-   r90
-   r91
-   r92
-   r93
-   r94
-   r95
-   r96
-   r97
-   r98
-   r99
-   taz

