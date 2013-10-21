/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.contrast;

import java.text.DecimalFormat;
import java.util.*;
import javax.annotation.Nonnull;
import javax.persistence.NoResultException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.opens.tanaguru.rules.elementchecker.contrast.exception.ContrastCheckerParseResultException;
import static org.opens.tanaguru.rules.keystore.EvidenceStore.*;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.opens.tanaguru.rules.utils.ContrastHelper;
import static org.opens.tanaguru.service.ProcessRemarkService.DEFAULT_EVIDENCE;

/**
 * This checker tests whether the contrast between the colour of each textual 
 * element and their background colour is correct regarding a settable ratio
 * value. 
 * The ratio is computed during the loading phase and this test consists in
 * parsing the result in JSON format, create the result of the test and the
 * remarks.
 * 
 */
public class ContrastChecker extends ElementCheckerImpl {

    private static final Logger LOGGER = Logger.getLogger(ContrastChecker.class);
    
    private static final String COLOR_EXTRACTOR_PRE_PROCESS_RESULT_KEY = "colorExtractor";
    
    private static final String JSON_FONT_SIZE_KEY = "fontSize";
    private static final String JSON_ELEMENT_PATH_KEY = "path";
    private static final String JSON_FONT_WEIGHT_KEY = "fontWeight";
    private static final String JSON_FG_COLOR_KEY = "color";
    private static final String JSON_BG_COLOR_KEY = "bgColor";
    private static final String JSON_IS_HIDDEN_KEY = "isHidden";
    private static final String JSON_IS_TEXT_NODE_KEY = "isTextNode";

    private static final String NORMAL_WEIGHT_KEY = "normal";
    private static final String BOLD_WEIGHT_KEY = "bold";
    private static final String LIGHTER_WEIGHT_KEY = "lighter";
    private static final String BOLDER_WEIGHT_KEY = "bolder";
    
    private static final int NORMAL_FONT_SIZE_THRESHOLD = 18;
    private static final int BOLD_FONT_SIZE_THRESHOLD = 14;
    private static final int NORMAL_FONT_WEIGHT = 400;
    private static final int BOLD_FONT_WEIGHT = 700;
    
    /** The contrast ratio */
    private Float contrastRatio;
    
    /** is lower test */
    private boolean isLowerTest = true;
    
    /** check bold text */
    private boolean checkBoldText = true;
    
    /** check normal text */
    private boolean checkNormalText = true;

    /** */
    private boolean createSourceCodeRemarkForNmi = false;
    
    /** */
    private Set<String> notTreatedBackgroundColorValue = new HashSet<String>();
    
    /** The element counter */
    int elementCounter = 0;
    public int getElementCounter() {
        return elementCounter;
    }
    
