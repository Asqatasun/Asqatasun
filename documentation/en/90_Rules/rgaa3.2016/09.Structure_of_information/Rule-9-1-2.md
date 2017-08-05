# RGAA 3.2016 - Rule 9.1.2

## Summary
This test consists in checking the relevancy of the headings hierarchy.

## Business description

### Criterion
[9.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-9-1)

### Test
[9.1.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-9-1-2)

### Description
<div lang="fr">Dans chaque page Web, la hi&#xE9;rarchie entre les <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#titre">titres</a> (balise <code lang="en">h</code> ou balise poss&#xE9;dant un r&#xF4;le ARIA <code lang="en">"heading"</code> associ&#xE9; &#xE0; une propri&#xE9;t&#xE9; <code lang="en">aria-level</code>) est-elle pertinente&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Decidable**

## Algorithm

### Selection

#### Set1

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

We assume that the index of the first encountered <Hx> tag represents the index of reference for the document.

#### Test1

We check that the difference between the index of each element of **Set1** and the index of its previous element is not superior to 1.

For each occurrence of false-result of **Test1**, raise a MessageA

#### Test2

We check that the index of each element of **Set1** is not inferior to the index of reference.

For each occurrence of false-result of **Test1**, raise a MessageB

###### MessageA : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : previous heading tag, snippet
-   present in source : yes

###### MessageB : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : first heading tag, snippet
-   present in source : yes

### Analysis

#### Passed

**Test1** or **Test2** return true for all the elements of **Set1**

#### Failed

**Test1** or **Test2** return false for at least one element of **Set1**

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)



##  TestCases

[TestCases files for rule 9.1.2](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule090102/)


