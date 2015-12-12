# AccessiWeb 2.2 - Rule 2.2.2

## Summary

This test consists in checking the relevancy of the title associated
with each iframe tag

## Business description

Criterion : 2.2

Test : [2.2.2](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-2-2-2)

Test description :

For each
[iframe](http://accessiweb.org/index.php/glossary-76.html#mCadreEnLigne)
(iframe tag) with a title attribute, is the content of this attribute
relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1 :**

All the `iframe` tags with a "title" attribute ( iframe[title] )

### Process

##### Test1

For each element of Set1, we check whether the "title" attribute is not
empty

For each element returning false in Test1, raise a Message1.

##### Test2

For each element of Set1, we check whether the "title" attribute doesn't
only contain non alphanumerical characters.

For each element returning false in Test2, raise a Message1.

##### Test3

For each element of Set1, we check whether the "title" attribute is not
striclty identical to the `src` attribute.

For each element returning false in Test3, raise a Message1.

**Test4**

If Test1 AND Test2 AND Test3 return true (no pattern detected), raise a
Message2**.**

##### Message1: Not Pertinent title of iframe

-   code : NotPertinentTitleOfIframe
-   status: Failed
-   parameter : title attribute, snippet
-   present in source : yes

##### Message2 : Check Pertinence of title of iframe

-   code : CheckTitleOfIframePertinence
-   status: NMI
-   parameter : title attribute, snippet
-   present in source : yes

### Analysis

**NA :**

The page has no `iframe` tag with a "title" attribute (Set1 is empty)

**Failed :**

Test1 OR Test2 OR Test3 returns true for at least one element of Set1

**NMI :**

In all other cases

## Notes

***Definition of not-pertinent iframe title :***

An iframe title is regarded as not-pertinent in the following cases :

-   the iframe title is empty
-   the iframe title is identical to the src attribute of the iframe
-   the iframe title only contains not alphanumerics characters

