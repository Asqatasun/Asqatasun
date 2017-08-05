# RGAA 3.2016 - Rule 6.3.1

## Summary
This test consists in checking whether the text of each textual link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion
[6.3](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-6-3)

### Test
[6.3.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-6-3-1)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lien-texte">lien texte</a> v&#xE9;rifie-t-il une de ces conditions (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-6-1,6-3" title="Cas particuliers pour le crit&#xE8;re 6.3">cas particuliers</a>)&nbsp;? <ul><li>L&#x2019;intitul&#xE9; du lien est <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#lien-explicite-hors-contexte">explicite hors contexte</a>&nbsp;;</li> <li>Un m&#xE9;canisme permet &#xE0; l&#x2019;utilisateur d&#x2019;obtenir un intitul&#xE9; de lien explicite hors contexte&nbsp;;</li> <li>Le contenu du titre de lien (attribut <code lang="en">title</code>) est explicite hors contexte.</li> </ul></div>

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

We assume here that the links are only composed of a text. (`<a href="http://www.asqatasun.org/target.html">`my link`</a>`)



##  TestCases

[TestCases files for rule 6.3.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule060301/)


