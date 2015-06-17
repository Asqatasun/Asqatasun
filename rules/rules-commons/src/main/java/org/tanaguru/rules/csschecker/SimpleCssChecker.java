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

package org.tanaguru.rules.csschecker;

import com.phloc.css.decl.CascadingStyleSheet;
import com.phloc.css.decl.visit.CSSVisitor;
import java.util.Collection;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.cssvisitor.SimpleCssVisitor;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This abstract class checker deals with css checks. It uses a css visitor 
 * to parse all the selectors of the page, make controls and collect remarks.
 * 
 * @author jkowalczyk
 */
public abstract class SimpleCssChecker implements CssChecker {

    /** the css visitor implementation */
    private SimpleCssVisitor cssVisitor;
    public SimpleCssVisitor getCssVisitor() {
        return cssVisitor;
    }
    
    public void setCssVisitor(SimpleCssVisitor cssVisitor) {
        this.cssVisitor = cssVisitor;
    }
    
    private NomenclatureLoaderService nomenclatureLoaderService;
    @Override
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    @Override
    public NomenclatureLoaderService getNomenclatureLoaderService() {
        return nomenclatureLoaderService;
    }
    
    /** the list of css media implied by the test **/
    private Collection<String> mediaList;
    @Override
    public void setMediaList(Collection<String> mediaList) {
        this.mediaList = mediaList;
    }
    
    /**
     * 
     * Default constructor
     */
    public SimpleCssChecker () {}

    @Override
    public int getCssRulesCounter() {
        if (cssVisitor != null) {
            return cssVisitor.getEffectiveSelectorCounter();
        }
        return 0;
    }

    @Override
    public void check(
            SSPHandler sspHandler, 
            String styleSheetName, 
            CascadingStyleSheet cascadingStyleSheet, 
            TestSolutionHandler testSolutionHandler) {
        initialiseVisitor(sspHandler.getProcessRemarkService(), styleSheetName, testSolutionHandler);
        CSSVisitor.visitCSS(cascadingStyleSheet, cssVisitor);
    }
    
    /**
     * 
     * @param prk
     * @param styleSheetName
     * @param testSolutionHandler 
     */
    protected void initialiseVisitor(ProcessRemarkService prk, String styleSheetName, 
             TestSolutionHandler testSolutionHandler) {
        // set media list when needed
        if (mediaList != null) {
            cssVisitor.setIncludeMediaList(mediaList);
        }
        if (getNomenclatureLoaderService() != null) {
            cssVisitor.setNomenclatureLoaderService(nomenclatureLoaderService);
        }
        cssVisitor.setSolutionHandler(testSolutionHandler);
        cssVisitor.setCurrentFileName(styleSheetName);
        cssVisitor.setProcessRemarkService(prk);
    }

}
