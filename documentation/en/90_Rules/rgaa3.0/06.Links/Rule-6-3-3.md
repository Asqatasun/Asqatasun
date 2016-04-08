# RGAA 3.0 -  Rule 6.3.3

## Summary

This test consists in checking whether the text of each clickable area is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-3)

### Test

[6.3.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-3-3)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mIntituleLien">intitul&eacute; de lien</a> de type <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZoneCliquable">zone cliquable</a> (contenu de l'attribut `alt` d'une balise `area`) est-il <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienHorsContexte">explicite hors contexte</a> (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.4">hors cas particuliers</a>) ?

### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<area>` tags with a `"href"` attribute and a `"alt"` attribute (area[href][alt] )

#### Set2

All the elements of **Set1** with a not empty `"alt"` attribute

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

#### Not Applicable

The page has no clickable area (**Set1** is empty)

#### Failed

At least one clickable area has an `"alt"` attribute content which is blacklisted or that only contains non alphanumerical characters (**Test1** OR **Test2** returns false for at least one element)

#### Pre-Qualified

In all other cases
