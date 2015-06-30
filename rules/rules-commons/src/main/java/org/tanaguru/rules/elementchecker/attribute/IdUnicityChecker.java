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

package org.tanaguru.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.tanaguru.rules.keystore.AttributeStore.ID_ATTR;

/**
 * This checker controls the unicity of the id attribute for a set of elements.
 * 
 * @author jkowalczyk
 */
public class IdUnicityChecker extends ElementCheckerImpl {

    /**
     * Default constructor 
     * 
     * @param messageCodeOnIdNotUnique
     */
    public IdUnicityChecker(
            String messageCodeOnIdNotUnique) {
        super();
        this.setFailureMsgCode(messageCodeOnIdNotUnique);
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
        this.setFailureMsgCode(messageCodeOnIdNotUnique);
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
        
        TestSolution testSolution = getSuccessSolution();
        
        for (Element el : elements) {
            if (el.hasAttr(ID_ATTR) && StringUtils.isNotBlank(el.id()) && 
                getIdPresenceCounter(sspHandler, el.id()) > 1) {
                testSolution = getFailureSolution();
                addSourceCodeRemark(getFailureSolution(), el, getFailureMsgCode());
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