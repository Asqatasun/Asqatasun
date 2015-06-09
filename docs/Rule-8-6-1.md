# Rule 8.6.1
## Summary

This test consists in checking the relevancy of the title tag in the
page.

## Business description

### Criterion

[8.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-8-6)

### Test

[8.6.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-8-6-1)

### Description

Pour chaque page Web ayant un <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTitrePage">titre de page</a> (balise `title`), le contenu de cette balise est-il pertinent ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

The content of the `<title>` tag

-   used nomenclature : none

-   reference : none

### Process

We compare the content of the `<title>` tag with blacklist elements.

-   used nomenclature : UnexplicitPageTitle

-   reference : none

### Analysis

-   NA : The page has no `<title>` tag
-   Failed : The title of the page has been found in the blacklist
-   Pre-Qualified : The title of the page has not been found in the blacklist

## Notes

No notes yet for that rule
