# RGAA 4.0 — Rule 8.9.1

## Summary

This test consists in searching patterns indicating that forbidden tags
(not `div`, `span` or `table`) are used for layout purpose.

## Business description

### Criterion

[8.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-9)

### Test

[8.9.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1)

### Description

> Dans chaque page web les balises (à l’exception de `<div>`, `<span>` et `<table>`) ne doivent pas être utilisées [uniquement à des fins de présentation](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#uniquement-a-des-fins-de-presentation). Cette règle est-elle respectée ?

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

All the `<a>` tags without `href`, `name` or `id` attribute.

CSS selector: 
```jquery-css
a:not([href]):not([name]):not([id])
```

#### Set2

All the following tags without content (text or tag child) :
- `<p>`
- `<li>`

CSS selector: 
```jquery-css
p:matchesOwn(^$):not(:has(*)):not([hidden]), 
li:matchesOwn(^$):not(:has(*)):not([hidden])
```

### Process

#### Tests

##### Test1

Test emptiness of **Set1**.
- If empty, raise a MessageA
- If not empty, for each occurence of the **Set1** raise a MessageB

##### Test2

Test emptiness of **Set2**.
- If empty, raise a MessageA
- If not empty, for each occurence of the **Set2** raise a MessageC

#### Messages 

##### MessageA : No suspect pattern detected

- code: NoPatternDetected
- status: Pre-Qualified
- present in source: no

##### MessageB : Link without target

- code: LinkWithoutTarget
- status: Failed
- parameter: snippet
- present in source: yes

##### MessageC : Tags without content that are used for layout purpose

- code: TagsWithoutContentUsedForLayoutPurpose
- status: Failed
- parameter: snippet
- present in source: yes


### Analysis

#### Failed

The page contains either a link (without `href`, `name` or `id` attribute)
or a tag (`<p>` or  `<li>`) without content (text or tag child)

#### Pre-qualified

In all other cases

## Notes

On latest webdev data set (2013-10-30, 78,000 pages), links without
target (a:not([href]):not([name]):not([id])) have been found on 18256
pages, i.e on 23% of the pages.


## Files

- [TestCases files for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080901/)
- [Unit test file for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080901Test.java)
- [Class file for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080901.java)
