/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.ruleimplementation.AbstractNotTestedRuleImplementation;
    
/**
 * Implementation of the rule 4.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-1">the rule 4.1 design page.</a>
 * @see <a href="http://rgaa.net/Presence-de-l-attribut-alt.html"> 4.1 rule specification
 *
 */
public class Rgaa22Rule04011 extends AbstractNotTestedRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04011 () {
        super();
    }
//    /** The evidence element attributes */
//    private static final String[] EVIDENCE_ELEMENT_ATTRIBUTES = 
//            {HtmlElementKeyStore.TEXT_ELEMENT2};
    
//    /** The element checker */
//    private static final ElementChecker elementChecker = 
//            new TextPertinenceChecker(
//                true, // the emptiness is tested
//                null, // no comparison with other attributes
//                null, // no comparison with blacklist
//                NOT_PERTINENT_LEGEND_MSG, //  message associated with element when not pertinent
//                CHECK_LEGEND_PERTINENCE_MSG, //  message associated with element when pertinence cannot be determined
//                Arrays.asList(EVIDENCE_ELEMENT_ATTRIBUTES));

//    private ElementHandler imageHandler = new ElementHandlerImpl();
//    private ElementHandler inputTypeHandler = new ElementHandlerImpl();
//    private ElementHandler areaHandler = new ElementHandlerImpl();
//    private ElementHandler appletHandler = new ElementHandlerImpl();
//    
//    private static ElementSelector imageSelector = 
//            new SimpleElementSelector(HtmlElementKeyStore.IMG_ELEMENT);
//    private static ElementSelector inputTypeSelector = 
//            new SimpleElementSelector(CssLikeQueryKeyStore.INPUT_IMAGE_CSS_LIKE_QUERY);
//    private static ElementSelector areaSelector = 
//            new SimpleElementSelector(HtmlElementKeyStore.AREA_ELEMENT);
//    private static ElementSelector appletSelector = 
//            new SimpleElementSelector(HtmlElementKeyStore.APPLET_ELEMENT);
//    
//    /** The evidence element attributes collected for images and input elements*/
//    private static final String[] SRC_EVIDENCE_ELEMENT_ATTRIBUTES = 
//            {SRC_ATTR};
//    
//    /** The evidence element attributes collected for area elements*/
//    private static final String[] HREF_EVIDENCE_ELEMENT_ATTRIBUTES = 
//            {HREF_ATTR};
//    
//    /** The evidence element attributes collected for applet elements*/
//    private static final String[] CODE_EVIDENCE_ELEMENT_ATTRIBUTES = 
//            {CODE_ATTR};
//
//    /** The element checker */
//    private static final ElementChecker altAttrCheckerWithSrc = 
//            new AttributePresenceChecker(
//                ALT_ATTR,
//                TestSolution.PASSED,
//                TestSolution.FAILED,
//                null, 
//                ALT_MISSING_MSG, 
//                Arrays.asList(SRC_EVIDENCE_ELEMENT_ATTRIBUTES));
//    
//    /** The element checker */
//    private static final ElementChecker altAttrCheckerWithHref = 
//            new AttributePresenceChecker(
//                ALT_ATTR,
//                TestSolution.PASSED,
//                TestSolution.FAILED,
//                null, 
//                ALT_MISSING_MSG, 
//                Arrays.asList(HREF_EVIDENCE_ELEMENT_ATTRIBUTES));
//    
//    /** The element checker */
//    private static final ElementChecker altAttrCheckerWithCode = 
//            new AttributePresenceChecker(
//                ALT_ATTR,
//                TestSolution.PASSED,
//                TestSolution.FAILED,
//                null, 
//                ALT_MISSING_MSG, 
//                Arrays.asList(CODE_EVIDENCE_ELEMENT_ATTRIBUTES));
//    
//    /**
//     * Constructor
//     */
//    public Rgaa22Rule04011() {
//        super();
//    }
//
//    @Override
//    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
//        imageSelector.selectElements(sspHandler, imageHandler);
//        inputTypeSelector.selectElements(sspHandler, inputTypeHandler);
//        areaSelector.selectElements(sspHandler, areaHandler);
//        appletSelector.selectElements(sspHandler, appletHandler);
//        elementHandler.addAll(imageHandler.get());
//        elementHandler.addAll(inputTypeHandler.get());
//        elementHandler.addAll(areaHandler.get());
//        elementHandler.addAll(appletHandler.get());
//    }
//
//    @Override
//    protected void check(SSPHandler sspHandler, ElementHandler elementHandler, TestSolutionHandler testSolutionHandler) {
//        if (elementHandler.isEmpty()) {
//            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
//        }
//        altAttrCheckerWithSrc.check(sspHandler, imageHandler, testSolutionHandler);
//        altAttrCheckerWithSrc.check(sspHandler, inputTypeHandler, testSolutionHandler);
//        altAttrCheckerWithHref.check(sspHandler, areaHandler, testSolutionHandler);
//        altAttrCheckerWithCode.check(sspHandler, appletHandler, testSolutionHandler);
//    }
////    public static String FAILED_MESSAGE_CODE = "altMissing";
//    public static String CHECK_CAPTCHA_MESSAGE_CODE = "checkElementIsCaptcha";
//    private static String JQUERY_EXPR1 = "img";
//    private static String JQUERY_EXPR2 = "area";
//    private static String JQUERY_EXPR3 = "input[type=image]";
//    private static String JQUERY_EXPR4= "applet";
//            
//    /**
//     * Default constructor
//     */
//    public Rgaa22Rule04011 () {
//        super();
//    }
//
//    /**
//     * Concrete implementation of the 4.1 rule.
//     * @param sspHandler
//     * @return
//     *      the processResult of the test
//     */
//    @Override
//    protected ProcessResult processImpl(SSPHandler sspHandler) {
//        super.processImpl(sspHandler);
//        
//        Collection<TestSolution> results = new ArrayList<TestSolution>();
//        Collection<ProcessRemark> prList = new ArrayList<ProcessRemark>();
//        
//        // works on img
//        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(JQUERY_EXPR1);
//        
//        // Some elements may be removed from the selection during computation.
//        // So the elementCounter is saved before
//        int elementCounter = sspHandler.getSelectedElementNumber();
//        results.add(computeResult(sspHandler, SRC_ATTR));
//        prList.addAll(sspHandler.getRemarkList());
//        
//        // works on area
//        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(JQUERY_EXPR2);
//        
//        // Some elements may be removed from the selection during computation.
//        // So the elementCounter is saved before
//        elementCounter += sspHandler.getSelectedElementNumber();
//        results.add(computeResult(sspHandler, HREF_ATTR));
//        prList.addAll(sspHandler.getRemarkList());
//        
//        // works on input[type=image]
//        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(JQUERY_EXPR3);
//        
//        // Some elements may be removed from the selection during computation.
//        // So the elementCounter is saved before
//        elementCounter += sspHandler.getSelectedElementNumber();
//        results.add(computeResult(sspHandler, SRC_ATTR));
//        prList.addAll(sspHandler.getRemarkList());
//        
//        // works on applet
//        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(JQUERY_EXPR4);
//        
//        // Some elements may be removed from the selection during computation.
//        // So the elementCounter is saved before
//        elementCounter += sspHandler.getSelectedElementNumber();
//        results.add(computeResult(sspHandler, CODE_ATTR));
//        prList.addAll(sspHandler.getRemarkList());
//        
//        DefiniteResult result = definiteResultFactory.create(
//                test,
//                sspHandler.getSSP().getPage(),
//                RuleHelper.synthesizeTestSolutionCollection(results),
//                prList);
//        
//        result.setElementCounter(elementCounter);
//
//        cleanUpRule();
//        
//        return result;
//    }
//    
//    /**
//     * Process regarding the selection.
//     * @param sspHandler
//     * @param eeAttribute the name of the attribute to save as evidence element
//     * @return 
//     *      The solution of the test
//     */
//    private TestSolution computeResult (SSPHandler sspHandler, String eeAttribute){
//        Elements elements = sspHandler.getSelectedElements();
//        if (elements.isEmpty()) {
//            return TestSolution.NOT_APPLICABLE;
//        }
//
//        // The captcha elements are removed from the current selection.
//        Elements captchaElements = RuleCheckHelper.extractCaptchaElements(elements);
//        Collection<TestSolution> results = new ArrayList<TestSolution>();
//        
//        // Checks first the potentially captcha images
//        if (!captchaElements.isEmpty()) {
//            results.add(RuleCheckHelper.checkAttributePresenceWithPreComputedResult(
//                    captchaElements, 
//                    ALT_ATTR, 
//                    CHECK_CAPTCHA_MESSAGE_CODE, 
//                    eeAttribute));
//        }
//
//        // Then checks all the other elements 
//        if (!elements.isEmpty()) {
//            results.add(RuleCheckHelper.checkAttributePresence(
//                    elements, 
//                    ALT_ATTR, 
//                    ALT_MISSING_MSG, 
//                    eeAttribute));
//        }
//        return RuleHelper.synthesizeTestSolutionCollection(results);
//    }
}