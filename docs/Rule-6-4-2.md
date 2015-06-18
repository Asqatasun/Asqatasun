# Rule 6.4.2
## Summary

This test consists in checking whether each indentical image link have
the same purpose and target

## Business description

### Criterion

[6.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-4)

### Test

[6.4.2](http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-4-2)

### Description

Pour chaque page web, chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mLienIdentique">lien identique</a> de <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mIntituleLien">type image</a> a-t-il les m&ecirc;mes fonction et destination ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

**Set1** : All the image links (see [the definition of an image link in
AccessiWeb2.1](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mLienImage))
with a "href" attribute, without "title" attribute, without context
(assuming [the definition of a link context in AccessiWeb
2.1](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mContexteLien))
and with an identical link text.

**Set2** : All the image links with a "href" attribute, a not empty "title"
attribute, without context and with an identical link text (combination
of the link text and the "title" attribute text)

**Set3** : All the image links with a "href" attribute, with a context and
with an identical link text (combination of the link text and the
"title" attribute text if it exists)

-   used nomenclature : none

-   reference : none

### Process

Test1 : We check whether all the elements of **Set1** have an identical
"href" attribute

Test2 : We check whether all the elements of **Set2** have an identical
"href" attribute

Test3 : We check whether all the elements of **Set3** have an identical
"href" attribute

-   used nomenclature : none

-   reference : none

### Analysis

-   NA : **Set1**, **Set2** and **Set3** are empty
-   Failed :

1.  At least one element of the **Set1** has a "href" attribute different
    from the others (Test1 returns false for at least one element)
2.  At least one element of the **Set2** has a "href" attribute different
    from the others (Test2 returns false for at least one element)

-   Pre-Qualified : In all other cases

-   Message 1:

1.  code : IdenticalLinkWithDifferentTarget
2.  status: Failed
3.  case : For each element returning false in Test1 and Test2
4.  parameter : tag name
5.  present in source : yes

-   Message 2:

1.  code : SuspectedIdenticalLinkWithDifferentTarget
2.  status: Pre-Qualified
3.  case : For each element returning true in Test3
4.  parameter : tag name
5.  present in source : yes

## Notes

No notes yet for that rule
