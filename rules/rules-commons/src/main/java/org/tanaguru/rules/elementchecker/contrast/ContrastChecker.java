/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker.contrast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import javax.annotation.Nonnull;
import javax.persistence.NoResultException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.domelement.DomElement;
import org.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.tanaguru.rules.elementchecker.contrast.exception.ContrastCheckerParseResultException;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.IMG_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.EvidenceStore.*;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.tanaguru.rules.elementchecker.contrast.helper.ContrastHelper;
import static org.tanaguru.service.ProcessRemarkService.DEFAULT_EVIDENCE;


/**
 * This checker tests whether the contrast between the colour of each textual 
 * element and their background colour is correct regarding a settable ratio
 * value. 
 * 
 */
public class ContrastChecker extends ElementCheckerImpl {

    private static final Logger LOGGER = Logger.getLogger(ContrastChecker.class);
    
    private static final String ALT_CONTRAST_MECHA_PARAM_KEY = 
                    "ALTERNATIVE_CONTRAST_MECHANISM";
    
    private static final int NORMAL_FONT_SIZE_THRESHOLD = 18;
    private static final int BOLD_FONT_SIZE_THRESHOLD = 14;
    
    /** The contrast ratio */
    private final Float contrastRatio;
    
    /** is lower test */
    private boolean isLowerTest = true;
    
    /** check bold text */
    private boolean checkBoldText = true;
    
    /** check normal text */
    private boolean checkNormalText = true;

    /** */
    private final boolean createSourceCodeRemarkForNmi = false;
    
    /** */
    private final Set<String> notTreatedBackgroundColorValue = new HashSet<>();
    
    /** */
    private boolean alternativeContrastMechanismPresent = false;
    
    /** The element counter */
    int elementCounter = 0;
    public int getElementCounter() {
        return elementCounter;
    }
    
    /**
     * Constructor 
     * 
     * @param contrastRatio
     * @param checkNormalText
     * @param checkBoldText
     * @param isLowerTest 
     */
    public ContrastChecker(
            @Nonnull Float contrastRatio, 
            @Nonnull boolean checkNormalText,
            @Nonnull boolean checkBoldText,
            @Nonnull boolean isLowerTest) {
        super();
        this.contrastRatio = contrastRatio;
        this.checkNormalText = checkNormalText;
        this.checkBoldText = checkBoldText;
        this.isLowerTest = isLowerTest;
    }
    
    @Override
    protected void doCheck(SSPHandler sspHandler, Elements elements, TestSolutionHandler testSolutionHandler) {
        alternativeContrastMechanismPresent = 
                isAlternativeContrastMechanismPresentFromParam(sspHandler);
        
        Collection<DomElement> domElements;
        try {
            domElements = 
                    DomElementExtractor.extractDomElements(sspHandler);
            if (CollectionUtils.isEmpty(domElements)) {
                return;
            }
        } catch (NoResultException nre) {
            // if the getPreProcessResult returns a noResultException, that 
            // means a problem occured when executing js. Nothing cannot be done.
            // the testResult is NOT_TESTED
            resetCollectedDataOnException(testSolutionHandler, nre);
            return;
        }
        try {
            // at this step, if the NoResultException hasn't be caught, that 
            // means that some elements are present. We initialise the solution
            // as passed, if some elements are invalid, we override it with a 
            // a failed solution
            checkDomElements(sspHandler, domElements, testSolutionHandler);
            if (elementCounter > 0) { 
            // means that no element is on error or NMI
                if (testSolutionHandler.getTestSolution().equals(TestSolution.NOT_APPLICABLE)) {
                    LOGGER.debug("nothing to say about contrast");
                    if (sspHandler.domCssLikeSelectNodeSet(IMG_CSS_LIKE_QUERY).getSelectedElementNumber() == 0) {
                        testSolutionHandler.addTestSolution(TestSolution.PASSED);
                        LOGGER.debug("The page has no image, the result is Passed");
                    } else {
                        testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
                        addProcessRemark(TestSolution.NEED_MORE_INFO, 
                                         CHECK_CONTRAST_OF_IMAGE_MSG);
                        LOGGER.debug("The page has images, the result is NMI");
                    }
                } else {
                    LOGGER.debug("some Constrast remarks have been thrown");
                }
            } else {
                LOGGER.debug("test is not applicable");
            }
        } catch (ContrastCheckerParseResultException ccpre) {
            // if any problem is encountered while analysing elements, the 
            // test result is set to NOT_TESTED and the processRemarkService is
            // reset
            resetCollectedDataOnException(testSolutionHandler, ccpre);
        }
    }

