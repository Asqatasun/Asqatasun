/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.ruleimplementation;

import com.phloc.css.decl.CascadingStyleSheet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.StylesheetContent;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.csschecker.CssChecker;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. The selectAndCheck method 
 * implementation parses all the stylesheets of the page and make a call to 
 * the cssChecker set by constructor argument.
 * A list of media can be set by constructor argument to restrict the scope
 * of the test.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleCssImplementation 
                extends AbstractPageRuleDefaultImplementation {

    /** The locale instance of cssChecker */
    private CssChecker cssChecker;
    
    /** The media list nomenclature   */
    private String mediaListNomenclatureKey;
    
    /** The number of tested css rules  */
    private int cssRulesCounter;
    
    /** 
     * The remark message thrown when a css cannot be tested due to adaptation
     * error.
     */
    private static final String UNTESTED_RESOURCE_MSG_CODE = "UnTestedResource";

    /**
     * constructor
     * @param cssChecker 
     */
    public AbstractPageRuleCssImplementation(CssChecker cssChecker){
        super();
        this.cssChecker = cssChecker;
    }
    
    /**
     * constructor 
     * 
     * @param cssChecker
     * @param mediaListNomenclatureKey 
     */
    public AbstractPageRuleCssImplementation(
            CssChecker cssChecker,
            String mediaListNomenclatureKey){
        this(cssChecker);
        this.mediaListNomenclatureKey = mediaListNomenclatureKey;
    }
    
    @Override
    public int getSelectionSize() {
        return cssRulesCounter;
    }
    
    @Override
    protected void selectAndCheck(
                SSPHandler sspHandler, 
                TestSolutionHandler testSolutionHandler) {
        
        if (StringUtils.isNotBlank(mediaListNomenclatureKey)) {
            cssChecker.setMediaList(nomenclatureLoaderService.
                    loadByCode(mediaListNomenclatureKey).getValueList());
        }
        
        cssChecker.setNomenclatureLoaderService(nomenclatureLoaderService);

        for (Map.Entry<String,CascadingStyleSheet> entry : 
                sspHandler.beginCssSelection().getStyleSheetMap().entrySet()) {
            cssChecker.check(
                    sspHandler, 
                    entry.getKey(),
                    entry.getValue(), 
                    testSolutionHandler);
            cssRulesCounter += cssChecker.getCssRulesCounter();
        }

        if (!sspHandler.getStyleSheetOnError().isEmpty()) {
            addCssOnErrorRemarks(
                    sspHandler.beginCssSelection().getStyleSheetOnError(), 
                    testSolutionHandler, 
                    sspHandler.getProcessRemarkService());
        }
    }
    
    /**
     * Each not adapted css generates a process remark to indicate that 
     * some selectors haven't been tested
     * 
     * @param styleSheetsOnError
     * @param testSolutionHandler
     * @param processRemarkService
     */
    private void addCssOnErrorRemarks(
            Collection<StylesheetContent> styleSheetsOnError,
            TestSolutionHandler testSolutionHandler, 
            ProcessRemarkService processRemarkService) {

        for (StylesheetContent stylesheetContent : styleSheetsOnError) {
            List<EvidenceElement> evidenceElementList =
                    new ArrayList<EvidenceElement>();
            evidenceElementList.add(processRemarkService.getEvidenceElement(
                    ProcessRemarkService.DEFAULT_EVIDENCE,
                    stylesheetContent.getURI()));
            processRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.NEED_MORE_INFO,
                    null,
                    UNTESTED_RESOURCE_MSG_CODE,
                    evidenceElementList);
        }
        testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
    }

}