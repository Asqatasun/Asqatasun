/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.elementchecker.doctype;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;
import org.asqatasun.rules.keystore.RemarkMessageStore;

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

    /**
     * Is HTML5 page? (blank doctype or valid HTML5 doctype)
     */
    public boolean isHtml5(SSPHandler sspHandler) {
        String doctype = sspHandler.getSSP().getDoctype();
        return StringUtils.isBlank(doctype) || isDoctypeValideHtml5(doctype);
    }


     @Override
    protected void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        if (isHtml5(sspHandler)) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        testSolutionHandler.addTestSolution(TestSolution.FAILED);
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
