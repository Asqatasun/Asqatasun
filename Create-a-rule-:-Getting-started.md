# Getting started

Start by [creating your test referential context](/Tanaguru/Tanaguru/wiki/How-to-create-your-own-referential) by using the referential-creator maven plugin. 

Once created, let's implement some rules : 
##Detection rule
To implement a detection rule, implement a class that extends the `AbstractDetectionPageRuleImplementation` abstract class. 

For example, the detection of the h1 tag should be implemented as follows : 

```java
public class DetectH1 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public DetectH1 () {
        super(
                new SimpleElementSelector("h1"),// the selector implementation that performs the selection
                TestSolution.PASSED, // solution when at least one element is found
                TestSolution.FAILED, // solution when no element is found
                null, // no message created when element is found because passed doesn't produce message
                "H1TagMissing", // General message indicating the element is not present on the page
            );
    }

}
```
This test will return PASSED when at least one `h1` element is present on the page, FAILED instead, producing the internationalisable message "H1TagMissing" (see How to internationalize the messages thrown by a test to properly render it in the Tanaguru web-app interface)

Let's consider the opposite : checking whether an unwished element, like `iframe`, is absent on the page should be implemented as follows : 
```java
public class DetectIframe extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public DetectIframe () {
        super(
                new SimpleElementSelector("iframe"),// the selector implementation that performs the selection
                TestSolution.FAILED, // solution when at least one element is found
                TestSolution.PASSED, // solution when no element is found
                "IframeDetected", // message associated with each detected element
                null, // no message created when attribute is found because passed doesn't produce message
            );
    }
}
```
This test will return PASSED when no `iframe` has been found on the page, FAILED instead, producing the internationalisable message "IframeDetected" for each occurence found on the page. Thus, each occurence will be rendered in the  Tanaguru web-app interface with its line number, and snippet of html source representing the element.

##Selection and Check rule
Detection is great, but a bit limited. To perform checks on selected elements, implement a class that extends the `AbstractPageRuleWithSelectorAndCheckerImplementation` abstract class.  
Let's say that you want to check that all the links (`a` tags) of a page have a `title` attribute. Here is what your class would look like : 

```java
public class MyClassThatChecksWhetherEachLinkHaveTitleAttribute extends 
                         AbstractPageRuleWithSelectorAndCheckerImplementation {
    /**
     * Constructor
     */
    public MyClassThatChecksWhetherEachLinkHaveTitleAttribute() {
        super(new SimpleElementSelector("a"), // The ElementSelector implementation
              new AttributePresenceChecker( // The ElementChecker implementation
                  "title", // the attribute to search
                  TestSolution.PASSED, // solution when attribute is found
                  TestSolution.FAILED, // solution when attribute is not found
                  null, // no message created when attribute is found because passed doesn't produce message
                  "LinkWithoutTitleMessage");// message associated with element when attribute is not found
        );
    }

}
```

##More About Selection
You need to perform more complex selection? The `SimpleElementSelector` is based on [Jsoup](http://jsoup.org) and its powerfull CSS (or jquery) like selector syntax to find matching elements. Have a look to the [Jsoup selector-syntax description page](http://jsoup.org/cookbook/extracting-data/selector-syntax) to know more about what you can do.

You can use also use one of [our implementations](#the-existing-implementations) or even implement yours. 

##More About Check

***

# About the rule entity
 
## What kind of data a rule can produce? 

A test can produce a result with 3 levels of informations : 
* Level1 (mandatory) -> PROCESS_RESULT that handles the final result of the test (and the total number of elements implied by the test)
* Level2 (mandatory) -> PROCESS_REMARK that can be either a general message associated with the result, or a message associated with an element of the DOM that needs to be treated. In this case, the type of the element, its result regarding the test, its position (line number), the source code representing it are automatically saved.
* Level3 (optional) -> EVIDENCE_ELEMENT that can store additionnal informations about the DOM element to help the qualification and thus the resolution.

# Advanced

The simplest way to write a rule is to create a java class that extends the abstract class `AbstractPageRuleWithSelectorAndCheckerImplementation` and set an [ElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/ElementSelector.html) implementation and an [ElementChecker](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementchecker/ElementChecker.htlm) implementation by constructor arguments. Let's see some examples :

## The `ElementSelector` interface
### The existing implementations 
This is a not exhaustive list of existing ElementSelector implementations : 
* [SimpleElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/SimpleElementSelector.html)
* [MultipleElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/MultipleElementSelector.html)
* [LinkElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/LinkElementSelector.html)
* [ImageElementSelector](http://tanaguru.org/Javadoc/3.0.2/org/opens/tanaguru/rules/elementselector/ImageElementSelector.html)
* ...

## The `ElementChecker` interface