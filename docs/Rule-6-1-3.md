# Rule 6.1.3
## Summary

This test consists in checking whether the context of each clickable
area is enough explicit to understand the purpose and the target

## Business description

### Criterion

[6.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-6-1)

### Test

[6.1.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-6-1-3)

### Description

Chaque lien doublant une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mZoneCliquable">zone cliquable</a> d'une <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mImgReactive">image r&eacute;active cot&eacute; serveur</a> v&eacute;rifie-t-il une de ces conditions (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.1">hors cas particuliers</a>) ? 
 
 * L'<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mIntituleLien">intitul&eacute; de lien</a> seul permet d'en comprendre la fonction et la destination 
 * Le <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mContexteLien">contexte du lien</a> permet d'en comprendre la fonction et la destination 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

##### Set1 :

All the <area\> tags with a "href" attribute and a "alt attribute (
area[href][alt] )

##### Set2 :

All the elements of Set1 with a not empty text and without context
(assuming [the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

##### Set3 :

All the elements of Set2 with a not empty text, with a context (assuming
[the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

in other words :

size(Set1) = size(Set2) + size(Set3)

### Process

##### Test1

For each element of Set2, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test1, raise a Message1, raise a
Message2 instead

##### Test2

For each element of Set3, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test2, raise a Message3, raise a
Message4 instead

##### Test3

For each element of Set2, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test3, raise a Message1, raise a
Message2 instead

##### Test4

For each element of Set3, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test4, raise a Message3, raise a
Message4 instead

##### Message1 : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message2 : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message3 : Unexplicit Link With context

-   code : UnexplicitLinkWithContext
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### Message4 : Check link with context pertinence

-   code : CheckLinkWithContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

### Analysis

#### Not Applicable

Set1 is empty

#### Failed

Test1 returns false for at least one element (At least one element of
the Set2 has a text content which is blacklisted)

#### Pre-qualified

In all other cases

## Notes

No notes yet for that rule
