### Summary

We check whether the language specified for each textual element of the page is correct (regarding the code list given in the following page [http://www.loc.gov/standards/iso639-2/php/code\_list.php](http://www.loc.gov/standards/iso639-2/php/code_list.php)) and relevant.

### Business description

Criterion : 8.4

Test : [8.4.1](http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-8-4-1)

Test description :

Test8.4.1: For each Web page with a [default human language](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mLangueDefaut), does the [language code](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mCodeLangue) pass the conditions below?

-   The [language code](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mCodeLangue) is valid
-   The [language code](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mCodeLangue) is relevant

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

#### Selection

##### Set 1 :

The `<html>` tag with a `lang` or `xml:lang` attribute.

##### Set 2 :

The textual tags (tags with a `title` attribute, an `alt` attribute or some content) without `lang` or `xml:lang` attribute. These attributes can be set to the current tag or to one of its ascendants.

##### Set 3 :

All the textual elements of the page which are not overidden by a `lang` attribute.

##### Set 3 :

All the textual elements of the page which are overidden by a `lang` attribute.

#### Process

##### Test 1:

We extract the language code from the `lang` or the `xml:lang` attributes from elements of Set 2. If these attributes are both set and different, we keep the `xml:lang` attribute if the `doctype` of the page is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep the `lang` attribute instead.

For each extracted `lang` attribute, we check its validity regarding the "ValidLanguageCode" whitelist 

-   Used nomenclature : "ValidLanguageCode", "XhtmlDoctypeDeclarations"
-   Reference : All the valid language codes recommanded in [http://www.loc.gov/standards/iso639-2/php/code\_list.php](http://www.loc.gov/standards/iso639-2/php/code_list.php%20 "http://www.loc.gov/standards/iso639-2/php/code_list.php ") and all the xhtml doctypes declarations recommanded in [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

###### MessageA : Wrong Language Declaration

-   code : WrongLanguageDeclaration
-   status: Failed
-   parameter : none
-   present in source : yes

##### Test 2:

We extract the language code from the `lang` or the `xml:lang` attributes from elements of Set 2. If these attributes are both set and different, we keep the `xml:lang` attribute if the `doctype` of the page is found within the "XhtmlDoctypeDeclarations" whitelist, and we keep the `lang` attribute instead

For each extracted lang attribute, we check its validity regarding the "ValidLanguageCode" whitelist

-   Used nomenclature : "ValidLanguageCode", "XhtmlDoctypeDeclarations"
-   Reference : All the valid language codes recommanded in [http://www.loc.gov/standards/iso639-2/php/code\_list.php](http://www.loc.gov/standards/iso639-2/php/code_list.php "http://www.loc.gov/standards/iso639-2/php/code_list.php ") and all the xhtml doctypes declarations recommanded in [http://www.w3.org/QA/2002/04/valid-dtd-list.html](http://www.w3.org/QA/2002/04/valid-dtd-list.html "http://www.w3.org/TR/html4/index/elements.html")

###### MessageA : Wrong Language Declaration

-   code : WrongLanguageDeclaration
-   status: Failed
-   parameter : none
-   present in source : yes

##### Test 3:

We detect the language of the textual elements of the page which are not overidden by a `lang` attribute (Set 3).

IF the detected language corresponds to the specified language AND the detection is seen as unreliable (regarding the google AJAX API detection described in the page [http://code.google.com/intl/fr/apis/language/translate/v1/using\_rest\_langdetect.html\#json\_response](http://code.google.com/intl/fr/apis/language/translate/v1/using_rest_langdetect.html#json_response "http://code.google.com/intl/fr/apis/language/translate/v1/using_rest_langdetect.html#json_response")

THEN raise Message A and return NMI

ELSE IF the detected language doesn't corresponds to the specified language AND the detection is seen as reliable

THEN raise Message B and return FAILED

ELSE IF the detected language doesn't corresponds to the specified language AND the detection is seen as unreliable

THEN raise Message C and return NMI

ELSE return PASSED

##### Test 4:

We detect the language of the textual elements of the page which are overidden by a lang attribute (Set 4).

IF the detected language corresponds to the specified language AND the detection is seen as unreliable (regarding the google AJAX API detection described in the page [http://code.google.com/intl/fr/apis/language/translate/v1/using\_rest\_langdetect.html\#json\_response](http://code.google.com/intl/fr/apis/language/translate/v1/using_rest_langdetect.html#json_response "http://code.google.com/intl/fr/apis/language/translate/v1/using_rest_langdetect.html#json_response")

THEN raise Message A and return NMI

ELSE IF the detected language doesn't corresponds to the specified language AND the detection is seen as reliable

THEN raise Message B and return FAILED

ELSE IF the detected language doesn't corresponds to the specified language AND the detection is seen as unreliable

THEN raise Message C and return NMI

ELSE return PASSED

###### MessageA : Suspected Relevant Language Declaration

-   code : SuspectedRelevantLanguageDeclaration

-   status: NMI
-   parameter : the extracted language, the detected language, and an extraction of the text (limited to 200 charachters) from which the detection has been applied 
-   present in source : yes

###### MessageB : Unrelevant Language Declaration

-   code : UnrelevantLanguageDeclaration

-   status: Failed
-   parameter : the extracted language, the detected language, and an extraction of the text (limited to 200 charachters) from which the detection has been applied 
-   present in source : yes

###### MessageC : Suspected Unrelevant Language Declaration

-   code : SuspectedUnrelevantLanguageDeclaration

-   status: NMI
-   parameter : the extracted language, the detected language, and an extraction of the text (limited to 200 charachters) from which the detection has been applied
-   present in source : yes

#### Analysis

##### NA

Selections are empty (The page has language declarations, that means that Set1 and Set2 are empty)

##### Failed

Test1 OR Test2 OR Test3 OR test4 returns FAILED

##### Passed

Test1 AND Test2 return true AND Test3 AND Test4 return PASSED

##### NMI

Test3 OR test4 returns NMI

### Notes

No notes yet for that rule
