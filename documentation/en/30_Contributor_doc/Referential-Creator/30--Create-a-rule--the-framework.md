## Rules input/output

@@@TODO refactor this doc

### Input

* DOM
* CSS "XML-ised"

@@@to be completed with more details

### Output
A test can produce a result with 3 levels of informations : 
* Level1 (mandatory) -> PROCESS_RESULT that handles the final result of the test (and the total number of elements implied by the test)
* Level2 (mandatory) -> PROCESS_REMARK that can be either a general message associated with the result, or a message associated with an element of the DOM that needs to be treated. In this case, the type of the element, its result regarding the test, its position (line number), the source code representing it are automatically saved.
* Level3 (optional) -> EVIDENCE_ELEMENT that can store additionnal informations about the DOM element to help the qualification and thus the resolution.

## The main interfaces

### The `ElementSelector`
#### Explanation
This interface defines a selection applied to the DOM, to set the scope of the rule, and thus its applicability

#### The existing ElementSelector implementations 
Here is a not exhaustive list of existing ElementSelector implementations : 
* [SimpleElementSelector ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementselector/SimpleElementSelector.html)
* [MultipleElementSelector ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementselector/MultipleElementSelector.html)
* [LinkElementSelector ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementselector/LinkElementSelector.html)
* [ImageElementSelector ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementselector/ImageElementSelector.html)
* ...

### The `ElementChecker`
#### Explanation
This interface defines a check to be done on elements 
#### Method to implement
```java
/**
     * Perform the check operation. The instance of {@link ElementHandler} 
     * received as a parameter is used to retrieve elements the test is about
     * and the instance of {@link TestSolutionHandler} received 
     * as a parameter is used to store the results of tests performed 
     * during the operation
     * 
     * @param sspHandler
     * @param elementHandler
     * @param testSolutionHandler
     *  
     */
    void check (
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler);
```
#### Abstract implementation
#### The existing ElementChecker implementations 
Here is a not exhaustive list of existing ElementChecker implementations : 
* [ElementPresenceChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/element/ElementPresenceChecker.html)
* [ChildElementPresenceChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/element/ChildElementPresenceChecker.html)
* [ElementUnicityChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/element/ElementUnicityChecker.html)
* [AttributePresenceChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/attribute/AttributePresenceChecker.html)
* [TextEmptinessChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/text/TextEmptinessChecker.html)
* [TextLengthChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/text/TextLengthChecker.html)
* [TextBelongsToBlackListChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/text/TextBelongsToBlackListChecker.html)
* [DoctypeValidityChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/doctype/DoctypeValidityChecker.html)
* [HeadingsHierarchyChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/headings/HeadingsHierarchyChecker.html)
* [LangChangeChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/lang/LangChangeChecker.html)
* [LinkPertinenceChecker ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/elementchecker/link/LinkPertinenceChecker.html)
* ...

### The `TextElementBuilder`
#### Explanation
This builder is in charge of creating a textual representation of an HTML element.
#### Method to implement
```java
/**
 * @param element
 * @return a textual representation of the element
 */
String buildTextFromElement(Element element);
```
Take a look at the [online javadoc of the Jsoup Element](https://jsoup.org/apidocs/org/jsoup/nodes/Element.html). 
#### The existing TextElementBuilder implementations 
Here is the list of existing TextElementBuilder implementations : 
* [SimpleTextElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/SimpleTextElementBuilder.html)
* [OwnTextElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/OwnTextElementBuilder.html)
* [TextAttributeOfElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/TextAttributeOfElementBuilder.html)
* [DeepTextElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/DeepTextElementBuilder.html)
* [CompleteTextElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/CompleteTextElementBuilder.html)
* [LinkTextElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/LinkTextElementBuilder.html)
* [PathElementBuilder ---> @@@TODO fix URL](#http://asqatasun.org/Javadoc/LATEST/org/asqatasun/rules/textbuilder/PathElementBuilder.html)

## Test context
### Create a nomenclature and populate it
```mysql
-- INSERT THE NOMENCLATURE
INSERT IGNORE INTO `NOMENCLATURE` (`Cd_Nomenclature`) VALUES ('MyNomenclature');
-- INSERT AN ELEMENT AND LINK IT TO THE NOMENCLATURE
INSERT IGNORE INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Label`) VALUES`('NomenclatureElementImpl', 'Value1');
UPDATE `NOMENCLATURE_ELEMENT` SET `Id_Nomenclature`= (SELECT `Id_Nomenclature` FROM `NOMENCLATURE` WHERE `Cd_Nomenclature` LIKE 'MyNomenclature') WHERE Label like 'Value1';
```
