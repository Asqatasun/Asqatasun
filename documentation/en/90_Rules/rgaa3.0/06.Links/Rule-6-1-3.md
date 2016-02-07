# RGAA 3.0 -  Rule 6.1.3

## Summary

This test consists in checking whether the text of each clickable area is enough explicit to understand the purpose and the target.

## Business description

### Criterion

[6.1](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-1)

### Test

[6.1.3](http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-1-3)

### Description

Chaque lien doublant une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZoneCliquable">zone cliquable</a> d'une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mImgReactive">image r&eacute;active cot&eacute; serveur</a> v&eacute;rifie-t-il une de ces conditions (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.1">hors cas particuliers</a>) ? 
 
 * L'<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mIntituleLien">intitul&eacute; de lien</a> seul permet d'en comprendre la fonction et la destination 
 * Le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mContexteLien">contexte du lien</a> permet d'en comprendre la fonction et la destination 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<area>` tags with a `"href"` attribute and a `"alt"` attribute (area[href][alt] )

##### Set2

All the elements of **Set1** with a not empty text and without context (assuming [the definition of a link context in Rgaa3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#contexte-du-lien))

##### Set3

All the elements of **Set2** with a not empty text, with a context (assuming [the definition of a link context in Rgaa3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#contexte-du-lien))

In other words :

size(**Set1**) = size(**Set2**) + size(**Set3**)

### Process

##### Test1

For each element of **Set2**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test1**, raise a MessageA, raise a MessageB instead

##### Test2

For each element of **Set3**, we check whether the link content is not pertinent (see Notes about relevancy detection)

For each element returning true in **Test2**, raise a MessageC, raise a MessageD instead

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

##### MessageC : Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

##### MessageD : Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, `"title"` attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no clickable area (**Set1** is empty)

#### Failed

At least one clickable area without context has a text content which is blacklisted or only composed of non-alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

**Definition of not-pertinent link title :**

A link title is seen as not-pertinent in the following cases :

-   the link title is blacklisted (regarding the LinkTextBlacklist nomenclature)
-   the link only contains not alphanumerics characters
