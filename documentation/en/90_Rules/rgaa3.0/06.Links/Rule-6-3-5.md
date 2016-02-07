# RGAA 3.0 -  Rule 6.3.5

## Summary

This test consists in checking whether the text of svg textual link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-3)

###Test

[6.3.5](http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-3-5)

### Description

Chaque intitul&eacute; de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienVectoriel">lien vectoriel</a> (contenu de l'alternative de l'image vectorielle, balise `svg`) est-il <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienHorsContexte">explicite hors contexte</a> (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.3">hors cas particuliers</a>) ?

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

All the `<a>` tags with a `"href"` attribute, with children (a[href]:has(*) )

##### Set2

All the elements of **Set1** without own text and with only one child of type `<svg>`(svg)

##### Set3

All the elements of **Set2** with a not empty text.

### Process

##### Test1

For each element of **Set2**, we check whether the link content doesn't belong to the text link blacklist.

For each element returning false in **Test1**, raise a MessageA, raise a MessageB instead

##### Test2

For each element of **Set2**, we check whether the link content doesn't only contain non alphanumeric characters

For each element returning false in **Test2**, raise a MessageA, raise a MessageB instead

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

##### Not Applicable

The page has no svg link (**Set1** is empty)

##### Failed

At least one svg link has a text content which is blacklisted or that only contains non alphanumerical characters (**Test1** OR **Test2** returns false for at least one element)

##### Pre-Qualified

In all other cases

## Notes 

We assume here that the svg links have only one child of type `<svg>`
