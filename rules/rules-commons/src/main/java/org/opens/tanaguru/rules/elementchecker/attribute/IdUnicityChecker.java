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

package org.opens.tanaguru.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ID_ATTR;
import org.opens.tanaguru.rules.utils.CssLikeSelectorBuilder;

/**
 * This checker controls the unicity of the id attribute for a set of elements.
 * 
 * @author jkowalczyk
 */
public class IdUnicityChecker extends ElementCheckerImpl {

    /**
     * The message code associated with a processRemark when an id is not unique
     * on the page
     */
    private String messageCodeOnIdNotUnique;
    
    /**
     * Default constructor 
     * 
     * @param messageCodeOnIdNotUnique
     */
    public IdUnicityChecker(
            String messageCodeOnIdNotUnique) {
        super();
        this.messageCodeOnIdNotUnique = messageCodeOnIdNotUnique;
    }
    
    /**
     * Default constructor 
     * 
     * @param messageCodeOnIdNotUnique
     * @param eeAttributeNameList 
     */
    public IdUnicityChecker(
            String messageCodeOnIdNotUnique, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.messageCodeOnIdNotUnique = messageCodeOnIdNotUnique;
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        checkIdUnicity(
                sspHandler,
                elements, 
                testSolutionHandler);
    }

    /**
     * This methods checks whether a given id is unique for a set of 
     * elements
     * 
     * @param sspHandler
     * @param elements
     * @param testSolutionHandler
     */
    private void checkIdUnicity (
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        
        if (elements.isEmpty() || !elements.hasAttr(ID_ATTR)) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        TestSolution testSolution = TestSolution.PASSED;
        
        for (Element el : elements) {
            if (el.hasAttr(ID_ATTR) && StringUtils.isNotBlank(el.id()) && 
                getIdPresenceCounter(sspHandler, el.id()) > 1) {
                testSolution = TestSolution.FAILED;
                if (StringUtils.isNotBlank(messageCodeOnIdNotUnique)) {
                    
                    addSourceCodeRemark(
                        TestSolution.FAILED, 
                        el, 
                        messageCodeOnIdNotUnique);
                }
            }
        }
        
        testSolutionHandler.addTestSolution(testSolution);
        
    }

    /**
     * 
     * @param sspHandler
     * @param idValue
     * @return the number of elements of the page with the given id
     */
    private int getIdPresenceCounter(SSPHandler sspHandler, String idValue) {
        String query = CssLikeSelectorBuilder.buildSelectorFromId(idValue);
        return sspHandler.domCssLikeSelectNodeSet(query).getSelectedElementNumber();
    }
    
}
