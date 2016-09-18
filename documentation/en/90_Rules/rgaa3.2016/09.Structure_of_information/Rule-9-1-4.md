# RGAA 3.2016 - Rule 9.1.4

## Summary
This test consists in checking whether each heading of the page is relevant

## Business description

### Criterion
[9.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-9-1)

### Test
[9.1.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-9-1-4)

### Description
<div lang="fr">Dans chaque page Web, chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#titre">titre</a> (balise <code lang="en">h</code> ou balise poss&#xE9;dant un r&#xF4;le ARIA <code lang="en">"heading"</code> associ&#xE9; &#xE0; une propri&#xE9;t&#xE9; <code lang="en">aria-level</code>) est-il pertinent&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

#### Test1

For each element of **Set1**, we check whether the content of the tag is pertinent (see Notes about relevancy detection).

For each element returning false in **Test1**, raise a MessageA. Raise a MessageB instead.

###### MessageA : Not pertinent heading

-    code : NotPertinentHeading
-    status: Failed
-    parameter : tag text, tag name, snippet
-    present in source : yes

###### MessageB : Check heading pertinence

-    code : CheckHeadingPertinence
-    status: Pre-Qualified
-    parameter : tag text, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)

#### Failed

At least one element of **Set1** has an empty content or a content only composed of non alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-Qualified

In all other cases

## Notes

***Definition of not-pertinent legend :***

A heading is seen as not-pertinent in the following cases :

-   the content of the tag is empty
-   the content of the tag only contains non alphanumerics characters



##  TestCases

[TestCases files for rule 9.1.4](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule090104/)


