# Getting started

Start by [creating your test referential context](/Tanaguru/Tanaguru/wiki/How-to-create-your-own-referential) by using the referential-creator maven plugin. 

Once created, let's implement some rules : 
##Detection rule
To create a detection rule, implement a class that extends the `AbstractDetectionPageRuleImplementation` abstract class. 

For example, the detection of the `h1` tag should be implemented as follows : 

```java
public class DetectH1 extends AbstractDetectionPageRuleImplementation {

    public DetectH1 () {
        super(
                // the selector implementation that performs the selection
                new SimpleElementSelector("h1"),
                // solution when at least one element is found
                TestSolution.PASSED,
                // solution when no element is found
                TestSolution.FAILED, 
                // no message created when element is found 
                // because passed doesn't produce message
                null, 
                // General message indicating the element is 
                // not present on the page
                "H1TagMissing" 
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
                null // no message created when attribute is found because passed doesn't produce message
            );
    }
}
```
This test will return PASSED when no `iframe` has been found on the page, FAILED instead, producing the internationalisable message "IframeDetected" for each occurence found on the page. Thus, each occurence will be rendered in the  Tanaguru web-app interface with its line number, and snippet of HTML source representing the element.

##Selection and check rule
Detection is great, but you often need to perform checks on selected elements. To do so, implement a class that extends the `AbstractPageRuleWithSelectorAndCheckerImplementation` abstract class.  
Let's say that you want to check that all the links (`a` tags) of a page have not a `title` attribute. Here is what your class would look like : 

```java
public class CheckWhetherEachLinkHaventTitleAttribute extends 
                         AbstractPageRuleWithSelectorAndCheckerImplementation {

    public CheckWhetherEachLinkHaventTitleAttribute() {
               
        super(new SimpleElementSelector("a"), // The ElementSelector implementation
              new AttributePresenceChecker( // The ElementChecker implementation
                  // the attribute to search
                  "title",
                  // solution when attribute is found  
                  TestSolution.FAILED,
                  // solution when attribute is not found
                  TestSolution.PASSED,
                  // message associated with element 
                  // when attribute is not found
                  "LinkWithTitleDetected",
                  // no message created when attribute is not 
                  // found because passed doesn't produce message
                  null)
        );
    }

}
```
## Site level rule
Tanaguru enables to create rules at site level, in other words, make cross-pages checks.

The most obvious example is the verification of the unicity of the title tag for each page. Tanaguru provides
the `AbstractUniqueElementSiteRuleImplementation` abstract class that can be used as follows in this case : 

```java
public class CheckTitleContentUnicityAtSiteLevel
                   extends AbstractUniqueElementSiteRuleImplementation {

    /**
     * Constructor
     */
    public CheckTitleContentUnicityAtSiteLevel() {
        super(
                // The ElementSelector implementation   
                new SimpleElementSelector("head title"), 
                // The TextElementBuilder implementation
                new SimpleTextElementBuilder(), 
                // Message associated with element its title 
                // is present on another page
                "TitleIdenticalToAnotherPage",
                // Message created to render a cross-page 
                // version of the result 
                "TitleNotUnique",
                false);
    }
}
``` 
This example introduces a new usefull interface called [TextElementBuilder](/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework#textelementbuilder), that produces a String representation for a given Element. The [SimpleTextElementBuilder](https://github.com/Tanaguru/Tanaguru/blob/master/rules/rules-commons/src/main/java/org/opens/tanaguru/rules/elementselector/SimpleElementSelector.java) used in the example simply returns the content of the title element. You may need to use other [text element builder implementations](/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework#the-existing-textelementbuilder-implementations) or implement your own ones.

## Nomenclature based Check rule
Tanaguru uses the concept of nomenclature to create dynamic lists that then can be used as white lists or black lists. 

Let's consider the check of the doctype validity. To avoid to hard-code in the rule implementation the exhaustive list of allowed doctype declarations, a nomenclature (seen as a whitelist here) can be used to handle them. The addition of a new one (HTML6 ?) consists in inserting a new entry in database, without modifying a line of the code. You can have a look at the [DoctypeValidityChecker implementation](https://github.com/Tanaguru/Tanaguru/blob/master/rules/rules-commons/src/main/java/org/opens/tanaguru/rules/elementchecker/doctype/DoctypeValidityChecker.java) for more details.

The concept of nomenclature can also lead to the check of relevancy. Dealing with this problematic is very difficult and is often taken up with complex algorithms. The usage of a nomenclature as a blacklist enables to detect, in an easy and collaborative way, common irrelevant cases.
From this approach, the relevancy of the title of a page can be compared with a list of definitive irrelevant titles such as 'Document', 'Home', 'Welcome'. That list can be then populated from feedbacks by just inserting entries in database. The rule that implements this case could be written as follows : 
```java
public class CheckTitleTagRelevancy extends 
                 AbstractPageRuleWithSelectorAndCheckerImplementation {
    /**
     * Constructor
     */
    public CheckTitleTagRelevancy() {
        super(new SimpleElementSelector("title"), // The ElementSelector implementation
              new TextBelongsToBlackListChecker( // The ElementChecker implementation
                  // the TextElementBuilder implementation  
                  new SimpleTextElementBuilder(), 
                  // the name of the irrelevant titles nomenclature 
                  "IrrelevantTitleNomenclature",
                  // message created when the title belongs 
                  // to the blacklist 
                  "IrrelevantTitle",
                  // the text of the title is extracted 
                  // and displayed in case of irrelevant   
                  "text" 
              )
        );
    }

}
```
To make this test work, an entry named "IrrelevantTitleNomenclature" has to exist in the NOMENCLATURE table of the database. Please refer to the "[Create a nomenclature and populate it](/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework#create-a-nomenclature-and-populate-it)" section for more details.


##More About Selection
You need to perform more complex selection? The `SimpleElementSelector` is based on [Jsoup](http://jsoup.org) and its powerfull CSS (or jquery) like selector syntax to find matching elements. Have a look to the [Jsoup selector-syntax description page](http://jsoup.org/cookbook/extracting-data/selector-syntax) to know more about what you can do.

You can use also use one of [our selection implementations](/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework#the-existing-elementselector-implementations) or even implement your own ones. 

##More About Check
Based on the implementation of accessiblity rules, many checkers have been implemented and can be reused.
Regarding your need, you can use one of [our check implementations](/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework#the-existing-elementchecker-implementations) or even implement your own ones.

##More About More
Tanaguru can also make controls on CSS, combine selectors, checkers, use data extracted from a javascript script executing while fetching the page and more, characterise elements by using a marker approach (use the "decorative-image" class to identify all the decoration images of the page). You can browse [the Accessiweb 2.2 rules implementations](https://github.com/Tanaguru/Tanaguru/tree/master/rules/accessiweb2.2/src/main/java/org/opens/tanaguru/rules/accessiweb22) or the [RGAA v2.2 rules implementation](https://github.com/Tanaguru/Tanaguru/tree/master/rules/rgaa2.2/src/main/java/org/opens/tanaguru/rules/rgaa22) to get more examples of how to implement a rule. 

Want really more ? see [Create a rule : the framework](https://github.com/Tanaguru/Tanaguru/wiki/Create-a-rule-:-the-framework)

##Any questions?

Let's discuss [Tanaguru on StackOverflow](http://stackoverflow.com/search?q=tanaguru)
