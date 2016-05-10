# RGAA 3.0 -  Rule 6.3.1

## Summary

This test consists in checking whether the text of each textual link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-3)

### Test

[6.3.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-3-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienTexte">lien texte</a> v&eacute;rifie-t-il une de ces conditions (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.3">hors cas particuliers</a>) ? 
 
 * L'intitul&eacute; du lien est explicite hors contexte 
 * Un m&eacute;canisme permet &agrave; l'utilisateur d'obtenir un intitul&eacute; de lien explicite hors contexte 
 * Le contenu du titre de lien (attribut `title`) est explicite hors contexte 


### Level

**AAA**

## Technical description

### Scope

**page**

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

We assume here that the links are only composed of a text. (<a href="http://www.asqatasun.org/target.html">` my link`</a>`)



##  TestCases 

[TestCases files for rule 6.3.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060301/) 


