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

package org.tanaguru.rules.cssvisitor;

import com.phloc.css.ECSSVersion;
import com.phloc.css.ICSSWriterSettings;
import com.phloc.css.decl.*;
import com.phloc.css.decl.visit.DefaultCSSVisitor;
import com.phloc.css.writer.CSSWriterSettings;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.tanaguru.contentadapter.css.CSSContentAdapter;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This abstract class defines basic visitor implementation that needs to 
 * be specified by concrete implementations. It deals with media list filtering and 
 * process remarks creation for selectors on error 
 * @author jkowalczyk
 */
public abstract class SimpleCssVisitor extends DefaultCSSVisitor {
    
    private static final String INLINE_CSS_STR = "inline";
    private static final String LOCALE_CSS_STR = "locale";
    
    /** The current selector name **/
    private String currentSelector;
    public String getCurrentSelector() {
        return currentSelector;
    }
    
    /** the name of the visited file **/
    private String currentFileName;
    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }
    
    /** the list of media requested by the visit **/
    private Collection<String> includeMediaList;
    public void setIncludeMediaList(Collection<String> includeMediaList) {
        this.includeMediaList = includeMediaList;
    }
    
    /** state that determines if the current style has to be visited **/
    private boolean excludeStyleFromMedia = false;
    
    /** state that determines if the current property has to be visited **/
    boolean excludeProperty = false;
    
    private final ICSSWriterSettings writterSetting = new CSSWriterSettings(ECSSVersion.CSS30);

    /** The testSolutionHandler **/
    private TestSolutionHandler solutionHandler;
    public void setSolutionHandler(TestSolutionHandler solutionHandler) {
        this.solutionHandler = solutionHandler;
    }
    public TestSolutionHandler getSolutionHandler() {
        return solutionHandler;
    }
    
    /** The processRemarkService **/
    private ProcessRemarkService processRemarkService;
    public ProcessRemarkService getProcessRemarkService() {
        return processRemarkService;
    }
    
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }
    
    /** The processRemarkService **/
    private NomenclatureLoaderService nomenclatureLoaderService;
    public NomenclatureLoaderService getNomenclatureLoaderService() {
        return nomenclatureLoaderService;
    }
    
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }
    
    private int globalSelectorCounter = 0;
    public int getGlobalSelectorCounter() {
        return globalSelectorCounter;
    }

    int effectiveSelectorCounter = 0;
    public int getEffectiveSelectorCounter() {
        return effectiveSelectorCounter;
    }
    
    boolean propertyTargetRule = false;
            
    /**
     * Default constructor
     */
    public SimpleCssVisitor (){}
    
    /**
     * Constructor
     * @param propertyRuleTarget 
     */
    public SimpleCssVisitor (boolean propertyRuleTarget){
        this.propertyTargetRule = propertyRuleTarget;
    }
    
    /**
     * 
     * @param currentFileName 
     * @param solutionHandler 
     * @param processRemarkService 
     */
    public SimpleCssVisitor (
            String currentFileName,
            TestSolutionHandler solutionHandler,
            ProcessRemarkService processRemarkService) {
        this.currentFileName = currentFileName;
        this.solutionHandler = solutionHandler;
        this.processRemarkService = processRemarkService;
    }
    
    /**
     * 
     * @param currentFileName 
     * @param solutionHandler
     * @param processRemarkService 
     * @param includeMediaList 
     */
    public SimpleCssVisitor (
            String currentFileName,
            TestSolutionHandler solutionHandler,
            ProcessRemarkService processRemarkService, 
            Collection<String> includeMediaList) {
        this(currentFileName, solutionHandler, processRemarkService);
        this.includeMediaList = includeMediaList;
    }

    @Override
    public void onEndStyleRule(final CSSStyleRule aStyleRule) {
        this.currentSelector = null;
    }

    @Override
    public void onBeginFontFaceRule(final CSSFontFaceRule cssffr) {
        globalSelectorCounter++;
        if (excludeStyleFromMedia) {
            return;
        }
        Logger.getLogger(this.getClass()).debug("onBeginFontFaceRule ");
        this.currentSelector = cssffr.getAsCSSString(writterSetting, 0);
        // if this style rule is not excluded from the media type, it is 
        // added to the counter
        if (!propertyTargetRule) {
            effectiveSelectorCounter++;
        }
    }

    @Override
    public void onStyleRuleSelector(final CSSSelector aSelector) {
        globalSelectorCounter++;
        if (excludeStyleFromMedia) {
            return;
        }
        Logger.getLogger(this.getClass()).debug("onStyleRuleSelector ");
        this.currentSelector = aSelector.getAsCSSString(writterSetting, 0);
        // if this style rule is not excluded from the media type, it is 
        // added to the counter
        if (!propertyTargetRule) {
            effectiveSelectorCounter++;
        }
    }

    @Override
    public void onBeginPageRule(final CSSPageRule csspageRule) {
        globalSelectorCounter++;
        if (excludeStyleFromMedia) {
            return;
        }
        Logger.getLogger(this.getClass()).debug("onBeginPageRule " + csspageRule.getPseudoPage());
        this.currentSelector = csspageRule.getAsCSSString(writterSetting, 0);
        // if this style rule is not excluded from the media type, it is 
        // added to the counter
        if (!propertyTargetRule) {
            effectiveSelectorCounter++;
        }
    }
    
    @Override
    public void onDeclaration(final CSSDeclaration aDeclaration) {
        Logger.getLogger(this.getClass()).debug("onDeclaration ");
        if (excludeStyleFromMedia) {
            return;
        }
        
        checkCSSDeclarationProperty(aDeclaration.getProperty());
        if (!excludeProperty) {
            checkCSSDeclarationMembers(aDeclaration.getExpression().getAllMembers());
            if (propertyTargetRule) {
                effectiveSelectorCounter++;
            }
        }
        excludeProperty = false;
    }
    
    @Override
    public void onBeginMediaRule(final CSSMediaRule aMediaRule) {
        if (includeMediaList == null) {
            return;
        }
        for (CSSMediaQuery mediaQuery : aMediaRule.getAllMediaQueries()) {
            if (includeMediaList.contains(mediaQuery.getMedium())) {
                return;
            }
        }
        excludeStyleFromMedia = true;
    }

    @Override
    public void onEndMediaRule(final CSSMediaRule aMediaRule) {
        excludeStyleFromMedia = false;
    }
    
    @Override
    public void begin() {
        effectiveSelectorCounter =  0;
        globalSelectorCounter = 0;
    }
    
    @Override
    public void end() {
        if (effectiveSelectorCounter == 0) {
            solutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        } else if (!solutionHandler.getTestSolution().equals(TestSolution.FAILED) && 
                !solutionHandler.getTestSolution().equals(TestSolution.NEED_MORE_INFO)) {
            solutionHandler.addTestSolution(TestSolution.PASSED);
        }
    }

    private void checkCSSDeclarationMembers(List<ICSSExpressionMember> memberList) {
        for (ICSSExpressionMember expr : memberList) {
            checkICSSExpressionMember(expr);
        }
    }

    private void checkICSSExpressionMember(ICSSExpressionMember exprMember) {
        if (exprMember instanceof CSSExpressionMemberTermSimple) {
            checkCSSExpressionMemberTermSimple((CSSExpressionMemberTermSimple) exprMember);
        } else if (exprMember instanceof CSSExpressionMemberFunction) {
            checkCSSExpressionMemberFunction((CSSExpressionMemberFunction) exprMember);
        } else if (exprMember instanceof CSSExpressionMemberMath) {
            Logger.getLogger(this.getClass()).debug("expressionMember " + exprMember.getClass());
        } else if (exprMember instanceof CSSExpressionMemberTermURI) {
            Logger.getLogger(this.getClass()).debug("expressionMember " + exprMember.getClass());
        } else if (exprMember instanceof ECSSExpressionOperator) {
            Logger.getLogger(this.getClass()).debug("expressionMember " + exprMember.getClass());
        } else {
            Logger.getLogger(this.getClass()).debug("expressionMember " + exprMember.getClass());
        }
    }

    /**
     * 
     * @param exprMember 
     */
    private void checkCSSExpressionMemberFunction(CSSExpressionMemberFunction exprMember) {
        if (exprMember.getExpression() != null) {
            checkCSSDeclarationMembers(exprMember.getExpression().getAllMembers());
        }
    }
    
    /**
     * 
     * @param testSolution
     * @param message
     * @param remarkTarget
     */
    protected void addCssCodeRemark(
            TestSolution testSolution, 
            String message, 
            String remarkTarget) {
        solutionHandler.addTestSolution(testSolution);

        processRemarkService.addSourceCodeRemark(
                        testSolution,
                        remarkTarget,
                        message,
                        processRemarkService.getEvidenceElement(
                            EvidenceStore.CSS_SELECTOR_EE, currentSelector), 
                        processRemarkService.getEvidenceElement(
                            EvidenceStore.CSS_FILENAME_EE, getFileNameFromCssName(currentFileName)));
    }
    
    /**
     * If the css is inline or locale, the associated name of the css is inline.
     * 
     * @param css
     * @return 
     */
    private String getFileNameFromCssName(String cssFileName) {
        if (cssFileName.contains(CSSContentAdapter.INLINE_CSS_PREFIX)) {
            return INLINE_CSS_STR;
        } else if (cssFileName.contains(CSSContentAdapter.LOCALE_CSS_PREFIX)) {
            return LOCALE_CSS_STR;
        } else {
            return cssFileName;
        }
    }
    
    /**
     * Make the effective control on the css value
     * @param exprMember 
     */
    protected abstract void checkCSSExpressionMemberTermSimple(CSSExpressionMemberTermSimple exprMember);
    
    /**
     * Make the effective control on the css property 
     * @param property 
     */
    protected abstract void checkCSSDeclarationProperty(String property);
    
}