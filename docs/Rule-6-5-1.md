# Rule 6.5.1
## Summary

This test checks whether the page contains empty links.

## Business description

### Criterion

[6.5](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-5)

### Test

[6.5.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-5-1)

### Description

Dans chaque page Web, chaque lien (balise `a` avec un attribut `href`), &agrave; l'exception des <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mAncreNom">ancres</a>, a-t-il un <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mIntituleLien">intitul&eacute;</a> entre `<a>` et `</a>` ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**decidable**

## Algorithm

### Selection

**Set1** : All the `<a>` tags of the page that are not an anchor (
a:not([name]):not([id]) )

**Set2** : All the tags from **Set1** that have an empty text (including
children text) and that have not children with a not empty "alt"
attribute

### Process

The selection handles the process.

### Analysis

#### Not Applicable

**Set1** is empty (The page has no `<a>` tag)

#### Passed

**Set2** is empty (The page only contains not empty links)

#### Failed

**Set2** is not empty. For each element of **Set2**, raise a Message1

##### Message 1 : Empty link

-   code : EmptyLink
-   status: Failed
-   case : For each element of **Set2**
-   parameter : href attribute, snippet
-   present in source : yes

## Notes

A `<a>` tag is seen as an anchor if and only if it has either a "name" or
an "id" attribute (assuming [the definition of an anchor in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mAncreNom))