    /**
     * Constructor 
     * 
     * @param contrastRatio
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
        try {

            String ppr = sspHandler.getPreProcessResult(
                    COLOR_EXTRACTOR_PRE_PROCESS_RESULT_KEY, 
                    sspHandler.getPage());
            // at this step, if the NoResultException hasn't be caught, that 
            // means that some elements are present. We initialise the solution
            // as passed, if some elements are invalid, we override it with a 
            // a failed solution
            parseJSONArray(sspHandler, new JSONArray(ppr), testSolutionHandler);
            if (elementCounter > 0) {
                testSolutionHandler.addTestSolution(TestSolution.PASSED);
            } else {
                testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            }
        } catch (JSONException e) {
            resetCollectedDataOnException(testSolutionHandler, e);
        } catch (NoResultException nre) {
            // if the getPreProcessResult returns a noResultException, that 
            // means a problem occured when executing js. Nothing cannot be done.
            // the testResult is NOT_TESTED
            resetCollectedDataOnException(testSolutionHandler, nre);
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
     * @param json
     * @param testSolutionHandler
     * 
     * @throws JSONException 
     * @throws ContrastCheckerParseResultException
     */
    private void parseJSONArray(
            SSPHandler sspHandler,
            JSONArray json, 
            TestSolutionHandler testSolutionHandler) throws JSONException, ContrastCheckerParseResultException{
        for(int i=0;i<json.length();i++) {
            JSONObject myJson = new JSONObject(json.get(i).toString());

            // if the luminosity couldn't have been computed, its value is set 
            // to "-1"
            if (isElementPartOfTheScope(myJson)) {
                
                String bgColor = myJson.get(JSON_BG_COLOR_KEY).toString();
                String fgColor = myJson.get(JSON_FG_COLOR_KEY).toString();
                
                if (ContrastHelper.isColorTestable(fgColor) && 
                    ContrastHelper.isColorTestable(bgColor)) {
                    
                    elementCounter++;
                    
                    Double contrast = ContrastHelper.getConstrastRatio(fgColor, bgColor);
                    if (contrast < contrastRatio) {
                        LOGGER.debug(" cssPath " + 
                                myJson.get(JSON_ELEMENT_PATH_KEY).toString()+ " "
                                    + contrast +" "+ " ");
                        TestSolution elementSolution = createRemarkOnBadContrastElement (
                                sspHandler, 
                                myJson.get(JSON_ELEMENT_PATH_KEY).toString(), 
                                Boolean.valueOf(myJson.get(JSON_IS_HIDDEN_KEY).toString()),
                                new DecimalFormat("#.00").format(contrast),
                                myJson.get(JSON_FG_COLOR_KEY).toString(), 
                                myJson.get(JSON_BG_COLOR_KEY).toString());
                        testSolutionHandler.addTestSolution(elementSolution);
                    } else {
                        LOGGER.debug(" good luminosity " + 
                                myJson.get(JSON_ELEMENT_PATH_KEY).toString()+ " "
                                    + contrast +" "+ " ");
                    }
                } else {
                    elementCounter++;
                    createNmiRemarkForManualCheckElement(
                            sspHandler, 
                            myJson.get(JSON_ELEMENT_PATH_KEY).toString(), 
                            fgColor, 
                            bgColor);
                    testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
                    LOGGER.debug(" nmi " + 
                                myJson.get(JSON_ELEMENT_PATH_KEY).toString()+ " "
                                    +" "+ " ");
                }
            }
        }
    }

    /**
     * 
     * @param sspHandler
     * @param cssPath
     * @param isVisible
     * @param luminosity
     * @param color
     * @param bgColor
     * @return 
     */
    private TestSolution createRemarkOnBadContrastElement (
            SSPHandler sspHandler,
            String cssPath, 
            boolean isHidden, 
            String luminosity,
            String color, 
            String bgColor) throws ContrastCheckerParseResultException {
        
        TestSolution testSolution;
        String msgCode;

        if (isHidden) {
            // if the result is hidden, the result is NEED_MORE_INFO
            testSolution = TestSolution.NEED_MORE_INFO;
            msgCode = BAD_CONTRAST_HIDDEN_ELEMENT_MSG;
        } else {
            // By default the result is failed
            testSolution = TestSolution.FAILED;
            msgCode = BAD_CONTRAST_MSG;
        }

        Elements elements = sspHandler.domCssLikeSelectNodeSet(cssPath).
                getSelectedElements();
        if (elements.isEmpty()) {
            // if any element can't be retrieved by jsoup, that means that
            // something is weird with the dom. The check is stopped, and
            // the test returns not_tested to avoid false positive results
            LOGGER.warn(
                    " cssPath " + cssPath+ " returns no element on " +
                    sspHandler.getSSP().getURI() 
                    + " The result of the test is set to Not tested");
            throw new ContrastCheckerParseResultException();
        } else if (elements.size() > 1) {
            // if any element can't be retrieved by jsoup, that means that
            // something is weird with the dom. The check is stopped, and
            // the test returns not_tested to avoid false positive results
            LOGGER.warn(
                    " cssPath " + cssPath+ " returns more than one element on " +
                    sspHandler.getSSP().getURI() 
                    + " The result of the test is set to Not tested");
            throw new ContrastCheckerParseResultException();
        } else {
            Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
            eeList.add(getEvidenceElement(CONTRAST_EE, luminosity));
            eeList.add(getEvidenceElement(FG_COLOR_EE, color));
            eeList.add(getEvidenceElement(BG_COLOR_EE, bgColor));
            addSourceCodeRemark(
                    testSolution, 
                    elements.iterator().next(), 
                    msgCode, 
                    eeList);
        }
        return testSolution;
    }
    
