# Rule 6.3.2
## Summary

This test consists in checking whether the text of an image link is
enough explicit to understand the purpose and the target out of its
context.

## Business description

### Criterion

[6.3](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-6-3)

### Test

[6.3.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-6-3-2)

### Description

Chaque intitul&eacute; de <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienImage">lien image</a> (contenu de l'attribut `alt`, texte entre `<canvas>` et `</canvas>` ou texte entre `<object>` et `</object>`) est-il <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienHorsContexte">explicite hors contexte</a> (<a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/cas_particulier.htm#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.4">hors cas particuliers</a>) ?

### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1 :

All the `<a>` tags with a "href" attribute, with children (
a[href]:has(*) )

#### Set2 :

All the elements of **Set1** without own text and with only one child of
type img or object (img , object[type^=image],
object[data^=data:image], object[data$=png], object[data$=jpeg],
object[data$=jpg],object[data$=bmp], object[data$=gif]) (assuming [the
definition of an image link in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mLienImage))

#### Set3 :

All the elements of **Set2** with a child tag with a not empty "alt"
attribute (assuming [the definition of a link context in AccessiWeb
2.2](http://accessiweb.org/index.php/glossary-76.html#mContexteLien))

### Process

##### Test1

For each element of **Set2**, we check whether the link content (content of
the "alt" attribute of the child tag) doesn't belong to the text link
blacklist.

For each element returning false in Test1, raise a MessageA, raise a
MessageB instead

##### Test2

For each element of **Set2**, we check whether the link content (content of
the "alt" attribute of the child tag) doesn't only contain non
alphanumeric characters

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

#### Not Applicable

**Set1** is empty (the page has no image links)

**Failed :**

Test1 OR Test2 returns false for at least one element (At least one
element of the **Set2** has a child tag with an "alt" attribute content
which is blacklisted or that only contains non alphanumerical
characters)

#### Pre-Qualified

In all other cases

## Notes

We assume here that the image links with only one child of type img or
object
