# RGAA 3.0 -  Rule 6.3.1

## Summary

This test consists in checking whether the text of each textual link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-6-3)

### Test

[6.3.1](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-6-3-1)

### Description
Does each <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mLienTexte">text
  link</a> meet one of the following conditions (except
    in <a title="Particular cases for criterion 6.3" href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit6-">particular cases</a>)?
    <ul><li>The link text is explicit out of its context</li>
  <li>The user can get an explicit
   link text out of its context, via a provided mechanism</li>
  <li>The content of the link title (<code>title</code> attribute) is
   explicit out of its context</li>
    </ul> 


### Level

**AAA**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<a>` tags with a `"href"` attribute, without children (a[href]:not(:has(*)) )

##### Set2

All the elements of **Set1** with a not empty text.

### Process

##### Test1

For each element of **Set3**, we check whether the link content doesn't belong to the text link blacklist.

For each element returning false in **Test1**, raise a MessageA, raise a MessageB instead.

##### Test2

For each element of **Set3**, we check whether the link content doesn't only contain non alphanumeric characters.

For each element returning false in **Test2**, raise a MessageA, raise a MessageB instead.

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

##### Not applicable

The page has no textual link (**Set1** is empty)

##### Failed

At least one textual link has a text content that is blacklisted or that only contains non alphanumerical characters (**Test1** OR **Test2** returns false for at least one element)

##### Pre-Qualified

In all other cases

## Notes

We assume here that the links are only composed of a text. (`<a href="https://asqatasun.org/target.html">`my link`</a>`)



##  TestCases 

[TestCases files for rule 6.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060301/) 


