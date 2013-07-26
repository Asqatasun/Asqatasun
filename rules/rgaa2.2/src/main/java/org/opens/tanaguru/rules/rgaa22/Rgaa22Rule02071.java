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

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.NoResultException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Implementation of the rule 2.7 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-2-7">the rule 2.7 design page.</a>
 * @see <a href="http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html"> 2.7 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule02071 extends AbstractPageRuleImplementation {

    private static final String COLOR_EXTRACTOR_KEY="colorExtractor";
    private static final String LUMINOSITY_KEY="luminosity";
    private static final String BG_COLOR_KEY="bgColor";
    private static final String PATH_KEY="path";
    private static final String COLOR_KEY="color";
    private static final String FONT_SIZE_KEY="fontSize";
    private static final String FONT_WEIGHT_KEY="fontWeight";
    private static final String IS_VISIBLE_KEY="isHidden";
    private static final String BOLD_KEY="bold";
    private static final String BAD_CONTRAST_KEY="badContrast";
    private static final String BAD_CONTRAST_HIDDEN_ELEMENT_KEY="badContrastHiddenElement";
    
    /**
     * Default constructor
     */
    public Rgaa22Rule02071 () {
        super();
    }

    /**
     * Concrete implementation of the 2.7 rule.
     * @param sspHandler
     * @return
     *      the processResult of the test
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        TestSolution testSolution = TestSolution.PASSED;
        int elementCounter=0;
        Collection<ProcessRemark> remarkList = new ArrayList<ProcessRemark>();
        try {
            String ppr = sspHandler.getPreProcessResult(COLOR_EXTRACTOR_KEY, sspHandler.getPage());
            JSONArray json = new JSONArray(ppr);
            for(int i=0;i<json.length();i++) {
                JSONObject myJson = new JSONObject(json.get(i).toString());
                String lumi = myJson.get(LUMINOSITY_KEY).toString();
                if (!StringUtils.equals(lumi, "null")) {
                    double fontSize = getFontSizeInPt(myJson.get(FONT_SIZE_KEY).toString());
                    String weight = myJson.get(FONT_WEIGHT_KEY).toString();
                    int fontWeight = 0;
                    try {
                        fontWeight = Integer.valueOf(weight);
                    } catch (NumberFormatException nbe) {
                        if (StringUtils.equalsIgnoreCase(weight, "normal")) {
                            fontWeight = 400;
                        } else if (StringUtils.equalsIgnoreCase(weight, "bold")) {
                            fontWeight = 700;
                        } else if (StringUtils.equalsIgnoreCase(weight, "lighter")) {
                            fontWeight = 400;
                        } else if (StringUtils.equalsIgnoreCase(weight, "bolder")) {
                            fontWeight = 700;
                        } else {
                            fontWeight = 400;
                        }
                    }
                    Float luminosity = Float.valueOf(lumi);
                    boolean isBold = (fontWeight > 700);
                        if ( (isBold && fontSize < 14) || (!isBold && fontSize < 18) ) {
                        elementCounter++;
                        if (luminosity < 4.5) {
                            Logger.getLogger(this.getClass()).error(" cssPath " + myJson.get(PATH_KEY).toString()+ " "+ fontSize + " " + luminosity +" "+ " ");
                            TestSolution elementSolution = computeResult(
                                    sspHandler, 
                                    myJson.get(PATH_KEY).toString(), 
                                    Boolean.getBoolean(myJson.get(IS_VISIBLE_KEY).toString()),
                                    myJson.get(COLOR_KEY).toString(), 
                                    myJson.get(BG_COLOR_KEY).toString(), 
                                    remarkList);
                            if (testSolution !=  TestSolution.FAILED) {
                                testSolution = elementSolution;
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            Logger.getLogger(this.getClass()).error(e);
        } catch (NoResultException nre) {
            // if the getPreProcessResult returns a noResultException, that 
            // means a problem occured when executing js. Nothing cannot be done.
            // the testResult is NOT_TESTED
            testSolution = TestSolution.NOT_TESTED;
        }
        Logger.getLogger(this.getClass()).error(" remarkList " + remarkList.size());
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                remarkList);
        result.setElementCounter(elementCounter);
        
        return result;
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
     * @param sspHandler
     * @param cssPath
     * @param isVisible
     * @param color
     * @param bgColor
     * @return 
     */
    private TestSolution computeResult (
            SSPHandler sspHandler,
            String cssPath, 
            boolean isHidden, 
            String color, 
            String bgColor, 
            Collection<ProcessRemark> remarkList) {
        TestSolution testSolution = TestSolution.FAILED;
        String msgCode = BAD_CONTRAST_KEY;
        if (isHidden) {
            testSolution = TestSolution.NEED_MORE_INFO;
            msgCode = BAD_CONTRAST_HIDDEN_ELEMENT_KEY;
        }
        Elements elements = sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(cssPath).getSelectedElements();
        if (elements.isEmpty() || elements.size() > 1) {
            Logger.getLogger(this.getClass()).warn(" cssPath " + cssPath+ " returns more than one element on " +sspHandler.getSSP().getURI());
        } else {
            Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
            eeList.add(sspHandler.getProcessRemarkService().getEvidenceElement(COLOR_KEY, color));
            eeList.add(sspHandler.getProcessRemarkService().getEvidenceElement(BG_COLOR_KEY, bgColor));
            sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(
                    testSolution, 
                    elements.iterator().next(), 
                    msgCode, 
                    eeList);
            remarkList.addAll(sspHandler.getProcessRemarkService().getRemarkList());
        }
        return testSolution;
    }
}