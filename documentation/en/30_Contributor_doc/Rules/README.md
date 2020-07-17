# Implementing rules

## Global tooling

All abstract classes are located in `rules/rules-commons`.

Package `org.asqatasun.ruleimplementation` contains pre-implementation. Representative class is `AbstractPageRuleWithSelectorAndCheckerImplementation`, which may be the motly used abstract class.

Package `org.asqatasun.rules` contains "unitary" selectors and checkers. Representative class is `SimpleElementSelector` or 
`AttributePresenceChecker`

Example with RGAA v3 rule 1.1.1: `AttributePresenceChecker` is used to verify the presence of `alt` attribute.  

## Run tests on a rule

* in IntelliJ, right click on a test class and select "Run (CTRL-SHIFT-F10)"

## References

* In French: [Processus d’ajout d’un nouveau référentiel à Asqatasun](https://forum.asqatasun.org/t/sommaire-coulisse-processus-dajout-dun-nouveau-referentiel-a-asqatasun/189)
