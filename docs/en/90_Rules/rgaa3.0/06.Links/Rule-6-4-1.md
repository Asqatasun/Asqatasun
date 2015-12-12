# Rule 6.4.1

## Summary

This test consists in checking whether each indentical textual link have the same purpose and target.

## Business description

### Criterion

[6.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-6-4)

###Test

[6.4.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-4-1)

### Description

Pour chaque page web, chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienIdentique">lien identique</a> de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mIntituleLien">type texte</a> a-t-il les m&ecirc;mes fonction et destination ?

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<a>` tags with a `"href"` attribute, without children (a[href]:not(:has(*)))

#### Set2

All the elements of **Set1** without `"title"` attribute and without context (assuming [the definition of a link context in Rgaa3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#contexte-du-lien)) and with an identical link text.

#### Set3

All the elements of **Set1** with a not empty `"title"` attribute, without context and with an identical link text (combinaison of the link text and the `"title"` attribute text)

#### Set4

All the elements of **Set1** with a `"href"` attribute, with a context and with an identical link text (combinaison of the link text and the `"title"` attribute text if it exists)

### Process

#### Test1

We check whether all the elements of **Set2** have an identical `"href"` attribute.

For each element returning false in **Test1**, raise a MessageA.

#### Test2

We check whether all the elements of **Set3** have an identical `"href"` attribute.

For each element returning false in **Test2**, raise a MessageA.

#### Test3

We check whether all the elements of **Set4** have an identical `"href"` attribute.

For each element returning false in **Test3**, raise a MessageB.

##### MessageA

-  code : IdenticalLinkWithDifferentTarget
-  status: Failed
-  parameter : text, `"href"` attribute, `"title"`, computed link text, tag name, snippet
-  present in source : yes

##### MessageB

-  code : SuspectedIdenticalLinkWithDifferentTarget
-  status: Pre-Qualified
-  parameter : text, `"href"` attribute, `"title"`, computed link text, tag name, snippet
-  present in source : yes

### Analysis

#### Not Applicable 

The page has no group of textual links with an identical link text (**Set2**, **Set3** and **Set4** are empty)

#### Failed

1.  At least one element of the **Set2** has a `"href"` attribute different from the others (**Test1** returns false for at least one element)
2.  At least one element of the **Set3** has a `"href"` attribute different from the others (**Test2** returns false for at least one element)

#### Pre-Qualified

In all other cases
