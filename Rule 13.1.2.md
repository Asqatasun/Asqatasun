### Summary

This test consists in checking whether a refresh action declared via the meta tag is immediate

### Business description

Criterion : 13.1

Test : [13.1.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-1-2)

Test description :

For each Web page, is each redirect process initiated via the meta tag immediate ([except in special cases](http://www.accessiweb.org/index.php/glossary-76.html#cpCrit13-1 "Special cases for criterion 13.1"))?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

Set1 : All the `<meta>` tags within a head with a `http-equiv` attribute equals to "refresh" and a `content` attribute that contains the "URL" or "url" occurence.

#### Process

Test1 :

We check whether the size of Set1 is equal to 1.

Test2 :

If Test1 is TRUE, we extract the delay value of the refresh regarding the definition of meta tag (@see Note)

Then we check whether the delay value is superior to 0. If true raise a message A

##### Message 1: Not Immediate Redirection via Meta

-   code : NotImmediateRedirectionViaMeta
-   status: Failed
-   parameter : tag name
-   present in source : yes

#### Analysis

##### NA

-   The Set1 is empty
-   Test1 returns FALSE

##### Failed

-   Test2 returns TRUE

##### Passed

-   Test2 returs FALSE

### Notes

We assume that the meta tag has to be defined has follows:

`    <meta http-equiv="refresh" content="delay;URL=\'URL\'">`