    /**
     * 
     * @param sspHandler
     * @param cssPath
     * @param color
     * @param bgColor
     * @return 
     */
    private void createNmiRemarkForManualCheckElement (
            SSPHandler sspHandler,
            String cssPath, 
            String color, 
            String bgColor) {
        
        if (createSourceCodeRemarkForNmi) {
            Elements elements = sspHandler.domCssLikeSelectNodeSet(cssPath).
                    getSelectedElements();
            if (elements.isEmpty() || elements.size() > 1) {
                LOGGER.warn(
                        " cssPath " + cssPath+ " returns more than one element on " +
                        sspHandler.getSSP().getURI());
            } else {
                Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
                eeList.add(getEvidenceElement(FG_COLOR_EE, color));
                eeList.add(getEvidenceElement(BG_COLOR_EE, bgColor));
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO, 
                        elements.iterator().next(), 
                        CHECK_CONTRAST_MANUALLY_MSG, 
                        eeList);
            }
        } else if (!notTreatedBackgroundColorValue.contains(bgColor)) {
            List<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
            eeList.add(getEvidenceElement(DEFAULT_EVIDENCE, bgColor));
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    null,
                    NOT_TREATED_BACKGROUND_COLOR_MSG,
                    eeList);
            notTreatedBackgroundColorValue.add(bgColor);
        }
    }
    
    /**
     * 
     * @param fontSize
     * @return 
     */
    private double getFontSizeInPt (String fontSize) {
        return Double.valueOf(fontSize.substring(0,fontSize.indexOf("px"))) * 0.75;
    }

    /**
     * 
     * @param fontSize
     * @param fontWeight
     * @return 
     */
    private boolean isElementPartOfTheScope (JSONObject myJson) throws JSONException {
        boolean isTextNode = isTextNodeFromJson(myJson);
        if (!isTextNode) {
            return false;
        }
        boolean isBold = isBoldFromJson(myJson);
        double fontSize = getFontSizeFromJson(myJson);
        if (isLowerTest) {
            return (checkBoldText && isBold && fontSize < BOLD_FONT_SIZE_THRESHOLD) || 
                    (checkNormalText && !isBold && fontSize < NORMAL_FONT_SIZE_THRESHOLD);
        } else {
            return (checkBoldText && isBold && fontSize >= BOLD_FONT_SIZE_THRESHOLD) || 
                    (checkNormalText && !isBold && fontSize >= NORMAL_FONT_SIZE_THRESHOLD);
        }
    }

    /**
     * 
     * @param myJson
     * @return
     * @throws JSONException 
     */
    private double getFontSizeFromJson (JSONObject myJson) throws JSONException {
        return getFontSizeInPt(myJson.get(JSON_FONT_SIZE_KEY).toString());
    }
    
    /**
     * 
     * @param myJson
     * @return
     * @throws JSONException 
     */
    private boolean isBoldFromJson (JSONObject myJson) throws JSONException {
        String weight = myJson.get(JSON_FONT_WEIGHT_KEY).toString();
        int fontWeight;
        try {
            fontWeight = Integer.valueOf(weight);
        } catch (NumberFormatException nbe) {
            if (StringUtils.equalsIgnoreCase(weight, NORMAL_WEIGHT_KEY)) {
                fontWeight = NORMAL_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, BOLD_WEIGHT_KEY)) {
                fontWeight = BOLD_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, LIGHTER_WEIGHT_KEY)) {
                fontWeight = NORMAL_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, BOLDER_WEIGHT_KEY)) {
                fontWeight = BOLD_FONT_WEIGHT;
            } else {
                fontWeight = NORMAL_FONT_WEIGHT;
            }
        }
        return fontWeight >= BOLD_FONT_WEIGHT;
    }
    
    /**
     * 
     * @param myJson
     * @return
     * @throws JSONException 
     */
    private boolean isTextNodeFromJson (JSONObject myJson) throws JSONException {
        return Boolean.valueOf(myJson.get(JSON_IS_TEXT_NODE_KEY).toString());
    }

    /**
     * Reset collected data when an exception occurred
     * @param testSolutionHandler 
     */
    private void resetCollectedDataOnException (
            TestSolutionHandler testSolutionHandler, 
            Exception exception) {
        LOGGER.warn(exception);
        testSolutionHandler.cleanTestSolutions();
        testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
        getProcessRemarkService().resetService();
        elementCounter = 0;
    }

}