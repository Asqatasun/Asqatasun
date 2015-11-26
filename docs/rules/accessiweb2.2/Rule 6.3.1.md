### Summary

This test consists in checking whether the text of a text link is enough explicit to understand the purpose and the target out of its context.

### Business description

Criterion : 6.3

Test : [6.3.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-3-1)

Test description :

Does each [text link](http://accessiweb.org/index.php/glossary-76.html#mLienTexte) pass one of the conditions below ([except in special cases](http://accessiweb.org/index.php/glossary-76.html#cpCrit6- "Special cases for criterion 6.3"))?

-   The link text is explicit out of its context
-   A mechanism allows the user to get an explicit link text out of its context
-   The content of the link title (title attribute) is explicit out of its context

Level : [Or](/en/category/rules-design/accessiweb-11/level/or)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

##### **Set1 :**

All the `<a>` tags with a `href` attribute, without children (`a[href]:not(:has(\*))` )

##### **Set2 :**

All the elements of Set1 with a not empty text.

#### Process

##### Test1

For each element of Set2, we check whether the link content doesn't belong to the text link blacklist.

For each element returning false in Test1, raise a MessageA, raise a MessageB instead

##### Test2

For each element of Set2, we check whether the link content doesn't only contain non alphanumeric characters

For each element returning false in Test2, raise a MessageA, raise a MessageB instead

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, `title` attribute, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, `title` attribute, snippet
-   present in source : yes

#### Analysis

##### **NA :**

Set1 is empty (the page has no simple links)

##### **Failed :**

Test1 OR Test2 returns false for at least one element (At least one element of the Set2 has a text content which is blacklisted or that only contains non alphanumerical characters)

##### **NMI :**

In all other cases

### Notes

We assume here that the links are only composed of a text. (`<a href="http://www.asqatasun.org/target.html"> my link</a>`)

All the links that have children different from `img` or `object`, are considered as combined links