    /**
     * 
     * @param sspHandler
     * @param domElements
     * @param testSolutionHandler
     * 
     * @throws ContrastCheckerParseResultException
     */
    private void checkDomElements(
            SSPHandler sspHandler,
            Collection<DomElement> domElements, 
            TestSolutionHandler testSolutionHandler) throws ContrastCheckerParseResultException{
        for(DomElement domElement : domElements) {
            
            // if the luminosity couldn't have been computed, its value is set 
            // to "-1"
            if (isElementPartOfTheScope(domElement)) {
                
                String bgColor = domElement.getBgColor();
                String fgColor = domElement.getFgColor();
                
                if (ContrastHelper.isColorTestable(fgColor) && 
                    ContrastHelper.isColorTestable(bgColor)) {
                    
                    elementCounter++;
                    
                    Double contrast = ContrastHelper.getConstrastRatio(fgColor, bgColor);
                    if (contrast < contrastRatio) {
                        LOGGER.debug(" cssPath " + domElement.getPath() + " "+
                                    + contrast);
                        DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.getDefault());
                        decimalSymbol.setDecimalSeparator('.');
                        TestSolution elementSolution = createRemarkOnBadContrastElement (
                                sspHandler, 
                                domElement,
                                new DecimalFormat("#.00", decimalSymbol).format(contrast));
                        testSolutionHandler.addTestSolution(elementSolution);
                    } else {
                        LOGGER.debug(" good luminosity " + 
                                domElement.getPath() + " "+
                                    + contrast);
                    }
                } else {
                    elementCounter++;
                    createNmiRemarkForManualCheckElement(
                            sspHandler, 
                            domElement);
                    testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
                    LOGGER.debug(" nmi " + domElement.getPath());
                }
            }
        }
    }

    /**
     * 
     * @param sspHandler
     * @param domElement
     * @param contrast
     * @return the TestSolution
     */
    private TestSolution createRemarkOnBadContrastElement (
            SSPHandler sspHandler,
            DomElement domElement, 
            String contrast) throws ContrastCheckerParseResultException {
        
        TestSolution testSolution;
        String msgCode;

        if (domElement.isHidden()) {
            // if the result is hidden, the result is NEED_MORE_INFO
            testSolution = TestSolution.NEED_MORE_INFO;
            msgCode = BAD_CONTRAST_HIDDEN_ELEMENT_MSG;
        } else if (alternativeContrastMechanismPresent) {
            // An alternative contrast mechanism is provided
            testSolution = TestSolution.NEED_MORE_INFO;
            msgCode = BAD_CONTRAST_BUT_ALT_MECHANISM_MSG;
        } else {
            // By default the result is failed
            testSolution = TestSolution.FAILED;
            msgCode = BAD_CONTRAST_MSG;
        }

        Element element = DomElementExtractor.getElementFromDomElement(domElement, sspHandler);
        if (element != null) {
            Collection<EvidenceElement> eeList = new LinkedList<>();
            eeList.add(getEvidenceElement(FG_COLOR_EE, domElement.getFgColor()));
            eeList.add(getEvidenceElement(BG_COLOR_EE, domElement.getBgColor()));
            eeList.add(getEvidenceElement(CONTRAST_EE, contrast));
            addSourceCodeRemark(
                    testSolution, 
                    element, 
                    msgCode, 
                    eeList);
        } else {
            // the element can't be retrieved by jsoup, that means that
            // something is weird with the dom. The check is stopped, and
            // the test returns not_tested to avoid false positive results
            throw new ContrastCheckerParseResultException();
        }

        return testSolution;
    }
    
    /**
     * 
     * @param sspHandler
     * @param element
     * @return 
     */
    private void createNmiRemarkForManualCheckElement (
            SSPHandler sspHandler,
            DomElement element) {
        
        if (createSourceCodeRemarkForNmi) {
            Element el = DomElementExtractor.getElementFromDomElement(element, sspHandler);
            if (el != null) {
                Collection<EvidenceElement> eeList = new ArrayList<>();
                eeList.add(getEvidenceElement(FG_COLOR_EE, element.getFgColor()));
                eeList.add(getEvidenceElement(BG_COLOR_EE, element.getBgColor()));
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO, 
                        el, 
                        CHECK_CONTRAST_MANUALLY_MSG, 
                        eeList);
            }
        } else if (!notTreatedBackgroundColorValue.contains(element.getBgColor())) {
            List<EvidenceElement> eeList = new ArrayList<>();
            eeList.add(getEvidenceElement(DEFAULT_EVIDENCE, element.getDisplayableBgColor()));
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    null,
                    NOT_TREATED_BACKGROUND_COLOR_MSG,
                    eeList);
            notTreatedBackgroundColorValue.add(element.getBgColor());
        }
    }
    
    /**
     * 
     * @param fontSize
     * @param fontWeight
     * @return 
     */
    private boolean isElementPartOfTheScope (DomElement element) {
        if (!element.isTextNode()) {
            return false;
        }
        boolean isBold = element.isBold();
        float fontSize = element.getFontSizeInPt();
        if (isLowerTest) {
            return (checkBoldText && element.isBold() && fontSize < BOLD_FONT_SIZE_THRESHOLD) || 
                    (checkNormalText && !isBold && fontSize < NORMAL_FONT_SIZE_THRESHOLD);
        } else {
            return (checkBoldText && isBold && fontSize >= BOLD_FONT_SIZE_THRESHOLD) || 
                    (checkNormalText && !isBold && fontSize >= NORMAL_FONT_SIZE_THRESHOLD);
        }
    }

    /**
     * Reset collected data when an exception occurred
     * @param testSolutionHandler 
     */
    private void resetCollectedDataOnException (
            TestSolutionHandler testSolutionHandler, 
            Exception exception) {
        LOGGER.info(exception);
        testSolutionHandler.cleanTestSolutions();
        testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
        elementCounter = 0;
    }

    private boolean isAlternativeContrastMechanismPresentFromParam(SSPHandler sspHandler) {
        for (Parameter parameter : sspHandler.getSSP().getAudit().getParameterSet()){
            if (parameter.getParameterElement().getParameterElementCode().equalsIgnoreCase(ALT_CONTRAST_MECHA_PARAM_KEY)) {
                if(Boolean.valueOf(parameter.getValue())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
        
}