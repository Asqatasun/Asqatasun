# Rule 6.3.1
## Summary

This test consists in checking whether the text of a text link is enough
explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-6-3)

### Test

[6.3.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-6-3-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienTexte">lien texte</a> v&eacute;rifie-t-il une de ces conditions (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.3">hors cas particuliers</a>) ? 
 
 * L'intitul&eacute; du lien est explicite hors contexte 
 * Un m&eacute;canisme permet &agrave; l'utilisateur d'obtenir un intitul&eacute; de lien explicite hors contexte 
 * Le contenu du titre de lien (attribut `title`) est explicite hors contexte 


### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

##### **Set1 :**

All the <a\> tags with a "href" attribute, without children (
a[href]:not(:has(\*)) )

##### **Set2 :**

All the elements of Set1 with a not empty text.

### Process

##### Test1

For each element of Set2, we check whether the link content doesn't
belong to the text link blacklist.

For each element returning false in Test1, raise a MessageA, raise a
MessageB instead

##### Test2

For each element of Set2, we check whether the link content doesn't only
contain non alphanumeric characters

For each element returning false in Test2, raise a MessageA, raise a
MessageB instead

##### MessageA : Unexplicit Link

-   code : UnexplicitLink
-   status: Failed
-   parameter : link text, title attribute, snippet
-   present in source : yes

##### MessageB : Check link without context pertinence

-   code : CheckLinkWithoutContextPertinence
-   status: Need More Info
-   parameter : link text, title attribute, snippet
-   present in source : yes

### Analysis

##### **NA :**

Set1 is empty (the page has no simple links)

##### **Failed :**

Test1 OR Test2 returns false for at least one element (At least one
element of the Set2 has a text content which is blacklisted or that only
contains non alphanumerical characters)

##### **NMI :\
**

In all other cases

## Notes

We assume here that the links are only composed of a text. (<a
href="http://www.tanaguru.org/target.html"\> my link</a\>)

All the links that have children different from img or object, are
considered as combined links
