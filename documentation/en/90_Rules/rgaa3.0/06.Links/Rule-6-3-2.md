# RGAA 3.0 -  Rule 6.3.2

## Summary

This test consists in checking whether the text of each image link is enough explicit to understand the purpose and the target out of its context.

## Business description

### Criterion

[6.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-6-3)

### Test

[6.3.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-6-3-2)

### Description

Chaque intitul&eacute; de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienImage">lien image</a> (contenu de l'attribut `alt`, texte entre `<canvas>` et `</canvas>` ou texte entre `<object>` et `</object>`) est-il <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienHorsContexte">explicite hors contexte</a> (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit6-" title="Cas particuliers pour le crit&egrave;re 6.4">hors cas particuliers</a>) ?

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

All the `<a>` tags with a `"href"` attribute, with children (a[href]:has(*) )

#### Set2

All the elements of **Set1** without own text and with only one child of type `<img>`, `<canvas>` or `<object>` (img , object[type^=image], object[data^=data:image], object[data$=png], object[data$=jpeg], object[data$=jpg],object[data$=bmp], object[data$=gif], canvas) (assuming [the definition of an image link in Rgaa 3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#title-lien-image))	

#### Set3

All the elements of **Set2** with a child tag with a not empty textual alternative (assuming [the definition of an image link in Rgaa 3.0](http://references.modernisation.gouv.fr/referentiel-technique-0#title-lien-image))

### Process

##### Test1

For each element of **Set2**, we check whether the link content doesn't belong to the text link blacklist.

For each element returning false in **Test1**, raise a MessageA, raise a MessageB instead.

##### Test2

For each element of **Set2**, we check whether the link content doesn't only contain non alphanumeric characters.

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

The page has no image link (**Set1** is empty)

#### Failed

At least one image link has a child tag with an alternative content that is blacklisted or that only contains non alphanumerical characters (**Test1** OR **Test2** returns false for at least one element)

#### Pre-Qualified

In all other cases

## Notes

We assume here that the image links with only one child of type `<img>`, `<canvas>`, or `<object>`



##  TestCases 

[TestCases files for rule 6.3.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule060302/) 


