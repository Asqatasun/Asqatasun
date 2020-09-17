# RGAA 4.0 - Rule 1.8.2

## Summary

No-check rule

## Business description

### Criterion

[1.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-8)

### Test

[1.8.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-8-2)

### Description

> Chaque bouton « [image texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-texte) » (balise `<input>` avec l’attribut `type="image"`) [porteur d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), en l’absence d’un [mécanisme de remplacement](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#mecanisme-de-remplacement), doit si possible être remplacé par du [texte stylé](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#texte-style). Cette règle est-elle respectée (hors cas particuliers) ?

#### Particular cases (criterion 1.8)

> Pour ce critère, il existe une gestion de cas particulier lorsque le texte fait partie du logo, d’une dénomination commerciale, d’un [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha), d’une [image-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test) ou d’une image dont l’exactitude graphique serait considérée comme essentielle à la bonne transmission de l’information véhiculée par l’image. Dans ces situations, le critère est non applicable pour ces éléments.

#### Technical notes (criterion 1.8)

> Le texte dans les images vectorielles étant du texte réel, il n’est pas concerné par ce critère.

### Level

**AA**


## Technical description

### Scope

**Page**

### Decision level

@@@TODO


## Algorithm

### Selection

None

### Process

None

### Analysis

#### Not Tested

In all cases


## Files

- [TestCases files for rule 1.8.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010802/)
- [Unit test file for rule 1.8.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010802Test.java)
- [Class file for rule 1.8.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010802.java)


