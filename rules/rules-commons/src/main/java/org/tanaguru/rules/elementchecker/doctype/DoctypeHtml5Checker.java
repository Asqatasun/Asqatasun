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

package org.tanaguru.rules.elementchecker.doctype;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * This class checks whether the doctype is an HTML5 docytpe.
 * 
 * @author wellcom
 */
public class DoctypeHtml5Checker extends ElementCheckerImpl {
    
    /**
     * list of known doctypes,  the Html5 doctype is not case sensitive only one value can be tested 
     * but i a take the for :)
     */
    private static final String[] VALID_KNOWN_HTML5_DOCTYPES = 
            {"<!doctype html>", 
            "<!DOCTYPE html>"};
    
    /**
     * Default constructor
     */
    public DoctypeHtml5Checker() {
        super();
    }

     @Override
    protected void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        String doctype = sspHandler.getSSP().getDoctype();
         // if the page doesn't have any doctype declaration, the test is 
        // not applicable
        if (StringUtils.isBlank(doctype)) {
            addProcessRemark(TestSolution.NEED_MORE_INFO, RemarkMessageStore.DOCTYPE_ABSENT_CHECK_HTML5);
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            return;
        }

        if (isDoctypeValideHtml5(doctype)) {
            addProcessRemark(TestSolution.NEED_MORE_INFO, RemarkMessageStore.HTML5_DOCTYPE_DETECTED_CHECK_MANUALLY);
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            return;
        } 
        testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
    }
      
    /*
    *Chek if doctype is valid html5 delaration from local list of known Html5 doctypes.
    */
    private boolean isDoctypeValideHtml5(String doctype) {
        for (String vKnownDocType : VALID_KNOWN_HTML5_DOCTYPES) {
            if (vKnownDocType.equalsIgnoreCase(doctype)) {
                return true;
            }
        }
        return false;
    }
    
    
    
}
