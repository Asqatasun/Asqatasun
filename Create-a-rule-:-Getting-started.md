# Getting started

Create your referential context by using the referential-creator maven plugin. 

The simplest way to write a rule is to extend the abstract class `AbstractPageRuleWithSelectorAndCheckerImplementation` and set a [ElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/ElementSelector.html) implementation and a [ElementChecker](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementchecker/ElementChecker.htlm) implementation as constructor arguments. Let's say that you want to check that all the links of a page have a title attribute. Here is what your class would look like : 

`public class MyClassThatChecksWhetherEachLinkHaveTitleAttribute extends AbstractPageRuleWithSelectorAndCheckerImplementation {

public class MyClassThatChecksWhetherEachLinkHaveTitleAttribute extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    /**
     * Constructor
     */
    public MyClassThatChecksWhetherEachLinkHaveTitleAttribute() {
        super(new SimpleElementSelector("a"), 
              new LinkTitlePertinenceChecker());
    }

}`

* `select(SSPHandler sspHandler, ElementHandler<Element> elementHandler);`
* `check(SSPHandler sspHandler, ElementHandler<Element> elementHandler, TestSolutionHandler testSolutionHandler)`

A rule is a java class that handles the effective test to apply on the Html code. It has to extend the abstract class `AbstractPageRuleMarkupImplementation` and implement two methods : 
* `select(SSPHandler sspHandler, ElementHandler<Element> elementHandler);`
* `check(SSPHandler sspHandler, ElementHandler<Element> elementHandler, TestSolutionHandler testSolutionHandler)`

## The different kind of rules  
### Detection 
To implement a detection rule, extends the `AbstractDetectionPageRuleImplementation` abstract class 
### Select and check selection
You need to do more than just checking the presence? 

# About the rule concept
 
## What kind of data a rule can produce? 

A test can produce a result with 3 levels of informations : 
* Level1 (mandatory) -> PROCESS_RESULT that handles the final result of the test (and the total number of elements implied by the test)
* Level2 (mandatory) -> PROCESS_REMARK that can be either a general message associated with the result, or a message associated with an element of the DOM that needs to be treated. In this case, the type of the element, its result regarding the test, its position (line number), the source code representing it are automatically saved.
* Level3 (optional) -> EVIDENCE_ELEMENT that can store additionnal informations about the DOM element to help the qualification and thus the resolution.

# Advanced

## The `ElementSelector` interface
### The existing implementations 
This is a not exhaustive list of existing ElementSelector implementations : 
* [SimpleElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/SimpleElementSelector.html)
* [MultipleElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/MultipleElementSelector.html)
* [LinkElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/LinkElementSelector.html)
* [ImageElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/ImageElementSelector.html)
* ...

## The `ElementChecker` interface